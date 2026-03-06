package com.expensetracker.service;

import com.expensetracker.dao.AuditLogDao;
import com.expensetracker.model.AuditLog;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class AuditLogService {
    private final AuditLogDao auditLogDao;

    public AuditLogService(AuditLogDao auditLogDao){
        this.auditLogDao=auditLogDao;
    }

    public AuditLog createAuditLog(AuditLog auditLog){
        if (auditLog==null){
            throw new IllegalArgumentException("Audit log cannot be null");
        }
        if (auditLog.getUserId()==null){
            throw new IllegalArgumentException("User id is required");
        }
        if (auditLog.getAction()==null || auditLog.getAction().isBlank()){
            throw new IllegalArgumentException("Action is required");
        }
        if (auditLog.getEntity()==null || auditLog.getEntity().isBlank()){
            throw new IllegalArgumentException("Entity is required");
        }
        if (auditLog.getEntityId()==null){
            throw new IllegalArgumentException("Entity id is required");
        }
        auditLog.setCreatedAt(LocalDateTime.now());
        try{
            auditLogDao.createAuditLog(auditLog);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to create audit log",e);
        }
        return auditLog;
    }

    public AuditLog getAuditLogById(Long id){
        if (id==null){
            throw new IllegalArgumentException("Audit Log id cannot be null");
        }
        try{
            return auditLogDao.getAuditLogById(id);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to fetch audit log",e);
        }
    }
}
