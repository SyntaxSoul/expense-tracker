package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Income {
    private Long id;
    private Long accountId;
    private String source;
    private BigDecimal amount;
    private LocalDate incomeDate;
    private LocalDateTime createdAt;

    public Income(Long id, long accountId, String source, BigDecimal amount, LocalDate incomeDate, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.source = source;
        this.amount = amount;
        this.incomeDate = incomeDate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
