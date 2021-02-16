package org.example.controller.adminPanel.exercise;

import org.example.dao.ExerciseDao;
import org.example.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddExerciseServlet", value = "/adminPanel/exercise/add")
public class AddExerciseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "add");
        getServletContext().getRequestDispatcher("/adminPanel/exercise/form.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exercise exercise = new Exercise();
        exercise.setTitle(request.getParameter("title"));
        exercise.setDescription(request.getParameter("description"));

        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);

        response.sendRedirect("/adminPanel/exercise");
    }
}