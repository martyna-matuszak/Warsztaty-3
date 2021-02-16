package org.example.controller.adminPanel.group;

import org.example.dao.GroupDao;
import org.example.model.Group;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddGroupServlet", value = "/adminPanel/group/add")
public class AddGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "add");
        getServletContext().getRequestDispatcher("/adminPanel/group/form.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Group group = new Group();
        group.setName(request.getParameter("name"));

        GroupDao groupDao = new GroupDao();
        groupDao.create(group);

        response.sendRedirect("/adminPanel/group");
    }
}