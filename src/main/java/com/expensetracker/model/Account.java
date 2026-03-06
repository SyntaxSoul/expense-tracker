package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {
    private Long id;
    private Long userId;
    private String name;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public Account(Long id, Long userId, String name, BigDecimal balance, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
