package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.Budget;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BudgetDao {

    public boolean createBudget(Budget budget) throws SQLException {
        String sql = "INSERT INTO budgets(category_id,month,year,limit_amount,created_at) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, budget.getCategoryId());
            ps.setInt(2, budget.getMonth());
            ps.setInt(3, budget.getYear());
            ps.setBigDecimal(4, budget.getLimitAmount());
            if (budget.getCreatedAt() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(budget.getCreatedAt()));
            } else {
                ps.setTimestamp(5, null);
            }

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    budget.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;
        }

    }


    public Budget getBudgetById(long id) throws SQLException {
        String sql = "SELECT * FROM budgets WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long categoryId = rs.getLong("category_id");
                    int month = rs.getInt("month");
                    int year = rs.getInt("year");
                    BigDecimal limitAmount = rs.getBigDecimal("limit_amount");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    return new Budget(id, categoryId, month, year, limitAmount, createdAt);
                }
            }
            return null;
        }
    }

    public List<Budget> getAllBudgets() throws SQLException {
        String sql = "SELECT * FROM budgets";
        List<Budget> budgets = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    long categoryId = rs.getLong("category_id");
                    int month = rs.getInt("month");
                    int year = rs.getInt("year");
                    BigDecimal limitAmount = rs.getBigDecimal("limit_amount");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    budgets.add(new Budget(id, categoryId, month, year, limitAmount, createdAt));
                }
            }
            return budgets;

        }
    }

    public boolean updateBudget(Budget budget) throws SQLException {
        String sql = "UPDATE budgets SET category_id=?,month=?,year=?,limit_amount=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, budget.getCategoryId());
            ps.setInt(2, budget.getMonth());
            ps.setInt(3, budget.getYear());
            ps.setBigDecimal(4, budget.getLimitAmount());
            ps.setLong(5, budget.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

//    public boolean deleteBudget(Budget budget) throws SQLException {
//        String sql = "UPDATE TABLE budgets SET status=? WHERE id=?";
//
//        try (Connection con = DBConnection.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql)){
//
//            ps.setBoolean(1, budget.getActive());
//            ps.setLong(2, budget.getId());
//            int rows = ps.executeUpdate();
//            return rows > 0;
//        }
//    }
}

