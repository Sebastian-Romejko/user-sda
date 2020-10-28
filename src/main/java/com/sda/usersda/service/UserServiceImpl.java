package com.sda.usersda.service;

import com.sda.usersda.dao.UserDao;
import com.sda.usersda.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userDao.getAllUsers();
    }

    @Override
    public User getById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public void modifyUser(int userId, User user) {
        userDao.modify(userId, user);
    }

    @Override
    public List<User> getByFirstName(String firstName) throws SQLException {
        return getAll().stream().filter(x -> x.getFirstName().toLowerCase().contains(firstName.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<User> getByLastName(String lastName) throws SQLException {
        return getAll().stream().filter(x -> x.getLastName().toLowerCase().contains(lastName.toLowerCase())).collect(Collectors.toList());
    }
}
