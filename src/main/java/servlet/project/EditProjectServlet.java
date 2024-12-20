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

@WebServlet(urlPatterns = "/edit-project")
public class EditProjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectService projectService = (ProjectService) this.getServletContext().getAttribute("projectService");

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Long projectId = IdParsingHelper.parseId(req.getParameter("projectId"));

        ProjectDto project = projectService.findById(projectId);

        if (project != null && name != null) {
            projectService.update(
                    ProjectDto.builder()
                            .id(projectId)
                            .name(name)
                            .projectOwnerId(project.getProjectOwnerId())
                            .projectTeamId(project.getProjectTeamId())
                            .description(description)
                            .build()
            );

            resp.sendRedirect("/team/%s/manage".formatted(project.getProjectTeamId()));
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
