package org.example.controller.adminPanel.solution;

import org.example.dao.ExerciseDao;
import org.example.dao.SolutionDao;
import org.example.dao.UserDao;
import org.example.model.Exercise;
import org.example.model.Solution;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SolutionsServlet", value = "/adminPanel/solution")
public class SolutionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();
        request.setAttribute("users", users);

        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise[] exercises = exerciseDao.findAll();
        request.setAttribute("exercises", exercises);

        getServletContext().getRequestDispatcher("/adminPanel/solution/main.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        SolutionDao solutionDao = new SolutionDao();
        String range = request.getParameter("range");
        response.getWriter().println(range);

        if ("all".equals(range)){
            request.setAttribute("message", "All solutions:");
            forwardSolutionsList(request, response, solutionDao.findAll());

        } else if ("user".equals(range)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            request.setAttribute("message", "All solutions for user with ID " + userId);
            forwardSolutionsList(request, response, solutionDao.findAllByUserId(userId));

        } else if ("exercise".equals(range)){
            int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
            request.setAttribute("message", "All solutions to the exercise with ID " + exerciseId);
            forwardSolutionsList(request, response, solutionDao.findAllByExerciseId(exerciseId));

        } else {
            response.sendRedirect("/adminPanel/solution");
        }
    }

    private void forwardSolutionsList(HttpServletRequest request, HttpServletResponse response, Solution[] solutions) throws ServletException, IOException {
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/adminPanel/solution/list.jsp")
                .forward(request, response);
    }

}
