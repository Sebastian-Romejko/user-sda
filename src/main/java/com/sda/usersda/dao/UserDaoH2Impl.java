package com.sda.usersda.dao;

import com.sda.usersda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev")
public class UserDaoH2Impl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    public UserDaoH2Impl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        //throw new NotYetImplementedException("Jeszcze nie zaimplementowana");
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    @Transactional
    public User addUser(User user) {
        entityManager.persist(user);
        return getUserById(user.getUserId());
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        User userToRemove = getUserById(user.getUserId());
        entityManager.remove(userToRemove);
    }

    @Override
    @Transactional
    public void modify(int userId, User user) {
        user.setUserId(userId);
        entityManager.persist(user);
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return entityManager.createQuery("select u from User u where lower(u.first_name) like '%" + firstName + "%'").getResultList();
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return entityManager.createQuery("select u from User u where lower(u.last_name) like '%" + lastName + "%'").getResultList();
    }

    @Override
    public List<User> getAgeBetween(int min, int max) {
        return getAllUsers().stream().filter(x -> {
            int years = Period.between(x.getBirthDate(), LocalDate.now()).getYears();
            return (years >= min && years <= max);
            }).collect(Collectors.toList());
    }
}
