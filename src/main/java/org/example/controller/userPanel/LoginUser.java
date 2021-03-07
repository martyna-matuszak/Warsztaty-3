package org.example.controller.userPanel;

import org.example.dao.UserDao;
import org.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
            request.setAttribute("loginMessage", "Wrong username or password. Try again.");

            request.getServletContext().getRequestDispatcher("/userPanel/userHome.jsp")
                    .forward(request, response);
        }

    }

    public static void loginByUsername (HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        Optional<User> userOptional = Optional.ofNullable(userDao.findByUsername(username));
        AtomicBoolean login = new AtomicBoolean(false);
        userOptional.ifPresent(user -> login.set(login(request, user, password)));
        redirectingAfterLogin(request,response,login.get());
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
