package servlet.authorization;

import dto.user.UserEditDto;
import dto.user.UserRegistrationDto;
import helpers.PasswordHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserEditDto user = new UserServiceImpl().getUserEditDto(login);

        if (user != null && PasswordHelper.passwordIsCorrect(password, user.getPassword())) {
            /* Add session */
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setMaxInactiveInterval(60 * 60);

            resp.sendRedirect("/");
        } else {
            req.setAttribute("errorMessage", "Login or password is incorrect");
            req.getRequestDispatcher("/template/auth/login.jsp").forward(req, resp);
        }
    }
}
