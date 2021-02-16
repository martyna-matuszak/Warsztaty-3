package org.example.controller.adminPanel.group;

import org.example.dao.GroupDao;
import org.example.model.Group;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GroupsServlet", value = "/adminPanel/group")
public class GroupsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDao groupDao = new GroupDao();
        Group[] groups = groupDao.findAll();
        request.setAttribute("groups" , groups);

        getServletContext().getRequestDispatcher("/adminPanel/group/main.jsp")
                .forward(request, response);
    }

}
