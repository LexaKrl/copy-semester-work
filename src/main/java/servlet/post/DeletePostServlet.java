package servlet.post;

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

@WebServlet(urlPatterns = "/delete-post/*")
public class DeletePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = (PostService) this.getServletContext().getAttribute("postService");
        TeamService teamService = (TeamService) this.getServletContext().getAttribute("teamService");

        HttpSession session = req.getSession();
        Long userId = ((UserEditDto) session.getAttribute("user")).getId();
        String pathInfo = req.getPathInfo();
        String[] paths = pathInfo.split("/");

        Long postId = IdParsingHelper.parseId(paths[1]);
        Long teamId = IdParsingHelper.parseId(req.getParameter("teamId"));
        Long projectId = IdParsingHelper.parseId(req.getParameter("projectId"));
        boolean isOwner = teamService.isOwner(userId, teamId);
        boolean isAssignee = postService.isAssignee(userId, postId);

        if (paths.length == 2) {
            if (isOwner || isAssignee) {
                postService.delete(postId);
                resp.sendRedirect("/team/%s/project/%s".formatted(teamId, projectId));
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
