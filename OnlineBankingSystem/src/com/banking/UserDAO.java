package com.banking;

import java.sql.*;

public class UserDAO {

    // Register a new user
    public void registerUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO users (name, email, password, account_number, balance) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAccountNumber());
            ps.setDouble(5, user.getBalance());
            ps.executeUpdate();
            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Authenticate user during login
    public User authenticateUser(String email, String password) {
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("name"), email, password, rs.getString("account_number"), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Update user's balance
    public void updateBalance(User user) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE users SET balance = ? WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, user.getBalance());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

