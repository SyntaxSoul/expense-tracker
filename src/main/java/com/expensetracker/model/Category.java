package com.expensetracker.model;

public class Category {
    private Long id;
    private String name;
    private String type;
    private Boolean active;

    public Category(Long id, String name, String type, Boolean active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        this.active=active;
    }
}
