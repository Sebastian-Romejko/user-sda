package com.sda.usersda.controller;

import com.sda.usersda.model.User;
import com.sda.usersda.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resources;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@Controller
public class TheUserController {

    private UserService userService;
    private MessageSource messageSource;

    public TheUserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public String index(Model model) throws SQLException {
        List<User> userList = userService.getAll();
        model.addAttribute("users", userList);
        //System.out.println(messageSource.getMessage("usr", null, Locale.ENGLISH));
        return "index";
    }

    @GetMapping("/thuser/id/{id}")
    public String addUser(@PathVariable int id, Model model) {
        User byId = userService.getById(id);
        model.addAttribute("user", byId);
        return "user";
    }

    @PostMapping("/")
    public String saveUser(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "add-user";
        } else {
            userService.addUser(user);
            return "redirect:/";
        }
    }

    @PostMapping("/modify")
    public String modify(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "modify-user";
        }
        userService.modifyUser(user.getUserId(), user);
        return "redirect:/";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.removeUser(userService.getById(id));
        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modifyUser(@RequestParam int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "modify-user";
    }
}
