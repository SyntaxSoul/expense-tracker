package com.expensetracker.service;

import com.expensetracker.dao.BudgetDao;
import com.expensetracker.model.Budget;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class BudgetService {
    private final BudgetDao budgetDao;

    public BudgetService(BudgetDao budgetDao){
        this.budgetDao=budgetDao;
    }

    public Budget createBudget(Budget budget){
        if (budget==null){
            throw new IllegalArgumentException("Budget cannot be null");
        }
        if (budget.getCategoryId()==null){
            throw new IllegalArgumentException("Category id is required");
        }
        if (budget.getMonth()==null){
           throw new IllegalArgumentException("Month is required");
        }
        if (budget.getYear()==null){
            throw new IllegalArgumentException("Year is required");
        }
        if (budget.getLimitAmount()==null){
            throw new IllegalArgumentException("Budget limit is required");
        }
        budget.setCreatedAt(LocalDateTime.now());
        try{
            budgetDao.createBudget(budget);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to create budget",e);
        }
        return budget;
    }

    public Budget getBudgetById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        try {
            return budgetDao.getBudgetById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch budget",e);
        }
    }

    public Budget updateBudget(Budget budget){
        if (budget==null){
            throw new IllegalArgumentException("Budget cannot be null");
        }
        if (budget.getId()==null){
            throw new IllegalArgumentException("Budget id is required");
        }
        if (budget.getCategoryId()==null){
            throw new IllegalArgumentException("Category id is required");
        }
        if (budget.getMonth()==null){
            throw new IllegalArgumentException("Month is required");
        }
        if (budget.getYear()==null){
            throw new IllegalArgumentException("Year is required");
        }
        if (budget.getLimitAmount()==null){
            throw new IllegalArgumentException("Limit amount is required");
        }
        try{
            budgetDao.updateBudget(budget);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to update budget",e);
        }
        return budget;
    }

}
