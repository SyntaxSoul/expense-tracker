package com.expensetracker.service;

import com.expensetracker.dao.PaymentMethodDao;
import com.expensetracker.model.PaymentMethod;
import com.expensetracker.model.Status;

import java.sql.SQLException;

public class PaymentMethodService {
    private final PaymentMethodDao paymentMethodDao;

    public PaymentMethodService(PaymentMethodDao paymentMethodDao) {
        this.paymentMethodDao = paymentMethodDao;
    }

    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment Method cannot be null");
        }

        if (paymentMethod.getName() == null || paymentMethod.getName().isBlank()) {
            throw new IllegalArgumentException("Payment method name is required");
        }
        if (paymentMethod.getDetails() == null || paymentMethod.getDetails().isBlank()) {
            throw new IllegalArgumentException("Details is required");
        }
        paymentMethod.setStatus(Status.ACTIVE);
        try {
            paymentMethodDao.createPaymentMethod(paymentMethod);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create payment method",e);
        }
        return paymentMethod;
    }

    public PaymentMethod getPaymentMethodById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        try {
            return paymentMethodDao.getPaymentMethodById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch payment method",e);
        }
    }

    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod){
        if (paymentMethod==null){
            throw new IllegalArgumentException("Payment Method cannot be null");
        }
        if (paymentMethod.getId()==null){
            throw new IllegalArgumentException("Payment method id is required");
        }
        if (paymentMethod.getName() == null || paymentMethod.getName().isBlank()) {
            throw new IllegalArgumentException("Payment method name is required");
        }
        if (paymentMethod.getDetails() == null || paymentMethod.getDetails().isBlank()) {
            throw new IllegalArgumentException("Details is required");
        }
        if (paymentMethod.getStatus()==null){
            throw new IllegalArgumentException("Status is required");
        }
        try {
            paymentMethodDao.updatePaymentMethod(paymentMethod);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update payment method",e);
        }
        return paymentMethod;
    }

    public PaymentMethod deletePaymentMethod(Long id){
        if (id==null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        PaymentMethod paymentMethod=getPaymentMethodById(id);
        if (paymentMethod==null){
            throw new IllegalStateException("Payment method not found");
        }
        paymentMethod.setStatus(Status.INACTIVE);
        try{
            paymentMethodDao.updatePaymentMethod(paymentMethod);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to delete payment method",e);
        }
        return paymentMethod;
    }
}
