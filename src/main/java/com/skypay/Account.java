package com.skypay;

import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private int balance;
    private List<Transaction> transactions;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        deposit(amount, getCurrentDate());
    }

    @Override
    public void withdraw(int amount) {
        withdraw(amount, getCurrentDate());
    }

    @Override
    public void printStatement() {
        System.out.println("Date || Amount || Balance");
        // Parcourir la liste des transactions dans l'ordre inverse
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    public void deposit(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    public void withdraw(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    public int getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private String getCurrentDate() {
        return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private static class Transaction {
        private String date;
        private int amount;
        private int balance;

        public Transaction(String date, int amount, int balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return date + " || " + amount + " || " + balance;
        }
    }
}