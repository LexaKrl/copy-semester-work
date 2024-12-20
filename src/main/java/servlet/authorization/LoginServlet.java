package servlet.authorization;

import dto.user.UserEditDto;
import helpers.PasswordHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) this.getServletContext().getAttribute("userService");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserEditDto user = userService.getUserEditDto(login);

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
