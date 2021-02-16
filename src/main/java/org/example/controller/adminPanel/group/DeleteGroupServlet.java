package org.example.controller.adminPanel.group;

import org.example.dao.GroupDao;
import org.example.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteGroupServlet", value = "/adminPanel/group/delete")
public class DeleteGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        GroupDao groupDao = new GroupDao();
        Group group = groupDao.read(id);

        request.setAttribute("action", "/adminPanel/group/delete");
        request.setAttribute("element", group);

        getServletContext().getRequestDispatcher("/adminPanel/delete.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");

        if(delete.equals("yes")) {
            GroupDao groupDao = new GroupDao();
            int id = Integer.parseInt(request.getParameter("id"));
            groupDao.delete(id);
        }
        response.sendRedirect("/adminPanel/group");
    }
}
