package servlet.authorization;

import dto.user.UserRegistrationDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean isRegistered = userService.getByLogin(login) != null;


        if (!isRegistered) {
            userService.register(UserRegistrationDto.builder()
                    .name(name)
                    .lastname(lastname)
                    .login(login)
                    .password(password)
                    .build()
            );
            resp.sendRedirect("/");
        } else {
            req.setAttribute("errorMessage", "Account already exist");
            req.getRequestDispatcher("/template/auth/register.jsp").forward(req, resp);
        }
    }
}
