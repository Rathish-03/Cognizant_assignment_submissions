package com.example.ex4;

import java.math.BigDecimal;

/**
 * A simple bank account class to demonstrate testing principles.
 */
public class Account {
    private String accountNumber;
    private BigDecimal balance;

    public Account(String accountNumber, BigDecimal initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be null or negative");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Deposits money into the account.
     * @param amount The amount to deposit. Must be positive.
     * @throws IllegalArgumentException if amount is not positive.
     */
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    /**
     * Withdraws money from the account.
     * @param amount The amount to withdraw. Must be positive.
     * @throws IllegalArgumentException if amount is not positive or if insufficient funds.
     */
    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }
}
