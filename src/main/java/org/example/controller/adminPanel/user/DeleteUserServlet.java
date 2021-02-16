package org.example.controller.adminPanel.user;

import org.example.dao.UserDao;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", value = "/adminPanel/user/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        UserDao userDao = new UserDao();
        User user = userDao.read(id);

        request.setAttribute("action", "/adminPanel/user/delete");
        request.setAttribute("element", user);

        getServletContext().getRequestDispatcher("/adminPanel/delete.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");

        if(delete.equals("yes")) {
            UserDao userDao = new UserDao();
            int id = Integer.parseInt(request.getParameter("id"));
            userDao.delete(id);
        }
        response.sendRedirect("/adminPanel/user");
    }
}
