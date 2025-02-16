package org.hans.digitalwallet.models;

import org.hans.digitalwallet.exceptions.InsufficientFundsException;

import java.security.InvalidParameterException;

public final class Account {

    private final String uuid;
    private final String user;
    private final String pass;
    private double balance;

    public Account(String uuid, String user, String pass, double balance) {
        this.uuid = uuid;
        this.user = user;
        this.pass = pass;
        this.balance = balance;
    }

    public boolean checkAccount (String user, String pass) {
        return this.user.equals(user) && this.pass.equals(pass);
    }

    public void withDraw (double amount) {
        if (amount <= 0) throw new InvalidParameterException("Amount must be greater than Zero (0)");

        if (insufficientFunds(amount)) throw new IllegalArgumentException("Insufficient Funds!");

        this.balance -= amount;
    }


    public void deposit (double amount) {
        if (amount <= 0) throw new InvalidParameterException("Amount must be greater than Zero (0)");

        this.balance += amount;
    }

    public boolean insufficientFunds (double amount) {
        return this.balance < amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
