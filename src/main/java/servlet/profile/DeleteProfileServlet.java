package servlet.profile;

import dto.user.UserEditDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

import java.io.IOException;

@WebServlet("/profile/edit/delete")
public class DeleteProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserService userService = (UserService) this.getServletContext().getAttribute("userService");
        UserEditDto user = ((UserEditDto) session.getAttribute("user"));

        userService.delete(user.getId());
        resp.sendRedirect("/logout");
    }
}
