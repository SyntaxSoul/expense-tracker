package com.expensetracker.model;

public class PaymentMethod {
    private Long id;
    private String name;
    private String details;
    private Boolean active;

    public PaymentMethod(Long id, String name, String details, Boolean active) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
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
