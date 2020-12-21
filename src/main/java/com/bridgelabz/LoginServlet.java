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

@WebServlet(
        description = "Login Servlet",
        urlPatterns = {"/LoginServlet"},
        initParams ={
                @WebInitParam(name="user-id",value = "Madhu"),
                @WebInitParam(name="password", value= "@Mhud123")
        })
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        String userId  = getServletConfig().getInitParameter("user-id");
        String password = getServletConfig().getInitParameter("password");

        if (userId.equals(user) && password.equals(pwd)) {
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
