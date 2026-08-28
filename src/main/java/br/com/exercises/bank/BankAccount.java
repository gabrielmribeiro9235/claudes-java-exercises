package br.com.exercises.bank;

public class BankAccount {
    private final String number;
    private final String holder;
    private double balance;

    public BankAccount(String number, String holder) {
        this.number = number;
        this.holder = holder;
    }

    public String getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double value) {
        if (value <= 0) return;

        balance += value;
    }

    public boolean withdraw(double value) {
        if (value <= 0 || value > balance) return false;

        balance -= value;

        return true;
    }
}
