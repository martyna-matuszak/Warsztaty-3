package org.example.controller.adminPanel.exercise;

import org.example.dao.ExerciseDao;
import org.example.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExercisesServlet", value = "/adminPanel/exercise")
public class ExercisesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise[] exercises = exerciseDao.findAll();
        request.setAttribute("exercises" , exercises);

        getServletContext().getRequestDispatcher("/adminPanel/exercise/main.jsp")
                .forward(request, response);
    }


}
