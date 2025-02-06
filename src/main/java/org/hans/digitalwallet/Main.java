package org.hans.digitalwallet;

import org.hans.digitalwallet.exceptions.InsufficientFundsException;
import org.hans.digitalwallet.models.Credential;
import org.hans.digitalwallet.services.DigitalWalletService;

public class Main {

    private static void processTransactions () {
        DigitalWalletService service = new DigitalWalletService();
        Credential credential = new Credential("512", "doe", "123");

        Thread doeWithDraw = new Thread(() -> {
            try {
                service.withdraw(credential, 21.0);
            } catch (InterruptedException | InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        }, "doeWithDraw");

        Thread doeWithDraw2 = new Thread(() -> {
            try {
                service.withdraw(credential, 13.0);
            } catch (InterruptedException | InsufficientFundsException e) {
                throw new RuntimeException(e);
            }
        }, "doeWithdraw2");

        Thread doeDeposit = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            service.deposit(credential, 5.0);
        }, "doeDeposit");

        Thread doeDeposit2 = new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            service.deposit(credential, 20.0);
        }, "doeDeposit2");

        doeWithDraw.start();
        doeWithDraw2.start();

        doeDeposit.start();
        doeDeposit2.start();

        try {
            doeWithDraw.join();
            doeWithDraw2.join();
            doeDeposit.join();
            doeDeposit2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Doe Final Balance " + service.getBalance(credential));
    }

    public static void main(String[] args) {
        processTransactions();
    }

}
