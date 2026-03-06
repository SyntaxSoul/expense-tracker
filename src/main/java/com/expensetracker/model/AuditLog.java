package com.expensetracker.model;

import java.time.LocalDateTime;

public class AuditLog {
    private Long id;
    private Long userId;
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

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public String getEntity() {
        return entity;
    }

    public Long getEntityId() {
        return entityId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
