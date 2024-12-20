package servlet.team;

import dto.user.UserEditDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.TeamService;

import java.io.IOException;

@WebServlet(urlPatterns = "/delete-team")
public class DeleteTeamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamService teamService = (TeamService) this.getServletContext().getAttribute("teamService");
        HttpSession session = req.getSession();

        Long userId = ((UserEditDto) session.getAttribute("user")).getId();
        Long teamId = parseId(req.getParameter("delete-team-id"), resp);

        boolean isOwner = teamService.isOwner(userId, teamId);

        if (isOwner) {
            teamService.delete(teamId);
            resp.sendRedirect("/team");
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private Long parseId(String id, HttpServletResponse resp) throws IOException {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            throw new NumberFormatException("Can't convert team id to Long");
        }
    }
}
