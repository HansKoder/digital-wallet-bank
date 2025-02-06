package org.hans.digitalwallet.strategy;

import org.hans.digitalwallet.models.Credential;
import org.hans.digitalwallet.services.DigitalWalletService;

public class WithDrawStrategy implements TransactionStrategy{

    private final DigitalWalletService service;
    private final Credential credential;
    private final double amount;

    public WithDrawStrategy(DigitalWalletService service, Credential credential, double amount) {
        this.service = service;
        this.credential = credential;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            service.withdraw(credential, amount);
        }catch (Exception exception) {
            System.err.println("Transaction invalid " + exception.getMessage());
        }
    }
}
