package servlet.profile;

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

@WebServlet(urlPatterns = "/profile/edit/password")
public class EditPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserService userService = (UserService) this.getServletContext().getAttribute("userService");

        String login = ((UserEditDto) session.getAttribute("user")).getLogin();

        String oldPassword = req.getParameter("old-password");
        String newPassword = req.getParameter("new-password");
        String newPasswordApprove = req.getParameter("new-password-approve");

        String hashPassword = userService.retrievePassword(login);

        if (oldPassword != null && PasswordHelper.passwordIsCorrect(oldPassword,hashPassword)) {
            if (newPassword.equals(newPasswordApprove)) {

                userService.editPassword(newPassword, login);

                session.setAttribute("user", userService.getUserEditDto(login));

                /* TODO implement js message */

                resp.sendRedirect("/profile");
            } else {
                req.setAttribute("passwordMessage", "Passwords don't match");
                req.getRequestDispatcher("/template/profile/manage-member.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("passwordMessage", "Looks like you texted incorrect password");
            req.getRequestDispatcher("/template/profile/manage-member.jsp").forward(req, resp);
        }
    }
}
