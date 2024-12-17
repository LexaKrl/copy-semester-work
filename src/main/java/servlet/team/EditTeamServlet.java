package servlet.team;

import dto.team.TeamDto;
import dto.team.TeamEditDto;
import helpers.PasswordHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TeamService;
import service.impl.TeamServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/edit-team")
public class EditTeamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long teamId;
        try {
            teamId = Long.valueOf(req.getParameter("team-id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException("Team ID is incorrect");
        }

        TeamService service = new TeamServiceImpl();

        String name = req.getParameter("name");
        String newPassword = req.getParameter("new-password");
        String password = req.getParameter("password");

        if (PasswordHelper.passwordIsCorrect(password, service.retrievePassword(teamId))) {
            service.update(TeamEditDto.builder()
                    .name(name)
                    .id(teamId)
                    .password(newPassword)
                    .build());
        }
    }
}
