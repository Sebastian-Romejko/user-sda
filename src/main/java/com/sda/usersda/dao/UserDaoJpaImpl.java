package com.sda.usersda.dao;

import com.sda.usersda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.ConnectionEvent;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("jdbc")
public class UserDaoJpaImpl implements UserDao {

    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String query = "select * from users";
        return jdbc.query(query, (rs, i) -> {
            User user = new User();
            user.setUserId(rs.getInt("id"));
            return user;
        });
    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void modify(int userId, User user) {

    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return null;
    }

    @Override
    public List<User> getAgeBetween(int min, int max) {
        return null;
    }
}
