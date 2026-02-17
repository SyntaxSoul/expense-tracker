package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expense {
    private long id;
    private long accountId;
    private long categoryId;
    private long paymentMethodId;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String description;
    private LocalDateTime createdAt;

    public Expense(long id, long accountId, long categoryId, long paymentMethodId, BigDecimal amount, LocalDate expenseDate, String description, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getAccountId() {
        return accountId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public long getPaymentMethodId() {
        return paymentMethodId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
