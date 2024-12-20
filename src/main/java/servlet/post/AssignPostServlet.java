package servlet.post;

import dto.post.PostDto;
import helpers.IdParsingHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PostService;

import java.io.IOException;

@WebServlet("/assign-post/*")
public class AssignPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = (PostService) this.getServletContext().getAttribute("postService");

        String pathInfo = req.getPathInfo();
        String[] paths = pathInfo.split("/");

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Long projectId = IdParsingHelper.parseId(req.getParameter("projectId"));
        Long teamId = IdParsingHelper.parseId(req.getParameter("teamId"));
        Long assigneeId = IdParsingHelper.parseId(paths[1]);

        if (paths.length == 2) {
            boolean isOwner = Boolean.parseBoolean(req.getParameter("isOwner"));
            if (isOwner) {
                postService.post(PostDto.builder()
                                .name(name)
                                .projectId(projectId)
                                .assigneeId(assigneeId)
                                .description(description)
                                .completed(false)
                                .build()
                );
                resp.sendRedirect("/team/%s/project/%s/manage-posts".formatted(teamId, projectId));
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
