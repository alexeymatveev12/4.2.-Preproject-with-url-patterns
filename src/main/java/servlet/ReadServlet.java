package servlet;



import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class ReadServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers =  userService.getAllUsers();
        req.setAttribute("allUsers", allUsers);
        req.getServletContext().getRequestDispatcher("/jsp/read.jsp").forward(req, resp);

//        req.setAttribute("allUsers", allUsers);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/read.jsp");
//        requestDispatcher.forward(req, resp);


    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
