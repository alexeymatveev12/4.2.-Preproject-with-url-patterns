package servlet;


import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {

    UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long reqID = Long.parseLong(req.getParameter("id"));

        User userToDelete = userService.getUserById(reqID);
        req.setAttribute("user", userToDelete);
        req.getServletContext().getRequestDispatcher("/jsp/delete.jsp").forward(req, resp);

    /*    if (userService.addUser(newUser)) {
            resp.sendRedirect("read");;
        } else {
            resp.getWriter().println("Client not add");
        }
*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long reqId = Long.parseLong(req.getParameter("id"));
      //  if (
                userService.deleteUserById(reqId);// {
            resp.sendRedirect("/admin");;
     //   } else {
      //      resp.getWriter().println("Client didn't delete");
        }


  //  }


}
