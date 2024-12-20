package servlet.project;

import dto.project.ProjectDto;
import helpers.IdParsingHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;

import java.io.IOException;

@WebServlet(urlPatterns = "/delete-project")
public class DeleteProjectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectService projectService = (ProjectService) this.getServletContext().getAttribute("projectService");

        Long projectId = IdParsingHelper.parseId(req.getParameter("project-id"));
        Long teamId = IdParsingHelper.parseId(req.getParameter("project-team-id"));
        ProjectDto project = projectService.findById(projectId);

        if (project != null) {
            projectService.delete(projectId);

            req.setAttribute("message", "Your project successfully deleted");
        } else {
            req.setAttribute("message", "Seems like you don't have such project");
        }

        resp.sendRedirect("/team/%s".formatted(teamId));
    }
}
