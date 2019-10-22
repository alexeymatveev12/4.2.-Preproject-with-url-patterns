package servlet;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//Вопрос!        User user = (User) req.getSession().getAttribute("userLogOn");
        User user = (User) req.getSession().getAttribute("userTryLogin");

        System.out.println("print user from USER servlet - userTryLogin : " + user );

         req.setAttribute("user", user);
    //    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/user.jsp");
      req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);

  //      requestDispatcher.forward(req, resp);

    }
/*
     req.getServletContext()
             .getRequestDispatcher("/jsp/create.jsp")
                .forward(req, resp); */
}
