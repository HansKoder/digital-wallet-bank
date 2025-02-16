package org.hans.digitalwallet.services;

import org.hans.digitalwallet.models.Account;
import org.hans.digitalwallet.utils.Sleep;

public class TransferFundService {

    public void transferFunds (Account fromAccount, Account toAccount, double amount) {

        synchronized (fromAccount) {
            System.out.println(Thread.currentThread().getName() + " acquired lock on from account");
            Sleep.delay(500L);
            synchronized (toAccount) {
                transfer(fromAccount, toAccount, amount);
            }
        }
    }

    private void transfer (Account fromAccount, Account toAccount, double amount)  {
        if (fromAccount.insufficientFunds(amount))
            throw new IllegalArgumentException("Insufficient funds");

        fromAccount.withDraw(amount);
        toAccount.deposit(amount);

        System.out.println("Transfer Thread " + Thread.currentThread().getName());
    }

}
