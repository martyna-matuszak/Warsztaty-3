package org.example.controller.userPanel;

import org.example.dao.ExerciseDao;
import org.example.dao.SolutionDao;
import org.example.model.Exercise;
import org.example.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "UserAddSolutionServlet", value = "/userPanel/addSolution")
public class UserAddSolutionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("id"));
        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = exerciseDao.read(exerciseId);
        request.setAttribute("exercise", exercise);

        getServletContext().getRequestDispatcher("/userPanel/solutionForm.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Solution solution = new Solution();

        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
        solution.setExerciseId(exerciseId);

        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("user");
        solution.setUserId(userId);

        solution.setDescription(request.getParameter("description"));
        solution.setCreated(String.valueOf(LocalDateTime.now()));

        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);

        response.sendRedirect("/userPanel/details");

    }
}
