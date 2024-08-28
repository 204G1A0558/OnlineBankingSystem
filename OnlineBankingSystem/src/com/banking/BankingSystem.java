package com.banking;

import java.util.Scanner;

public class BankingSystem {

    static UserDAO userDAO = new UserDAO();
    static User currentUser;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Online Banking System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (choice == 1) {
                register(scanner);
            } else if (choice == 2) {
                login(scanner);
            } else {
                break;
            }

            if (currentUser != null) {
                showMainMenu(scanner);
            }
        }
    }

    // Register a new user
    public static void register(Scanner scanner) {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        System.out.println("Enter account number:");
        String accountNumber = scanner.nextLine();
        System.out.println("Enter initial balance:");
        double balance = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        User user = new User(name, email, password, accountNumber, balance);
        userDAO.registerUser(user);
    }

    // User login
    public static void login(Scanner scanner) {
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        currentUser = userDAO.authenticateUser(email, password);
        if (currentUser == null) {
            System.out.println("Invalid credentials.");
        } else {
            System.out.println("Login successful. Welcome " + currentUser.getName());
        }
    }

    // Main banking operations menu
    public static void showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (choice == 1) {
                deposit(scanner);
            } else if (choice == 2) {
                withdraw(scanner);
            } else if (choice == 3) {
                checkBalance();
            } else {
                currentUser = null;
                break;
            }
        }
    }

    // Deposit money
    public static void deposit(Scanner scanner) {
        System.out.println("Enter amount to deposit:");
        double amount = scanner.nextDouble();
        currentUser.setBalance(currentUser.getBalance() + amount);
        userDAO.updateBalance(currentUser);
        System.out.println("Deposit successful.");
    }

    // Withdraw money
    public static void withdraw(Scanner scanner) {
        System.out.println("Enter amount to withdraw:");
        double amount = scanner.nextDouble();
        if (amount > currentUser.getBalance()) {
            System.out.println("Insufficient balance.");
        } else {
            currentUser.setBalance(currentUser.getBalance() - amount);
            userDAO.updateBalance(currentUser);
            System.out.println("Withdrawal successful.");
        }
    }

    // Check balance
    public static void checkBalance() {
        System.out.println("Your current balance is: " + currentUser.getBalance());
    }
}

