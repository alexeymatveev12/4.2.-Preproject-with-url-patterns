package filters;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(
        urlPatterns = "/index",
        filterName = "LoginFilter",
        description = "Filter after Login"
)


public class LoginFilter implements Filter {

    private String contextPath;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        contextPath = fc.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(); //для получения атрибута

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User userTryLogin = UserService.getInstance().isExist(login, password);

        String userRole = userTryLogin.getRole();

        if (userRole.equals("admin")) {

            session.setAttribute("userTryLogin", userTryLogin);
            resp.sendRedirect("/admin");

        }
        //redirect на UserServlet
        else if (userRole.equals("user")) {


            session.setAttribute("userTryLogin", userTryLogin);

            resp.sendRedirect("user");

        }
        //перенаправляем на LoginServlet
        else if (userRole == null) {
            req.getServletContext()
                    .getRequestDispatcher("/jsp/index.jsp")
                    .forward(req, resp);
        }


    }


    @Override
    public void destroy() {

    }


}










