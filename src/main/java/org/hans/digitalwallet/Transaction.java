package org.hans.digitalwallet;

record Transaction (
        DigitalWallet digitalWallet,
        double amount,
        Operation operation
) implements Runnable {

    @Override
    public void run() {
        switch (operation) {
            case DEPOSIT -> digitalWallet.deposit(amount);
            case WITHDRAW -> digitalWallet.withdraw(amount);
        }
    }

    public enum Operation {
        DEPOSIT,
        WITHDRAW
    }
}
