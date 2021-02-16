package org.example.controller.adminPanel.exercise;

import org.example.dao.ExerciseDao;
import org.example.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteExerciseServlet", value = "/adminPanel/exercise/delete")
public class DeleteExerciseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = exerciseDao.read(id);

        request.setAttribute("action", "/adminPanel/exercise/delete");
        request.setAttribute("element", exercise);

        getServletContext().getRequestDispatcher("/adminPanel/delete.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");

        if(delete.equals("yes")) {
            ExerciseDao exerciseDao = new ExerciseDao();
            int id = Integer.parseInt(request.getParameter("id"));
            exerciseDao.delete(id);
        }
        response.sendRedirect("/adminPanel/exercise");
    }
}
