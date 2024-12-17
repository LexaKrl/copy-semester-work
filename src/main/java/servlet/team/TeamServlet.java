package servlet.team;

import dto.team.TeamDto;
import dto.user.UserEditDto;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.TeamService;
import service.impl.TeamServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/team")
public class TeamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamService service = new TeamServiceImpl();

        String type = req.getParameter("type");
        HttpSession session = req.getSession();
        Long id = ((UserEditDto) session.getAttribute("user")).getId();

        List<TeamDto> teams = new ArrayList<>();
        if (type == null || type.equals("owner")) {
            teams = service.retrieveTeamListWhereUserOwner(id);
        } else if (type.equals("member")) {
            teams = service.retrieveTeamListWhereUserMember(id);
        }

        req.setAttribute("teams", teams);

        req.getRequestDispatcher("/template/team/team-main.jsp").forward(req, resp);
    }
}
