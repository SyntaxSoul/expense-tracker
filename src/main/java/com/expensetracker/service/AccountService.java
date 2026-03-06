package com.expensetracker.service;

import com.expensetracker.dao.AccountDao;
import com.expensetracker.model.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AccountService {
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao){
        this.accountDao=accountDao;
    }

    public Account createAccount(Account account){
        if(account==null){
            throw new IllegalArgumentException("Account cannot be null");
        }
        if(account.getName()==null || account.getName().isBlank()){
            throw new IllegalArgumentException("Account name is required");
        }
        account.setBalance(BigDecimal.valueOf(0));
        account.setCreatedAt(LocalDateTime.now());

        try{
            accountDao.createAccount(account);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to create account");
        }
        return account;
    }

    public Account getAccountById(Long id){
        if(id == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        try{
            return accountDao.getAccountById(id);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to fetch account",e);
        }
    }

    public Account updateAccount(Account account){
        if(account==null){
            throw new IllegalArgumentException("Account cannot be null");
        }
        if(account.getId()==null){
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        if(account.getName()==null || account.getName().isBlank()){
            throw new IllegalArgumentException("Account name is required");
        }
        if(account.getBalance()==null){
            throw new IllegalArgumentException("Balance cannot be null");
        }
        try{
            accountDao.updateAccount(account);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to update account",e);
        }
        return account;
    }

    public Account deleteAccount(Long id){
        if(id==null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        Account account=getAccountById(id);
        if(account==null){
            throw new IllegalStateException("Failed to get account");
        }

        try{
            accountDao.updateAccount(account);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to delete account");
        }
        return account;
    }
}
