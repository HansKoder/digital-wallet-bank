package org.hans.digitalwallet.models;

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
