package com.bridgelabz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet",
        urlPatterns = {"/LoginServlet"},
        initParams ={
                @WebInitParam(name="user-id",value = "Madhu"),
                @WebInitParam(name="password", value= "M@dhu123")
        }
        )
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String USER_NAME_PATTERN = "^[A-Z]{1}[a-z]{2,}";
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[^a-zA-Z0-9]{1}).{8,})";

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        String userId  = getServletConfig().getInitParameter("user-id");
        String password = getServletConfig().getInitParameter("password");

        Pattern namePattern = Pattern.compile(USER_NAME_PATTERN);
        Matcher nameMatcher = namePattern.matcher(user);
        boolean isNameValid = nameMatcher.matches();

        Pattern pwdPattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher pwdMatcher = pwdPattern.matcher(pwd);
        boolean isPwdValid = pwdMatcher.matches();

        if(!isNameValid){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Enter valid user name(Starts with Capital letter & has minimum 3 characters.).</font>");
            requestDispatcher.include(req,resp);
        } else if (!isPwdValid) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Enter valid password.</font>");
            requestDispatcher.include(req,resp);
        } else if (userId.equals(user) && password.equals(pwd)) {
            req.setAttribute("user",user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req,resp);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            requestDispatcher.include(req,resp);
        }
    }
}
