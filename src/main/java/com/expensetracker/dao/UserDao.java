package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public boolean createUser(User user) throws SQLException {
        String sql = "INSERT INTO users(name,email,status,created_at) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getStatus());
            if (user.getCreatedAt() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(user.getCreatedAt()));
            } else {
                ps.setTimestamp(4, null);
            }

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String status = rs.getString("status");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    return new User(id, name, email, status, createdAt);
                }
            }
            return null;
        }
    }

    public User getUserById(long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    String status = rs.getString("status");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    return new User(id, name, email, status, createdAt);
                }
            }
            return null;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String status = rs.getString("status");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    users.add(new User(id, name, email, status, createdAt));
                }
            }
            return users;
        }
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET name=?,email=?,status=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getStatus());
            ps.setLong(4, user.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "UPDATE users SET status=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getStatus().name());
            ps.setLong(2, user.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }
}