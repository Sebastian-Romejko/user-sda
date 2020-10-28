package com.sda.usersda.service;

import com.sda.usersda.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAll() throws SQLException;
    User getById(int userId);
    void removeUser(User user);
    User addUser(User user);
    void modifyUser(int userId, User user);
    List<User> getByFirstName(String firstName) throws SQLException;
    List<User> getByLastName(String lastName) throws SQLException;
}
