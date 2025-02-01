package org.hans.digitalwallet.services;

public class DigitalWalletService {

    private double balance;

    private final Object depositLock = new Object();
    private final Object withdrawLock = new Object();
    private final Object balanceLock = new Object();

    public DigitalWalletService(double balance) {
        this.balance = balance;
    }

    public void deposit (double amount) {
        // At the same time != parallel - CPU Time Slicing
        synchronized (depositLock) {
            balance += amount;
        }
    }

    public void withdraw (double amount) {
        synchronized (withdrawLock) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds.");
            }
        }

    }

    public double getBalance() {
        synchronized (balanceLock) {
            return balance;
        }
    }

}
