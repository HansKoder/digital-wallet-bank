package org.hans.digitalwallet.entities;

public class Account {

    private final String uuid;
    private String user;
    private String pass;
    private double balance;

    public Account(String uuid, String user, String pass, double balance) {
        this.uuid = uuid;
        this.user = user;
        this.pass = pass;
        this.balance = balance;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uuid='" + uuid + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", balance=" + balance +
                '}';
    }
}
