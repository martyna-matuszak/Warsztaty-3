package org.example.controller.userPanel;

import org.example.dao.ExerciseDao;
import org.example.dao.GroupDao;
import org.example.dao.SolutionDao;
import org.example.dao.UserDao;
import org.example.model.Solution;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserDetailsServlet", value = "/userPanel/details")
public class UserDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("user");
        UserDao userDao = new UserDao();
        User user = userDao.read(userId);
        request.setAttribute("user", user);

        GroupDao groupDao = new GroupDao();
        request.setAttribute("group", groupDao.read(user.getUserGroupId()));

        SolutionDao solutionDao = new SolutionDao();
        Solution[] solutions = solutionDao.findAllByUserId(userId);
//        request.setAttribute("solutions", solutions);

        String[][] solutionsWithExerciseDetails = solutionDao.getSolutionsWithExerciseDetails(solutions);
        request.setAttribute("solutionsExercises", solutionsWithExerciseDetails);

        ExerciseDao exerciseDao = new ExerciseDao();
        request.setAttribute("exercises", exerciseDao.allUnsolvedExercisesByUser(userId));

        getServletContext().getRequestDispatcher("/userPanel/userDetails.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



}
