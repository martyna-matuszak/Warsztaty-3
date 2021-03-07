package org.example.controller.adminPanel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(LoginAdmin.loginBySession(request)){
            response.sendRedirect("/adminPanel/user");
        } else {
            request.setAttribute("loginMessage", "To continue please login as admin.");
            getServletContext().getRequestDispatcher("/adminPanel/admin.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginAdmin.loginByUsername(request,response,username,password);

    }

}
