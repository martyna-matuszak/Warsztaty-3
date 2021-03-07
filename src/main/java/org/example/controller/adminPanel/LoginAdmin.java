package org.example.controller.adminPanel;

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

public class LoginAdmin {

    private static boolean login (HttpServletRequest request, User admin, String password){

        if(BCrypt.checkpw(password, admin.getPassword()) && admin.getRole().equals("admin")){
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin.getId());
            session.setAttribute("adminPassword", admin.getPassword());
            return true;
        } else {
            return false;
        }
    }

    private static void redirectingAfterLoginAdmin(HttpServletRequest request, HttpServletResponse response,boolean loggedAdmin) throws IOException, ServletException {
        if(loggedAdmin){
            response.sendRedirect(request.getParameter("redirect"));
        } else {
            request.setAttribute("loginMessage", "Wrong username or password");

            request.getServletContext().getRequestDispatcher("/adminPanel/admin.jsp")
                    .forward(request, response);
        }

    }

    public static void loginByUsername (HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException, ServletException {
        UserDao userDao = new UserDao();
        Optional<User> adminOptional = Optional.ofNullable(userDao.findByUsername(username));
        AtomicBoolean login = new AtomicBoolean(false);
        adminOptional.ifPresent(admin -> login.set(login(request, admin, password)));
        redirectingAfterLoginAdmin(request,response, login.get());
    }

    public static boolean loginBySession (HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        try {
            int adminId = (Integer) session.getAttribute("admin");
            User admin = userDao.read(adminId);

            String password = (String) session.getAttribute("adminPassword");

            return password.equals(admin.getPassword());
        } catch (NullPointerException e){
            return false;
        }

    }
}
