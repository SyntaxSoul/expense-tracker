package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Income {
    private long id;
    private long accountId;
    private String source;
    private BigDecimal amount;
    private LocalDate incomeDate;
    private LocalDateTime createdAt;

    public Income(long id, long accountId, String source, BigDecimal amount, LocalDate incomeDate, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.source = source;
        this.amount = amount;
        this.incomeDate = incomeDate;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getSource() {
        return source;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
