package org.hans.digitalwallet;

import org.hans.digitalwallet.exceptions.InsufficientFundsException;
import org.hans.digitalwallet.models.Credential;
import org.hans.digitalwallet.services.DigitalWalletService;
import org.hans.digitalwallet.strategy.DepositStrategy;
import org.hans.digitalwallet.strategy.WithDrawStrategy;

public class Main {

    private static void processTransactions () {
        DigitalWalletService service = new DigitalWalletService();
        Credential credential = new Credential("512", "doe", "123");

        Thread doeWithDraw = new Thread(new WithDrawStrategy(service, credential, 20.0), "doe-withdraw-1");
        Thread doeWithDraw2 = new Thread(new WithDrawStrategy(service, credential, 13.0), "doe-withdraw-2");

        Thread doeDeposit = new Thread(new DepositStrategy(service, credential, 5.0, 1000), "doe-deposit-1");
        Thread doeDeposit2 = new Thread(new DepositStrategy(service, credential, 20.0, 2000), "doe-deposit-2");

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

        System.out.println("Final Balance " + service.getBalance(credential));
    }

    public static void main(String[] args) {
        processTransactions();
    }

}
