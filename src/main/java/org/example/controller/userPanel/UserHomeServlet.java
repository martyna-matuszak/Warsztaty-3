package org.example.controller.userPanel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserHomeServlet", value = "/userPanel")
public class UserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(LoginUser.loginBySession(request)){
            response.sendRedirect("/userPanel/details");
        } else {
            request.setAttribute("loginMessage", "To continue please login.");
            getServletContext().getRequestDispatcher("/userPanel/userHome.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginUser.loginByUsername(request,response,username,password);

    }
}
