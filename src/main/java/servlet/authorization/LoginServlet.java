package servlet.authorization;

import dao.impl.UserDaoImpl;
import entity.User;
import helpers.PasswordHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        User user = new UserDaoImpl().getByLogin(login);

        if (user != null && PasswordHelper.passwordIsCorrect(password, user.getPassword())) {
            /* Add session */
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setMaxInactiveInterval(60 * 60);

            /* Add cookie */
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);

            resp.sendRedirect("/");
        } else {
            req.setAttribute("errorMessage", "Login or password is incorrect");
            req.getRequestDispatcher("/template/login.jsp").forward(req, resp);
        }
    }
}
