package servlet.dispatcher;

import dto.post.PostDto;
import dto.project.ProjectDto;
import dto.team.TeamDto;
import dto.user.UserDto;
import dto.user.UserEditDto;
import helpers.IdParsingHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PostService;
import service.ProjectService;
import service.TeamService;
import service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/team/*")
public class TeamDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ProjectService projectService = (ProjectService) this.getServletContext().getAttribute("projectService");
        PostService postService = (PostService) this.getServletContext().getAttribute("postService");
        TeamService teamService = (TeamService) this.getServletContext().getAttribute("teamService");
        UserService userService = (UserService) this.getServletContext().getAttribute("userService");

        Long userId = ((UserEditDto) session.getAttribute("user")).getId();

        String pathInfo = req.getPathInfo();
        String[] paths = pathInfo.split("/");

        /* parsing path to the /team/{team-id} */

        if (paths.length == 2) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            TeamDto team = teamService.findById(teamId);

            boolean isUserInTeam = userService.isInTeam(userId, teamId);

            if (teamService.teamExist(teamId) && isUserInTeam) {
                List<UserDto> members = userService.retrieveAllByTeamId(teamId);
                boolean isOwner = teamService.isOwner(userId, teamId);

                req.setAttribute("isOwner", isOwner);
                req.setAttribute("team", team);
                req.setAttribute("members", members);
                req.setAttribute("projects", projectService.getAllByTeamId(teamId));
                req.getRequestDispatcher("/template/team/team.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

            /* parsing path to the /team/{team-id}/manage */

        } else if (paths.length == 3 && paths[2].equals("manage")) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            TeamDto team = teamService.findById(teamId);

            boolean isOwner = teamService.isOwner(userId, teamId);

            if (isOwner) {
                req.setAttribute("userId", userId);
                req.setAttribute("team", team);
                req.setAttribute("projects", projectService.getAllByTeamId(teamId));

                req.getRequestDispatcher("/template/team/manage.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

            /* parsing path to the /team/{team-id}/project/{project-id} */

        } else if (paths.length == 4 && paths[2].equals("project")) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            Long projectId = IdParsingHelper.parseId(paths[3]);

            ProjectDto project = projectService.findById(projectId);
            boolean isUserInTeam = userService.isInTeam(userId, teamId);

            if (!isUserInTeam) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else if (teamService.teamExist(teamId) && project != null) {
                String type = req.getParameter("type");

                List<PostDto> posts = new ArrayList<>();

                if (type == null || type.equals("get-all-posts")) {
                    posts = postService.getAllByProjectId(projectId);
                } else if (type.equals("get-assignee-to-me")) {
                    posts = postService.getAllByUserId(userId);
                } else if (type.equals("get-all-completed")) {
                    posts = postService.getAllByStatus(true);
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }

                req.setAttribute("isOwner", teamService.isOwner(userId, teamId));
                req.setAttribute("teamId", teamId);
                req.setAttribute("project", project);
                req.setAttribute("posts", posts);

                req.getRequestDispatcher("/template/project/project.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

            /* parsing path to the /team/{team-id}/project/{project-id}/manage-members */

        } else if (paths.length == 5 && paths[2].equals("project") && paths[4].equals("manage-members")) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            Long projectId = IdParsingHelper.parseId(paths[3]);

            boolean isOwner = teamService.isOwner(userId, teamId);

            if (isOwner) {
                List<UserDto> teamMembers = userService.retrieveAllByTeamId(teamId);
                List<UserDto> projectMembers = userService.retrieveAllByProjectId(projectId);

                req.setAttribute("teamMembers", teamMembers);
                req.setAttribute("projectMembers", projectMembers);
                req.setAttribute("isOwner", isOwner);
                req.setAttribute("teamId", teamId);
                req.setAttribute("projectId", projectId);

                req.getRequestDispatcher("/template/project/manage-members.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

            /* parsing path to the /team/{team-id}/project/{project-id}/manage-posts */

        } else if (paths.length == 5 && paths[2].equals("project") && paths[4].equals("manage-posts")) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            Long projectId = IdParsingHelper.parseId(paths[3]);

            boolean isOwner = teamService.isOwner(userId, teamId);

            if (isOwner) {
                List<UserDto> members = userService.retrieveAllByProjectId(projectId);

                req.setAttribute("members", members);
                req.setAttribute("isOwner", isOwner);
                req.setAttribute("teamId", teamId);
                req.setAttribute("posts", postService.getAllByProjectId(projectId));
                req.setAttribute("projectId", projectId);

                req.getRequestDispatcher("/template/project/manage-posts.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

            /* parsing path to the /team/{team-id}/project/{project-id}/manage-data */

        } else if (paths.length == 5 && paths[2].equals("project") && paths[4].equals("manage-data")) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            Long projectId = IdParsingHelper.parseId(paths[3]);

            ProjectDto project = projectService.findById(projectId);
            boolean isOwner = teamService.isOwner(userId, teamId);

            if (isOwner && project != null) {
                req.setAttribute("project", project);
                req.setAttribute("teamId", teamId);

                req.getRequestDispatcher("/template/project/manage-data.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

            /* parsing path to the /team/{team-id}/project/{project-id}/post/{post-id} */

        } else if (paths.length == 6 && paths[2].equals("project") && paths[4].equals("post")) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            Long projectId = IdParsingHelper.parseId(paths[3]);
            Long postId = IdParsingHelper.parseId(paths[5]);

            PostDto post = postService.getById(postId);
            boolean isUserInTeam = userService.isInTeam(userId, teamId);
            boolean postInProject = postService.isInProject(postId, projectId);

            if (!isUserInTeam) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else if (post != null && postInProject) {
                req.setAttribute("teamId", teamId);
                req.setAttribute("projectId", projectId);
                req.setAttribute("post", post);

                req.getRequestDispatcher("/template/post/post.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

            /* parsing path to the /team/{team-id}/project/{project-id}/post/{post-id}/edit */

        } else if (paths.length == 7 && paths[2].equals("project") && paths[4].equals("post") && paths[6].equals("edit")) {
            Long teamId = IdParsingHelper.parseId(paths[1]);
            Long projectId = IdParsingHelper.parseId(paths[3]);
            Long postId = IdParsingHelper.parseId(paths[5]);

            PostDto post = postService.getById(postId);
            boolean isOwner = teamService.isOwner(userId, teamId);
            boolean isAssignee = postService.isAssignee(userId, postId);
            boolean postInProject = postService.isInProject(postId, projectId);

            if (isAssignee || isOwner) {
                if (post != null && postInProject) {
                    req.setAttribute("teamId", teamId);
                    req.setAttribute("projectId", projectId);
                    req.setAttribute("post", post);

                    req.getRequestDispatcher("/template/post/edit-post.jsp").forward(req, resp);
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
