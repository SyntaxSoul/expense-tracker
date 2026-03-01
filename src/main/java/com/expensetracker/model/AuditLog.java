package com.expensetracker.model;

import java.time.LocalDateTime;

public class AuditLog {
    private Long id;
    private long userId;
    private String action;
    private String entity;
    private long entityId;
    private LocalDateTime createdAt;

    public AuditLog(Long id, long userId, String action, String entity, long entityId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public String getEntity() {
        return entity;
    }

    public long getEntityId() {
        return entityId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
