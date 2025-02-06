package org.hans.digitalwallet.services;

import org.hans.digitalwallet.models.Account;
import org.hans.digitalwallet.repositories.AccountRepository;

import java.util.Optional;

public class DigitalWalletService {

    private final AccountRepository instance = AccountRepository.getInstance();

    private final Object depositLock = new Object();
    private final Object withdrawLock = new Object();
    private final Object balanceLock = new Object();

    public void withdraw (String accountId, double amount) throws InterruptedException {
        Optional<Account> accountExist = instance.getAccountByUUID(accountId);
        if (accountExist.isEmpty()) return;

        Account account = accountExist.get();
        synchronized (account) {
            while (account.insufficientFunds(amount)) {
                System.out.println(Thread.currentThread().getName() + " insufficientFunds - wait until new deposits");
                System.out.println("-".repeat(100));
                account.wait();
            }

            account.setBalance(account.getBalance() - amount);
            System.out.println(Thread.currentThread().getName()
                    + " Funds is enough, withdraw was success"
                    + " Balance updated " + account.getBalance());

            System.out.println("-".repeat(100));
        }
    }

    public void deposit (String accountId, double amount) {
        Optional<Account> accountExist = instance.getAccountByUUID(accountId);
        if (accountExist.isEmpty()) return;

        Account account = accountExist.get();
        synchronized (account) {
            account.setBalance(account.getBalance() + amount);
            System.out.println(
                    Thread.currentThread().getName() +
                    " new deposit - notify " + "Balance updated : "
                    + account.getBalance());

            System.out.println("-".repeat(100));
            account.notifyAll();
        }
    }

    public double getBalance(String accountId) {
        Optional<Account> accountExist = instance.getAccountByUUID(accountId);
        if (accountExist.isEmpty()) return 0.0;

        Account account = accountExist.get();
        synchronized (account) {
            return account.getBalance();
        }
    }

}
