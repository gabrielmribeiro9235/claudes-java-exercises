package br.com.exercises.bank;

import java.util.Locale;

public class Main {
    static void main() {
        BankAccount bankAccount = new BankAccount("12345", "Jonny");

        System.out.println("Initial state:");
        System.out.println("Holder: " + bankAccount.getHolder());
        System.out.println("Account number: " + bankAccount.getNumber());
        System.out.printf(Locale.US, "Balance: US$ %,.2f%n", bankAccount.getBalance());
        System.out.println("-------------------------------------------");

        bankAccount.deposit(1000);
        System.out.println("US$ 1,000.00 has been deposited");

        System.out.println("-------------------------------------------");
        System.out.println("Current state:");
        System.out.println("Holder: " + bankAccount.getHolder());
        System.out.println("Account number: " + bankAccount.getNumber());
        System.out.printf(Locale.US, "Balance: US$ %,.2f%n", bankAccount.getBalance());
        System.out.println("-------------------------------------------");

        System.out.println("Trying to withdraw US$ 500.00...\n");
        boolean withdraw = bankAccount.withdraw(500);
        System.out.println(withdraw ? "US$ 500.00 has been withdrawn" : "Failed to withdraw US$ 500.00");

        System.out.println("-------------------------------------------");
        System.out.println("Current state:");
        System.out.println("Holder: " + bankAccount.getHolder());
        System.out.println("Account number: " + bankAccount.getNumber());
        System.out.printf(Locale.US, "Balance: US$ %,.2f%n", bankAccount.getBalance());
        System.out.println("-------------------------------------------");

        System.out.println("Trying to withdraw US$ 2,000.00...\n");
        withdraw = bankAccount.withdraw(2000);
        System.out.println(withdraw ? "US$ 2,000.00 has been withdrawn" : "Failed to withdraw US$ 2,000.00");

        System.out.println("-------------------------------------------");
        System.out.println("Current state:");
        System.out.println("Holder: " + bankAccount.getHolder());
        System.out.println("Account number: " + bankAccount.getNumber());
        System.out.printf(Locale.US, "Balance: US$ %,.2f%n", bankAccount.getBalance());
        System.out.println("-------------------------------------------");
    }
}
