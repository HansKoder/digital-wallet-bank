package org.hans.digitalwallet.services;

import org.hans.digitalwallet.exceptions.InsufficientFundsException;
import org.hans.digitalwallet.exceptions.InvalidTransactionException;
import org.hans.digitalwallet.models.Account;
import org.hans.digitalwallet.models.Credential;
import org.hans.digitalwallet.repositories.AccountRepository;

import java.util.Optional;

public class DigitalWalletService {

    private final AccountRepository instance = AccountRepository.getInstance();

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

        synchronized (account) {
            while (account.insufficientFunds(amount)) {
                System.out.println(Thread.currentThread().getName() + " insufficientFunds - wait until new deposits");
                System.out.println("-".repeat(100));
                account.wait();
            }

            account.withDraw(amount);
            System.out.println(Thread.currentThread().getName()
                    + " Funds is enough, withdraw was success"
                    + " Balance updated " + account.getBalance());

            System.out.println("-".repeat(100));
        }
    }

    public void deposit (Credential credential, double amount) {
        Account account = validateAccount(credential);
        synchronized (account) {
            account.deposit(amount);
            System.out.println(
                    Thread.currentThread().getName() +
                    " new deposit - notify " + "Balance updated : "
                    + account.getBalance());

            System.out.println("-".repeat(100));
            account.notifyAll();
        }
    }

    public double getBalance(Credential credential) {
        Account account = validateAccount(credential);
        synchronized (account) {
            return account.getBalance();
        }
    }

}
