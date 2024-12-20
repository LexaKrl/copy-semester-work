package servlet.project;

import helpers.IdParsingHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;

import java.io.IOException;

@WebServlet(urlPatterns = "/join-project/*")
public class JoinProjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectService projectService = (ProjectService) this.getServletContext().getAttribute("projectService");

        Long projectId = IdParsingHelper.parseId(req.getParameter("projectId"));
        Long teamId = IdParsingHelper.parseId(req.getParameter("teamId"));
        String pathInfo = req.getPathInfo();
        String[] paths = pathInfo.split("/");

        Long userToJoin = IdParsingHelper.parseId(paths[1]);

        if (paths.length == 2) {
            boolean isOwner = Boolean.parseBoolean(req.getParameter("isOwner"));
            if (isOwner) {
                projectService.joinProject(userToJoin, projectId);
                resp.sendRedirect("/team/%s/project/%s/manage-members".formatted(teamId, projectId));
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
