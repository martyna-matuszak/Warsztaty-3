package org.example.controller.adminPanel.user;

import org.example.dao.GroupDao;
import org.example.dao.UserDao;
import org.example.model.Group;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddUserServlet", value = "/adminPanel/user/add")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "add");

        GroupDao groupDao = new GroupDao();
        Group[] allGroups = groupDao.findAll();
        request.setAttribute("groups", allGroups);

        getServletContext().getRequestDispatcher("/adminPanel/user/form.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.hashPassword(request.getParameter("password"));
        user.setUserGroupId(Integer.parseInt(request.getParameter("groupId")));

        UserDao userDao = new UserDao();
        userDao.create(user);

        response.sendRedirect("/adminPanel/user");
    }
}