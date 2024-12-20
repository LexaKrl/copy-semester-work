package servlet.team;

import dto.team.TeamDto;
import dto.user.UserEditDto;
import helpers.PasswordHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.TeamService;

import java.io.IOException;

@WebServlet(urlPatterns = "/join-team")
public class JoinTeamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/team/join-team.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamService teamService = (TeamService) this.getServletContext().getAttribute("teamService");

        HttpSession session = req.getSession();
        Long userId = ((UserEditDto) session.getAttribute("user")).getId();

        Long teamId = null;
        try {
            teamId = Long.valueOf(req.getParameter("team-id"));
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid team ID format.");
            req.getRequestDispatcher("/template/team/join-team.jsp").forward(req, resp);
        }

        String teamName = req.getParameter("name");
        String teamPassword = req.getParameter("password");

        TeamDto team = teamService.findById(teamId);

        if (team == null) {
            req.setAttribute("message", "This team doesn't exist, try to enter another team ID");
        } else {
            boolean isCorrectlyEntered = team.getName().equals(teamName) &&
                    PasswordHelper.passwordIsCorrect(teamPassword, teamService.retrievePassword(teamId));

            if (isCorrectlyEntered) {
                teamService.joinTeam(userId, teamId);
                req.setAttribute("message", "You successfully joined the team!");
            } else {
                req.setAttribute("message", "Looks like you input incorrect data");
            }
        }

        req.getRequestDispatcher("/template/team/join-team.jsp").forward(req, resp);
    }
}
