package servlet.dispatcher;

import dto.team.TeamDto;
import dto.user.UserDto;
import dto.user.UserEditDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.TeamService;
import service.UserService;
import service.impl.TeamServiceImpl;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/team/*")
public class TeamDispatcherServlet extends HttpServlet {
    TeamService teamService = new TeamServiceImpl();
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        Long userId = ((UserEditDto) session.getAttribute("user")).getId();

        String pathInfo = req.getPathInfo();
        String[] paths = pathInfo.split("/");

        if (paths.length == 2) {
            Long teamId = parseTeamId(paths, req, resp);

            TeamDto team = teamService.retrieveTeamById(teamId);
            boolean isUserInTeam = userService.isInTeam(userId, teamId);

            if (team != null && isUserInTeam) {
                List<UserDto> members = userService.retrieveAllByTeamId(teamId);

                req.setAttribute("team", team);
                req.setAttribute("members", members);
                /*      TODO implement this line
                req.setAttribute("projects", projectService.getAllByTeamId(teamId));
                */
                req.getRequestDispatcher("/template/team/team.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (paths.length == 3 && paths[2].equals("edit")) {
            Long teamId = parseTeamId(paths, req, resp);

            TeamDto team = teamService.retrieveTeamById(teamId);

            req.setAttribute("team", team);
            /*TODO implement edit page for the team page*/

            req.getRequestDispatcher("/template/team/edit.jsp").forward(req, resp);
        } else if (paths.length == 3 && paths[2].equals("projects")) {

            /* TODO implement some logic */

        } else if (paths.length == 4) {
            Long teamId = Long.getLong(paths[1]);
            Long projectId = Long.getLong(paths[3]);

            /*TODO implement logic for the team_project page*/

            req.getRequestDispatcher("/template/project/project.jsp").forward(req, resp);
        } else if (paths.length == 5) {
            /* TODO implement logic for the project edit page*/

            req.getRequestDispatcher("/template/project/edit.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Long parseTeamId(String[] paths, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            if (paths[1] != null) {
                return Long.parseLong(paths[1]);
            } else {
                throw new NumberFormatException("Team ID is missing, null or not exist");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid team ID format.");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        return -1L;
    }
}
