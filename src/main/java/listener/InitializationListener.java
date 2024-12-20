package listener;


import dao.PostDao;
import dao.ProjectDao;
import dao.TeamDao;
import dao.UserDao;
import dao.impl.PostDaoImpl;
import dao.impl.ProjectDaoImpl;
import dao.impl.TeamDaoImpl;
import dao.impl.UserDaoImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import mapper.PostMapper;
import mapper.ProjectMapper;
import mapper.TeamMapper;
import mapper.UserMapper;
import service.PostService;
import service.ProjectService;
import service.TeamService;
import service.UserService;
import service.impl.PostServiceImpl;
import service.impl.ProjectServiceImpl;
import service.impl.TeamServiceImpl;
import service.impl.UserServiceImpl;

@WebListener
public class InitializationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();

        TeamMapper teamMapper = new TeamMapper();
        UserMapper userMapper = new UserMapper();
        ProjectMapper projectMapper = new ProjectMapper();
        PostMapper postMapper = new PostMapper();

        TeamDao teamDao = new TeamDaoImpl(teamMapper);
        UserDao userDao = new UserDaoImpl(userMapper);
        ProjectDao projectDao = new ProjectDaoImpl(projectMapper);
        PostDao postDao = new PostDaoImpl(postMapper);

        TeamService teamService = new TeamServiceImpl(teamDao);
        UserService userService = new UserServiceImpl(userDao);
        ProjectService projectService = new ProjectServiceImpl(projectDao);
        PostService postService = new PostServiceImpl(postDao);

        servletContext.setAttribute("teamService", teamService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("projectService", projectService);
        servletContext.setAttribute("postService", postService);
    }
}