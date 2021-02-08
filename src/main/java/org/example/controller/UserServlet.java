package org.example.controller;

import org.example.dao.SolutionDao;
import org.example.dao.UserDao;
import org.example.model.Solution;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDao();
        User user = userDao.read(userId);
        request.setAttribute("user", user);

        SolutionDao solutionDao = new SolutionDao();
        Solution[] solutions = solutionDao.findAllByUserId(user.getId());
        request.setAttribute("solutions", solutions);

        getServletContext().getRequestDispatcher("/user.jsp")
                .forward(request, response);
    }

}
