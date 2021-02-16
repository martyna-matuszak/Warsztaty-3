package org.example.controller.adminPanel.exercise;

import org.example.dao.ExerciseDao;
import org.example.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditExerciseServlet", value = "/adminPanel/exercise/edit")
public class EditExerciseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("id"));

        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = exerciseDao.read(exerciseId);

        request.setAttribute("exercise", exercise);
        request.setAttribute("action", "edit");

        getServletContext().getRequestDispatcher("/adminPanel/exercise/form.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();

        Exercise exercise = exerciseDao.read(Integer.parseInt(request.getParameter("id")));
        exercise.setTitle(request.getParameter("title"));
        exercise.setDescription(request.getParameter("description"));

        exerciseDao.update(exercise);

        response.sendRedirect("/adminPanel/exercise");
    }
}