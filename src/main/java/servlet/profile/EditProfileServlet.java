package servlet.profile;

import dto.user.UserDto;
import dto.user.UserEditDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/profile/edit")
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/profile/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserService service = new UserServiceImpl();

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String description = req.getParameter("description");

        UserDto user = service.getByLogin(login);

        if (user == null) {
            service.editInfo(UserEditDto.builder()
                    .id(((UserEditDto) session.getAttribute("user")).getId())
                    .name(name)
                    .lastname(lastname)
                    .login(login)
                    .description(description)
                    .build());

            session.setAttribute("user", service.getUserEditDto(login));

            /* TODO implement js message */

            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("message", "Your data hasn't updated. Try to enter another login");
            req.getRequestDispatcher("/template/profile/edit.jsp").forward(req, resp);
        }
    }
}
