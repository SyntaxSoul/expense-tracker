package com.expensetracker.service;

import com.expensetracker.dao.IncomeDao;
import com.expensetracker.model.Income;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class IncomeService {
    private final IncomeDao incomeDao;

    public IncomeService(IncomeDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    public Income createIncome(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }
        if (income.getAccountId() == null) {
            throw new IllegalArgumentException("Account id is required");
        }
        if (income.getSource() == null || income.getSource().isBlank()) {
            throw new IllegalArgumentException("Source is required");
        }
        if (income.getAmount() == null) {
            throw new IllegalArgumentException("Amount is required");
        }
        if (income.getIncomeDate() == null) {
            throw new IllegalArgumentException("Income date is required");
        }
        income.setCreatedAt(LocalDateTime.now());
        try {
            incomeDao.createIncome(income);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create income", e);
        }
        return income;
    }

    public Income getIncomeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Income id cannot be null");
        }
        try {
            return incomeDao.getIncomeById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch income", e);
        }

    }

    public Income updateIncome(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }
        if (income.getId() == null) {
            throw new IllegalArgumentException("Income id is required");
        }
        if (income.getAccountId() == null) {
            throw new IllegalArgumentException("Account id is required");
        }
        if (income.getSource() == null || income.getSource().isBlank()) {
            throw new IllegalArgumentException("Source is required");
        }
        if (income.getAmount() == null) {
            throw new IllegalArgumentException("Amount is required");
        }
        if (income.getIncomeDate() == null) {
            throw new IllegalArgumentException("Income date is required");
        }
        try {
            incomeDao.updateIncome(income);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update income", e);
        }
        return income;
    }

}
