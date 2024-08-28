package com.banking;

public class User {
    private String name;
    private String email;
    private String password;
    private String accountNumber;
    private double balance;

    public User(String name, String email, String password, String accountNumber, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }
}

