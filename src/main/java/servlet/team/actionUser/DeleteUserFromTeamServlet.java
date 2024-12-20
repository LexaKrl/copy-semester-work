package servlet.team.actionUser;

import helpers.IdParsingHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TeamService;
import service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/team-delete-user/*")
public class DeleteUserFromTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) this.getServletContext().getAttribute("userService");
        TeamService teamService = (TeamService) this.getServletContext().getAttribute("teamService");
        String pathInfo = req.getPathInfo();
        String[] paths = pathInfo.split("/");

        Long userId = IdParsingHelper.parseId(paths[1]);
        Long teamId = IdParsingHelper.parseId(req.getParameter("delete-team-id"));

        boolean userInTeam = userService.isInTeam(userId, teamId);
        boolean isOwner = teamService.isOwner(userId, teamId);

        if (userInTeam && !isOwner) {
            userService.deleteFromTeamUser(userId);
            req.setAttribute("message", "User successfully deleted from team");

            resp.sendRedirect("/team/%s".formatted(teamId));
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
