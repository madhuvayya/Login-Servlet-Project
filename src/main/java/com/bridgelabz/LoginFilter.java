package com.bridgelabz;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns={"/LoginServlet"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String USER_NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}";
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[^a-zA-Z0-9]{1}).{8,})";

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        PrintWriter out = response.getWriter();
        RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/login.html");

        String user = servletRequest.getParameter("user");
        String pwd = servletRequest.getParameter("pwd");

        Pattern namePattern = Pattern.compile(USER_NAME_PATTERN);
        Matcher nameMatcher = namePattern.matcher(user);
        boolean isNameValid = nameMatcher.matches();

        Pattern pwdPattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher pwdMatcher = pwdPattern.matcher(pwd);
        boolean isPwdValid = pwdMatcher.matches();

        if(!isNameValid){
            out.println("<font color=red>Enter valid user name(Starts with Capital letter & has minimum 3 characters.).</font>");
            requestDispatcher.include(request,response);
        } else if (!isPwdValid) {
            out.println("<font color=red>Enter valid password.</font>");
            requestDispatcher.include(request,response);
        } else {
            filterChain.doFilter(request,response);
        }
        out.close();
    }
}
