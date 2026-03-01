package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.Account;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    public boolean createAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts(user_id,name,balance,created_at) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, account.getUserId());
            ps.setString(2, account.getName());
            ps.setBigDecimal(3, account.getBalance());
            if (account.getCreatedAt() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(account.getCreatedAt()));
            } else {
                ps.setTimestamp(4, null);
            }

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    account.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;
        }
    }

    public Account getAccountById(long id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long userId = rs.getLong("user_id");
                    String name = rs.getString("name");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    return new Account(id, userId, name, balance, createdAt);
                }
            }
            return null;
        }
    }

    public List<Account> getAllAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts";
        List<Account> accounts = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    long userId = rs.getLong("user_id");
                    String name = rs.getString("name");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    accounts.add(new Account(id, userId, name, balance, createdAt));
                }
            }
            return accounts;
        }
    }

    public boolean updateAccount(Account account) throws SQLException {
        String sql = "UPDATE accounts SET user_id=?, name=?, balance=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, account.getUserId());
            ps.setString(2, account.getName());
            ps.setBigDecimal(3, account.getBalance());
            ps.setLong(4, account.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

//**Need to add status column in accounts table**

//    public boolean deleteAccount(Account account) throws SQLException{
//        String sql="UPDATE TABLE accounts SET status=? WHERE id=?";
//
//        try(Connection con=DBConnection.getConnection();
//            PreparedStatement ps=con.prepareStatement(sql)){
//
//            ps.setString(1,account.getStatus());
//            ps.setLong(2,account.getId());
//
//            int rows=ps.executeUpdate();
//            return rows>0;
//        }
//    }
}
