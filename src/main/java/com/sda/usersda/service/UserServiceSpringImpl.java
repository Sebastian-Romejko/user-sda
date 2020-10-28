package com.sda.usersda.service;

import com.sda.usersda.dao.UserDaoSpring;
import com.sda.usersda.model.User;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class UserServiceSpringImpl implements UserService {

    UserDaoSpring userDaoSpring;

    public UserServiceSpringImpl(UserDaoSpring userDaoSpring) {
        this.userDaoSpring = userDaoSpring;
    }

    @Override
    public List<User> getAll() {
        return userDaoSpring.findAll();
    }

    @Override
    public User getById(int userId) {
        return userDaoSpring.findById(userId).orElse(new User());
    }

    @Override
    public void removeUser(User user) {
        userDaoSpring.delete(user);
    }

    @Override
    public User addUser(User user) {
        return userDaoSpring.save(user);
    }

    @Override
    @Transactional
    public void modifyUser(int userId, User user) {
        user.setUserId(userId);
        userDaoSpring.save(user);
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return userDaoSpring.getAllByFirstName(firstName);
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return userDaoSpring.getAllByLastName(lastName);
    }
}
