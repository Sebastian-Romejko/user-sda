package com.sda.usersda.dao;

import com.sda.usersda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDaoSpring extends JpaRepository<User, Integer> {
    List<User> getAllByFirstName(String name);
    List<User> getAllByLastName(String name);
    @Query(name = "dateBetween")
    List<User> getByAge(@Param("name") String param);
}
