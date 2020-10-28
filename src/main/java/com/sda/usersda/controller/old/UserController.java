package com.sda.usersda.controller.old;

import com.sda.usersda.model.User;
import com.sda.usersda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @RequestMapping("/")
    public String hello() {
        return "Hello world!";
    }

    @RequestMapping("/users")
    public List<User> users() throws SQLException {
        System.out.println("Pobieranie użytkowników");
        return userService.getAll();
    }

    @GetMapping("/user/id/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping("/user/firstName")
    public List<User> getByFirstName(@RequestParam String firstName) throws SQLException {
        return userService.getByFirstName(firstName);
    }

    @GetMapping("/user/lastName")
    public List<User> getByLastName(@RequestParam String lastName) throws SQLException {
        return userService.getByLastName(lastName);
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = userService.getById(id);
        userService.removeUser(user);
        return "Skasowano użytkownika: " + user.toString();
    }

    @PostMapping("/users/")
    public String addUser(@RequestBody User user) {
           User newUser = userService.addUser(user);
           return "Dodano użytownika: " + newUser.toString();
    }

    @PutMapping("user/modify")
    public String modifyUser(@RequestParam int id, @RequestBody User user) {
        userService.modifyUser(id, user);
        return "Zmodyfikowano dane użytkownika: " + user.toString();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
