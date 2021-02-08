package org.example.controller;

import org.example.dao.ExerciseDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ExerciseServlet", value = "/exercise")
public class ExerciseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("id"));
        ExerciseDao exerciseDao = new ExerciseDao();
        request.setAttribute("exercise", exerciseDao.read(exerciseId));
        getServletContext().getRequestDispatcher("/exercise.jsp")
                .forward(request, response);
    }

}
