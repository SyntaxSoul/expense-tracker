package com.expensetracker.service;

import com.expensetracker.dao.UserDao;
import com.expensetracker.model.Status;
import com.expensetracker.model.User;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao=userDao;
    }
    //will get compile time error if dao is not passed as arg to constructor

    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        user.setStatus(Status.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());

        try {
            userDao.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create user", e);
        }
        return user;
    }

    public User getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id is required");
        }
        try {
            return userDao.getUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch user",e);
        }
    }

    public User getUserByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        try {
            return userDao.getUserByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch user",e);
        }
    }

    public User updateUser(User user) {
        if (user==null || user.getId()==null){
            throw new IllegalArgumentException("User id is required for update");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        try {
            userDao.updateUser(user);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update user", e);
        }
        return user;
    }

    public User deleteUser(Long id){
        if (id==null){
            throw new IllegalArgumentException("User id is required");
        }
        User user=getUserById(id);
        if (user==null){
            throw new IllegalStateException("User not found");
        }
        user.setStatus(Status.INACTIVE);
        try{
            userDao.updateUser(user);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to delete user");
        }
        return user;
    }

}


