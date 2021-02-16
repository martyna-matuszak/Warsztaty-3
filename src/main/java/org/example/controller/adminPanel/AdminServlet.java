package org.example.controller.adminPanel;

import org.example.dao.SolutionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/panelAdmin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        int limit = Integer.parseInt(getServletConfig().getServletContext().getInitParameter("number-solutions"));
        request.setAttribute("solutions", solutionDao.findRecent(limit));
        getServletContext().getRequestDispatcher("/adminPanel/admin.jsp")
                .forward(request, response);
    }

}
