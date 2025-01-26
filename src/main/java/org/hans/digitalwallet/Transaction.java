package org.hans.digitalwallet;

record Transaction (
        DigitalWallet digitalWallet,
        double amount,
        Operation operation
) implements Runnable {

    @Override
    public void run() {
        switch (operation) {
            case DEPOSIT -> {
                digitalWallet.deposit(amount);
                // System.out.println("Amount " + digitalWallet.getBalance());
            }
            case WITHDRAW -> {
                digitalWallet.withdraw(amount);
                // System.out.println("Amount " + digitalWallet.getBalance());
            }
        }
    }

    public enum Operation {
        DEPOSIT,
        WITHDRAW
    }
}
