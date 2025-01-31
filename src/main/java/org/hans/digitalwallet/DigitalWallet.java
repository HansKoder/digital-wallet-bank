package org.hans.digitalwallet;

public class DigitalWallet {

    private double balance;

    private final Object depositLock = new Object();
    private final Object withdrawLock = new Object();
    private final Object balanceLock = new Object();

    public DigitalWallet(double balance) {
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
