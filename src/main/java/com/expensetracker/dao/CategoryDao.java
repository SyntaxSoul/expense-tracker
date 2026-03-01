package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public boolean createCategory(Category category) throws SQLException {
        String sql = "INSERT INTO categories(name,type,active) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, category.getName());
            ps.setString(2, category.getType());
            ps.setBoolean(3, category.getActive());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    category.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;
        }
    }

    public Category getCategoryById(long id) throws SQLException {
        String sql = "SELECT * FROM categories WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    Boolean active = rs.getBoolean("active");

                    return new Category(id, name, type, active);
                }
            }
            return null;
        }
    }

    public Category getCategoryByName(String name) throws SQLException {
        String sql = "SELECT * FROM categories WHERE name=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Long id = rs.getLong("id");
                    String type = rs.getString("type");
                    Boolean active = rs.getBoolean("active");

                    return new Category(id, name, type, active);
                }
            }
            return null;
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT * FROM categories";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            List<Category> categories = new ArrayList<>();

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    Boolean active = rs.getBoolean("active");

                    categories.add(new Category(id, name, type, active));
                }
            }
            return categories;
        }
    }

    public boolean updateCategory(Category category) throws SQLException {
        String sql = "UPDATE categories SET name=?, type=?, active=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category.getName());
            ps.setString(2, category.getType());
            ps.setBoolean(3, category.getActive());
            ps.setLong(4, category.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deleteCategory(Category category) throws SQLException {
        String sql = "UPDATE categories SET active=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, false);
            ps.setLong(2, category.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }
}
