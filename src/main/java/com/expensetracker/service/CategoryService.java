package com.expensetracker.service;

import com.expensetracker.dao.CategoryDao;
import com.expensetracker.model.Category;
import com.expensetracker.model.Status;

import java.sql.SQLException;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao){
        this.categoryDao=categoryDao;
    }

    public Category createCategory(Category category){
        if (category==null){
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.getName()==null || category.getName().isBlank()){
            throw new IllegalArgumentException("Category name is required");
        }
        if (category.getType()==null){
            throw new IllegalArgumentException("Category type is required");
        }
        category.setStatus(Status.ACTIVE);
        try{
            categoryDao.createCategory(category);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to create category");
        }
        return category;
    }

    public Category getCategoryById(Long id){
        if (id==null){
            throw new IllegalArgumentException("Category id cannot be null");
        }
        try{
            return categoryDao.getCategoryById(id);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to fetch category");
        }
    }

    public Category updateCategory(Category category){
        if (category==null){
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.getId()==null){
            throw new IllegalArgumentException("Category id is required");
        }
        if (category.getName()==null || category.getName().isBlank()){
            throw new IllegalArgumentException("Category name is required");
        }
        if (category.getType()==null){
            throw new IllegalArgumentException("Category type is required");
        }
        if (category.getStatus()==null){
            throw new IllegalArgumentException("Category status is required");
        }
        try{
            categoryDao.updateCategory(category);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to update category");
        }
        return category;
    }

    public Category deleteCategory(Long id){
        if (id==null){
            throw new IllegalArgumentException("Category id cannot be null");
        }
        Category category=getCategoryById(id);
        if (category==null){
            throw new IllegalStateException("Failed to fetch category");
        }
        category.setStatus(Status.INACTIVE);
        try{
            categoryDao.updateCategory(category);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to delete category",e);
        }
        return category;
    }
}
