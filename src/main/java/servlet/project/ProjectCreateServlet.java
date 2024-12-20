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

@WebServlet(urlPatterns = "/create-project")
public class ProjectCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectService projectService = (ProjectService) this.getServletContext().getAttribute("projectService");

        String name = req.getParameter("name");
        Long projectOwnerId = IdParsingHelper.parseId(req.getParameter("project-owner-id"));
        Long projectTeamId = IdParsingHelper.parseId(req.getParameter("project-team-id"));

        projectService.create(ProjectDto.builder()
                .name(name)
                .projectTeamId(projectTeamId)
                .projectOwnerId(projectOwnerId)
                .build()
        );
        req.setAttribute("message", "Your project successfully created");
        resp.sendRedirect("/team/%s".formatted(projectTeamId));
    }
}
