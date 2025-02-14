package org.hans.digitalwallet.services;

import org.hans.digitalwallet.exceptions.InsufficientFundsException;
import org.hans.digitalwallet.models.Account;
import org.hans.digitalwallet.models.Credential;
import org.hans.digitalwallet.repositories.AccountRepository;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DigitalWalletService {

    private final AccountRepository instance = AccountRepository.getInstance();

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private Account validateAccount (Credential credential) {
        Optional<Account> accountExist = instance.getAccountByUUID(credential.accountId());
        if (accountExist.isEmpty())
            throw new IllegalArgumentException("Account is invalid");

        Account account = accountExist.get();
        if  (!account.checkAccount(credential.user(), credential.pass()))
            throw new IllegalArgumentException("User and Pass Invalid for this account");

        return account;
    }

    public void withdraw (Credential credential, double amount)
            throws InterruptedException, InsufficientFundsException {
        Account account = validateAccount(credential);

        lock.lock();

        try {
            while (account.insufficientFunds(amount)) {
                System.out.println(Thread.currentThread().getName() + " Insufficient Funds - wait until a new deposit");
                System.out.println("-".repeat(100));
                condition.await();
            }

            account.withDraw(amount);
            System.out.println(Thread.currentThread().getName()
                    + " Funds is enough, withdraw was success"
                    + " Balance updated " + account.getBalance());

            System.out.println("-".repeat(100));
        } finally {
            lock.unlock();
        }

    }

    public void deposit (Credential credential, double amount) {
        Account account = validateAccount(credential);

        lock.lock();

        try {
            account.deposit(amount);
            System.out.println(
                    Thread.currentThread().getName() +
                    " new deposit - notify " + "Balance updated : "
                    + account.getBalance());

            System.out.println("-".repeat(100));
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public double getBalance(Credential credential) {
        Account account = validateAccount(credential);

        lock.lock();

        try {
            return account.getBalance();
        } finally {
            lock.unlock();
        }
    }

}
