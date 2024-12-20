package servlet.post;

import dto.post.PostDto;
import dto.user.UserEditDto;
import helpers.IdParsingHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PostService;
import service.TeamService;

import java.io.IOException;

@WebServlet(urlPatterns = "/edit-post/*")
public class EditPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = (PostService) this.getServletContext().getAttribute("postService");
        TeamService teamService = (TeamService) this.getServletContext().getAttribute("teamService");

        HttpSession session = req.getSession();
        Long userId = ((UserEditDto) session.getAttribute("user")).getId();
        Long teamId = IdParsingHelper.parseId(req.getParameter("teamId"));
        Long projectId = IdParsingHelper.parseId(req.getParameter("projectId"));
        Long postId = IdParsingHelper.parseId(req.getParameter("postId"));
        String pathInfo = req.getPathInfo();
        String[] paths = pathInfo.split("/");

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        boolean completed = Boolean.parseBoolean(req.getParameter("completed"));
        boolean isAssignee = postService.isAssignee(userId, postId);
        boolean isOwner = teamService.isOwner(userId, teamId);

        Long postToEdit = IdParsingHelper.parseId(paths[1]);
        PostDto post = postService.getById(postToEdit);

        if (paths.length == 2) {
            if (isOwner || isAssignee && post != null) {
                postService.update(PostDto.builder()
                                .id(postToEdit)
                                .name(name)
                                .description(description)
                                .projectId(projectId)
                                .completed(completed)
                                .build()
                );
                resp.sendRedirect("/team/%s/project/%s".formatted(teamId, projectId));
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
