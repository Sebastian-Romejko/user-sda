package com.sda.usersda.dao;

import com.sda.usersda.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;
    User getUserById(int userId);
    User addUser(User user);
    void removeUser(User user);
    void modify(int userId, User user);
    List<User> getByFirstName(String firstName);
    List<User> getByLastName(String lastName);
    List<User> getAgeBetween(int min, int max);
}
