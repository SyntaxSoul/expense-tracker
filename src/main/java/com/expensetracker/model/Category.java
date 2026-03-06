package com.expensetracker.model;

public class Category {
    private Long id;
    private String name;
    private CategoryType type;
    private Status status;

    public Category(Long id, String name, CategoryType type, Status status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryType getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
