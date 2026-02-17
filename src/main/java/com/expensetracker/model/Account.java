package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {
    private long id;
    private long userId;
    private String name;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public Account(long id, long userId, String name, BigDecimal balance, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public long getId() {
        return this.id;
    }

    public long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
