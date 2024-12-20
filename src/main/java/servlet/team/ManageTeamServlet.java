package servlet.team;

import dto.team.TeamEditDto;
import helpers.PasswordHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TeamService;

import java.io.IOException;

@WebServlet(urlPatterns = "/manage-team")
public class ManageTeamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long teamId;
        try {
            teamId = Long.parseLong(req.getParameter("team-id"));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Team ID is incorrect");
        }

        TeamService teamService = (TeamService) this.getServletContext().getAttribute("teamService");

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (PasswordHelper.passwordIsCorrect(password, teamService.retrievePassword(teamId))) {
            teamService.update(TeamEditDto.builder()
                    .name(name)
                    .id(teamId)
                    .build());
        }

        resp.sendRedirect("/team/%s".formatted(teamId));
    }
}
