package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.PaymentMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDao {

    public boolean createPaymentMethod(PaymentMethod paymentMethod) throws SQLException {
        String sql = "INSERT INTO payment_methods(name,details,active) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, paymentMethod.getName());
            ps.setString(2, paymentMethod.getDetails());
            ps.setBoolean(3, paymentMethod.getStatus());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    paymentMethod.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;
        }

    }


    public PaymentMethod getPaymentMethodById(long id) throws SQLException {
        String sql = "SELECT * FROM payment_methods WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String details = rs.getString("details");
                    boolean active = rs.getBoolean("active");

                    return new PaymentMethod(id, name, details, active);
                }
            }
            return null;
        }
    }

    public List<PaymentMethod> getAllPaymentMethods() throws SQLException {
        String sql = "SELECT * FROM payment_methods";
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String details = rs.getString("details");
                    boolean active = rs.getBoolean("active");

                    paymentMethods.add(new PaymentMethod(id, name, details, active));
                }
            }
            return paymentMethods;
        }
    }

    public boolean updatePaymentMethod(PaymentMethod paymentMethod) throws SQLException {
        String sql = "UPDATE payment_methods SET name=?, details=?, active=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paymentMethod.getName());
            ps.setString(2, paymentMethod.getDetails());
            ps.setBoolean(3, paymentMethod.getStatus());
            ps.setLong(4, paymentMethod.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deletePaymentMethod(PaymentMethod paymentMethod) throws SQLException {
        String sql = "UPDATE payment_methods SET active=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, paymentMethod.getStatus());
            ps.setLong(2, paymentMethod.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }
}
