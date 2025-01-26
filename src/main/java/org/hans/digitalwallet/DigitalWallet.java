package org.hans.digitalwallet;

public class DigitalWallet {

    private double balance;

    public DigitalWallet(double balance) {
        this.balance = balance;
    }

    public void deposit (double amount) {
        synchronized (this) {
            balance += amount;
        }
        // System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
    }

    public void withdraw (double amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance -= amount;
                // System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds.");
            }
        }

    }

    public double getBalance() {
        synchronized (this) {
            return balance;
        }
    }

}
