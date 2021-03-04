package org.example.controller.userPanel;

import org.example.dao.UserDao;
import org.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginUser {

    private static boolean login (HttpServletRequest request, User user, String password){

        if(BCrypt.checkpw(password, user.getPassword())){
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getId());
            session.setAttribute("password", user.getPassword());
            return true;
        } else {
            return false;
        }
    }

    private static void redirectingAfterLogin(HttpServletRequest request, HttpServletResponse response,boolean loggedUser) throws IOException, ServletException {
        if(loggedUser){
            response.sendRedirect(request.getParameter("redirect"));
        } else {
            request.setAttribute("login", "Wrong username or password");

            request.getServletContext().getRequestDispatcher("/userPanel")
                    .forward(request, response);
        }

    }

    public static void loginByUsername (HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(username);
        redirectingAfterLogin(request,response,login(request,user,password));
    }

    public static boolean loginBySession (HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        try {
            int userId = (Integer) session.getAttribute("user");
            User user = userDao.read(userId);

            String password = (String) session.getAttribute("password");

            return password.equals(user.getPassword());
        } catch (NullPointerException e){
            return false;
        }

    }
}
