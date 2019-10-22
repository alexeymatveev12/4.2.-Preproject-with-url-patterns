package servlet;


import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/create")
public class CreateServlet extends HttpServlet {

 //   private UserServiceHibernate userServiceHibernate  = new UserServiceHibernate().get;
//    SessionFactory sessionFactory = DBHelper.getSessionFactory();
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext()
                .getRequestDispatcher("/jsp/create.jsp")
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  try {
     //      req.setCharacterEncoding("UTF-8");
      //  System.out.println("Попытка UTF-8");
      //  } catch (UnsupportedEncodingException e) {
      //      e.printStackTrace();
      //  }

        // form data
        String nameUser = req.getParameter("name");
        String loginUser = req.getParameter("login");
        String passwordUser = req.getParameter("password");
        String role = req.getParameter("role");

        // new client
        User newUser = new User(nameUser, loginUser, passwordUser, role);

        // check the user!!!!!!
      //  if (
                userService.addUser(newUser);
    //    {
            resp.sendRedirect("/admin");;
     //   } else {
   //         resp.getWriter().println("Client not add");
   //     }

 //       resp.setStatus(HttpServletResponse.SC_OK);

    }


}
