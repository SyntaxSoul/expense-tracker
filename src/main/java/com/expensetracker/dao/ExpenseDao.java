package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.Expense;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDao {

    public boolean createExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses(account_id,category_id,payment_method_id,amount,expense_date,description,created_at) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, expense.getAccountId());
            ps.setLong(2, expense.getCategoryId());
            ps.setLong(3, expense.getPaymentMethodId());
            ps.setBigDecimal(4, expense.getAmount());
            if (expense.getExpenseDate() != null) {
                ps.setDate(5, Date.valueOf(expense.getExpenseDate()));
            } else {
                ps.setDate(5, null);
            }
            ps.setString(6, expense.getDescription());
            if (expense.getCreatedAt() != null) {
                ps.setTimestamp(7, Timestamp.valueOf(expense.getCreatedAt()));
            } else {
                ps.setTimestamp(7, null);
            }

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    expense.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;

        }

    }


    public Expense getExpenseById(long id) throws SQLException {
        String sql = "SELECT * FROM expenses WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long accountId = rs.getLong("account_id");
                    long categoryId = rs.getLong("category_id");
                    long paymentMethodId = rs.getLong("payment_method_id");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    Date date = rs.getDate("expense_date");
                    LocalDate expenseDate = date != null ? date.toLocalDate() : null;
                    String description = rs.getString("description");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    return new Expense(id, accountId, categoryId, paymentMethodId, amount, expenseDate, description, createdAt);
                }
            }
            return null;
        }
    }

    public List<Expense> getAllExpenses() throws SQLException {
        String sql = "SELECT * FROM expenses";
        List<Expense> expenses = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    long accountId = rs.getLong("account_id");
                    long categoryId = rs.getLong("category_id");
                    long paymentMethodId = rs.getLong("payment_method_id");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    Date date = rs.getDate("expense_date");
                    LocalDate expenseDate = date != null ? date.toLocalDate() : null;
                    String description = rs.getString("description");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    expenses.add(new Expense(id, accountId, categoryId, paymentMethodId, amount, expenseDate, description, createdAt));
                }
            }
            return expenses;
        }
    }

    public boolean updateExpense(Expense expense) throws SQLException {
        String sql = "UPDATE expenses SET account_id=?, category_id=?, payment_method_id=?, amount=?, expense_date=?, description=?  WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, expense.getAccountId());
            ps.setLong(2, expense.getCategoryId());
            ps.setLong(3, expense.getPaymentMethodId());
            ps.setBigDecimal(4, expense.getAmount());
            if (expense.getExpenseDate() != null) {
                ps.setDate(5, Date.valueOf(expense.getExpenseDate()));
            } else {
                ps.setDate(5, null);
            }
            ps.setString(6, expense.getDescription());
            ps.setLong(7, expense.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

//    public boolean deleteExpense(Expense expense) throws SQLException {
//        String sql = "UPDATE TABLE expenses SET status=? WHERE id=?";
//
//        try (Connection con = DBConnection.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql)){
//
//            ps.setBoolean(1, expense.getActive());
//            ps.setLong(2, expense.getId());
//            int rows = ps.executeUpdate();
//            return rows > 0;
//        }
//    }
}
