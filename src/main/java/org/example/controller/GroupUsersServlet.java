package org.example.controller;

import org.example.dao.GroupDao;
import org.example.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GroupUsersServlet", value = "/group")
public class GroupUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("id"));
        GroupDao groupDao = new GroupDao();
        UserDao userDao = new UserDao();
        request.setAttribute("users", userDao.findAllByGroupId(groupId));
        request.setAttribute("group", groupDao.read(groupId));
        getServletContext().getRequestDispatcher("/groupUsers.jsp")
                .forward(request, response);
    }


}
