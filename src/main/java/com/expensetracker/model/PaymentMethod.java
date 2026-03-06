package com.expensetracker.model;

public class PaymentMethod {
    private Long id;
    private String name;
    private String details;
    private Status status;

    public PaymentMethod(Long id, String name, String details, Status status) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
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
