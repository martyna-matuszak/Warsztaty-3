package org.example.controller.adminPanel.solution;

import org.example.dao.SolutionDao;
import org.example.model.Solution;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "SolutionGradeServlet", value = "/adminPanel/solution/grade")
public class SolutionGradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Solution solution = solutionDao.read(id);
        request.setAttribute("solution", solution);

        Double[] grades = {5.0,4.5,4.0,3.5,3.0,2.5,2.0};
        request.setAttribute("grades", grades);

        getServletContext().getRequestDispatcher("/adminPanel/solution/form.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Solution solution = solutionDao.read(id);
        double grade = Double.parseDouble(request.getParameter("grade"));
        solution.setGrade(grade);
        solution.setUpdated(String.valueOf(LocalDateTime.now()));
        solutionDao.update(solution);
        response.sendRedirect("/adminPanel/solution");
    }
}
