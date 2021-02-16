package org.example.controller.adminPanel.user;

import org.example.dao.UserDao;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsersServlet", value = "/adminPanel/user")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();
        request.setAttribute("users" , users);

        getServletContext().getRequestDispatcher("/adminPanel/user/main.jsp")
                .forward(request, response);
    }


}
