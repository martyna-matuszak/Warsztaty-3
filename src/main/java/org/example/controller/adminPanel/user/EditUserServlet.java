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

@WebServlet(name = "EditUserServlet", value = "/adminPanel/user/edit")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        UserDao userDao = new UserDao();
        User user = userDao.read(userId);
        request.setAttribute("user", user);

        GroupDao groupDao = new GroupDao();
        Group[] allGroups = groupDao.findAll();
        request.setAttribute("groups", allGroups);

        request.setAttribute("action", "edit");

        getServletContext().getRequestDispatcher("/adminPanel/user/form.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();

        User user = userDao.read(Integer.parseInt(request.getParameter("id")));
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.hashPassword(request.getParameter("password"));
        user.setUserGroupId(Integer.parseInt(request.getParameter("groupId")));

        userDao.update(user);

        response.sendRedirect("/adminPanel/user");
    }
}