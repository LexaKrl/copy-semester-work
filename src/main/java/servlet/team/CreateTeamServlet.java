package servlet.team;

import dto.team.TeamEditDto;
import dto.user.UserEditDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TeamService;
import service.impl.TeamServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/create-team")
public class CreateTeamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/template/team/create-team.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamService teamService = new TeamServiceImpl();

        String teamName = req.getParameter("name");
        String teamPassword = req.getParameter("password");
        Long ownerId = ((UserEditDto) req.getSession().getAttribute("user")).getId();

        boolean teamExist = teamService.teamExist(ownerId, teamName);

        if (ownerId != null && !teamName.isEmpty() && !teamPassword.isEmpty() && !teamExist) {
            teamService.register(TeamEditDto.builder()
                    .name(teamName)
                    .ownerId(ownerId)
                    .password(teamPassword)
                    .build());

            req.setAttribute("message", "Your team successfully registered");
            req.getRequestDispatcher("/template/team/create-team.jsp").forward(req, resp);
        } else if (teamExist) {
            req.setAttribute("message", "You already have team with this name");
            req.getRequestDispatcher("/template/team/create-team.jsp").forward(req, resp);
        } else {

            req.setAttribute("message","You enter incorrect data. Try again");
            req.getRequestDispatcher("/template/team/create-team.jsp").forward(req, resp);
        }
    }
}
