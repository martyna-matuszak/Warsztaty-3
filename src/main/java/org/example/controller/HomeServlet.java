package org.example.controller;


import org.example.dao.SolutionDao;
import org.example.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        int limit = Integer.parseInt(getServletConfig().getServletContext().getInitParameter("number-solutions"));
        Solution[] solutions = solutionDao.findRecent(limit);
        request.setAttribute("solutions" , solutions);

        getServletContext().getRequestDispatcher("/homepage.jsp")
                .forward(request, response);
    }

}
