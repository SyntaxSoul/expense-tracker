package com.expensetracker.dao;

import com.expensetracker.db.DBConnection;
import com.expensetracker.model.AuditLog;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditLogDao {

    public boolean createAuditLog(AuditLog auditLog) throws SQLException {
        String sql = "INSERT INTO audit_logs(user_id,action,entity,entity_id,created_at) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, auditLog.getUserId());
            ps.setString(2, auditLog.getAction());
            ps.setString(3, auditLog.getEntity());
            ps.setLong(4, auditLog.getEntityId());
            if (auditLog.getCreatedAt() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(auditLog.getCreatedAt()));
            } else {
                ps.setTimestamp(5, null);
            }

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    auditLog.setId(rs.getLong(1));
                    return true;
                }
            }
            return false;
        }

    }


    public AuditLog getAuditLogById(long id) throws SQLException {
        String sql = "SELECT * FROM audit_logs WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long userId = rs.getLong("user_id");
                    String action = rs.getString("action");
                    String entity = rs.getString("entity");
                    long entityId = rs.getLong("entity_id");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    return new AuditLog(id, userId, action, entity, entityId, createdAt);
                }
            }
            return null;
        }
    }

    public List<AuditLog> getAllAuditLogs() throws SQLException {
        String sql = "SELECT * FROM audit_logs";
        List<AuditLog> auditLogs = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    long userId = rs.getLong("user_id");
                    String action = rs.getString("action");
                    String entity = rs.getString("entity");
                    long entityId = rs.getLong("entity_id");
                    Timestamp ts = rs.getTimestamp("created_at");
                    LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

                    auditLogs.add(new AuditLog(id, userId, action, entity, entityId, createdAt));
                }
            }
            return auditLogs;
        }
    }


//    public boolean deleteAuditLog(AuditLog auditLog) throws SQLException {
//        String sql = "UPDATE  audit_log SET status=? WHERE id=?";
//
//        try (Connection con = DBConnection.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql)){
//
//            ps.setBoolean(1, auditLog.getActive());
//            ps.setLong(2, auditLog.getId());
//            int rows = ps.executeUpdate();
//            return rows > 0;
//        }
//    }
}
