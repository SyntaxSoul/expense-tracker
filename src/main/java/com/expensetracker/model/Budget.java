package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Budget {
    private Long id;
    private Long categoryId;
    private Integer month;
    private Integer year;
    private BigDecimal limitAmount;
    private LocalDateTime createdAt;

    public Budget(Long id, long categoryId, int month, int year, BigDecimal limitAmount, LocalDateTime createdAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.month = month;
        this.year = year;
        this.limitAmount = limitAmount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount=limitAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
