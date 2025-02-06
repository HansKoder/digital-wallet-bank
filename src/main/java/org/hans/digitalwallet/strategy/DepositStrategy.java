package org.hans.digitalwallet.strategy;

import org.hans.digitalwallet.models.Credential;
import org.hans.digitalwallet.services.DigitalWalletService;

public class DepositStrategy implements TransactionStrategy{

    private final DigitalWalletService service;
    private final Credential credential;
    private final double amount;
    private final long delay;

    public DepositStrategy(DigitalWalletService service, Credential credential, double amount, long delay) {
        this.service = service;
        this.credential = credential;
        this.amount = amount;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            service.deposit(credential, amount);
        }catch (Exception exception) {
            System.err.println("Transaction invalid " + exception.getMessage());
        }
    }
}
