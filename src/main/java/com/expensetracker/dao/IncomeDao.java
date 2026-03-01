package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.Income;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IncomeDao {


    public boolean createIncome(Income income) throws SQLException {
        String sql = "INSERT INTO income(account_id,source,amount,income_date,created_at) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, income.getAccountId());
            ps.setString(2, income.getSource());
            ps.setBigDecimal(3, income.getAmount());
            if (income.getIncomeDate() != null) {
                ps.setDate(4, Date.valueOf(income.getIncomeDate()));
            } else {
                ps.setDate(4, null);
            }
            if (income.getCreatedAt() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(income.getCreatedAt()));
            } else {
                ps.setTimestamp(5, null);
            }

            int rows = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    income.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;
        }

    }


    public Income getIncomeById(long id) throws SQLException {
        String sql = "SELECT * FROM income WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long accountId = rs.getLong("account_id");
                    String source = rs.getString("source");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    Date date = rs.getDate("income_date");
                    LocalDate incomeDate = date != null ? date.toLocalDate() : null;
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    return new Income(id, accountId, source, amount, incomeDate, createdAt);
                }
            }
            return null;
        }
    }

    public List<Income> getAllIncomes() throws SQLException {
        String sql = "SELECT * FROM income";
        List<Income> incomes = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    long accountId = rs.getLong("account_id");
                    String source = rs.getString("source");
                    BigDecimal amount = rs.getBigDecimal("amount");
                    Date date = rs.getDate("income_date");
                    LocalDate incomeDate = date != null ? date.toLocalDate() : null;
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    incomes.add(new Income(id, accountId, source, amount, incomeDate, createdAt));
                }
            }
            return incomes;
        }
    }

    public boolean updateIncome(Income income) throws SQLException {
        String sql = "UPDATE income SET account_id=?, source=?, amount=?, income_date=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, income.getAccountId());
            ps.setString(2, income.getSource());
            ps.setBigDecimal(3, income.getAmount());
            if (income.getIncomeDate() != null) {
                ps.setDate(4, Date.valueOf(income.getIncomeDate()));
            } else {
                ps.setDate(4, null);
            }
            ps.setLong(5, income.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

//    public boolean deleteIncome(Income income) throws SQLException {
//        String sql = "UPDATE TABLE income SET status=? WHERE id=?";
//
//        try (Connection con = DBConnection.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql)){
//
//            ps.setBoolean(1, income.getActive());
//            ps.setLong(2, income.getId());
//            int rows = ps.executeUpdate();
//            return rows > 0;
//        }
//    }
}
