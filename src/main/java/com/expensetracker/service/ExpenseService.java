package com.expensetracker.service;

import com.expensetracker.dao.ExpenseDao;
import com.expensetracker.model.Expense;
import com.expensetracker.model.Income;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class ExpenseService {
    private final ExpenseDao expenseDao;

    public ExpenseService(ExpenseDao expenseDao){
        this.expenseDao=expenseDao;
    }

    public Expense createExpense(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }
        if (expense.getAccountId() == null) {
            throw new IllegalArgumentException("Account id is required");
        }
        if (expense.getCategoryId() == null) {
            throw new IllegalArgumentException("Category id is required");
        }
        if (expense.getPaymentMethodId() == null) {
            throw new IllegalArgumentException("Payment method id is required");
        }
        if (expense.getAmount()==null){
            throw new IllegalArgumentException("Amount is required");
        }
        if (expense.getExpenseDate() == null) {
            throw new IllegalArgumentException("Expense date is required");
        }
        if (expense.getDescription()==null || expense.getDescription().isBlank()){
            throw new IllegalArgumentException("Description is required");
        }
        expense.setCreatedAt(LocalDateTime.now());
        try {
            expenseDao.createExpense(expense);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create expense", e);
        }
        return expense;
    }

    public Expense getExpenseById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Expense id cannot be null");
        }
        try {
            return expenseDao.getExpenseById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch expense", e);
        }

    }

    public Expense updateExpense(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }
        if (expense.getId() == null) {
            throw new IllegalArgumentException("Expense id is required");
        }
        if (expense.getAccountId() == null) {
            throw new IllegalArgumentException("Account id is required");
        }

        if (expense.getCategoryId() == null) {
            throw new IllegalArgumentException("Category id is required");
        }
        if (expense.getPaymentMethodId()==null){
            throw new IllegalArgumentException("Payment method id is required");
        }
        if (expense.getAmount() == null) {
            throw new IllegalArgumentException("Amount is required");
        }
        if (expense.getDescription() == null || expense.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        try {
            expenseDao.updateExpense(expense);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update expense", e);
        }
        return expense;
    }

}
