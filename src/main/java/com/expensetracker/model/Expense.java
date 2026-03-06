package com.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expense {
    private Long id;
    private Long accountId;
    private Long categoryId;
    private Long paymentMethodId;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String description;
    private LocalDateTime createdAt;

    public Expense(Long id, long accountId, long categoryId, long paymentMethodId, BigDecimal amount, LocalDate expenseDate, String description, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getPaymentMethodId() {
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

    public void setAmount(BigDecimal amount) {
        this.amount=amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
