package org.example.controller.adminPanel;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(!LoginAdmin.loginBySession(req)){
            req.setAttribute("redirect", req.getRequestURL());
            req.setAttribute("loginMessage", "To enter this section please log in.");
            req.getServletContext().getRequestDispatcher("/adminPanel/admin.jsp")
                    .forward(request, response);
        };
        chain.doFilter(request, response);
    }
}
