package servlet;



import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@WebServlet("/index")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // вытаскиваем из запроса имя пользователя и его пароль
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        System.out.println("login: "+login + " password:"+password);
        // если пользователь есть в системе (а если нет?)
        User userTryLogin = UserService.getInstance().isExist(login, password);
        System.out.println("print user from session filter - userTryLogin : " + userTryLogin);
        String userRole = userTryLogin.getRole();
        System.out.println("print 1: "+ userRole);
        // создаем для него сессию
        HttpSession session = req.getSession();
        // кладем в атрибуты сессии атрибут user с именем пользователя
        // почему в сессию??

        session.setAttribute("userTryLogin", userTryLogin);
        resp.sendRedirect("/admin");
    //    resp.sendRedirect("/user");
     //   resp.sendRedirect("user");
        }
    }
