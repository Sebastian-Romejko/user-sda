package com.sda.usersda.controller.old;

import com.sda.usersda.model.User;
import com.sda.usersda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.LambdaUtil.getAll;

@WebServlet("/prettyusers")
public class UserServlet extends HttpServlet {
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        try {
            users = userService.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>");
        writer.println("Pretty users");
        writer.println("</title>");
        writer.println("</head>");
        writer.println("</body>");
        writer.println("<h1>Uzytkownicy</h1>");
        for(User user : users) {
            writer.println("<div>");
            writer.println("<br/>Uzytkownik o id " + user.getUserId() + ":<br/>");
            writer.println(user.getFirstName() + " | " + user.getLastName() + " | " + user.getBirthDate());
            writer.println("<div>");
        }
        writer.println("</body>");
        writer.println("</html>");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
