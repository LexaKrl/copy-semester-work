package dao.impl;

import dao.ProjectDao;
import entity.Project;
import lombok.RequiredArgsConstructor;
import mapper.ProjectMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.Configuration.getConnection;

@RequiredArgsConstructor
public class ProjectDaoImpl implements ProjectDao {

    private final ProjectMapper mapper;

    //language=sql
    private static final String SQL_GET_PROJECTS_BY_TEAM_ID = """
        select * from project where team_id = ?;
    """;

    //language=sql
    private static final String SQL_GET_BY_ID = """
        select * from project where id = ?;
    """;

    //language=sql
    private static final String SQL_GET_BY_NAME = """
        select * from project where name = ?;
    """;

    //language=sql
    private static final String SQL_SAVE = """
        insert into project (name, owner_id, team_id, description) values (?, ?, ?, ?);
    """;

    //language=sql
    private static final String SQL_SAVE_USER_PROJECT = """
        insert into user_project (user_id, project_id) values (?, ?);
    """;

    //language=sql
    private static final String SQL_DELETE = """
        delete from project where id = ?;
    """;

    //language=sql
    private static final String SQL_UPDATE_BY_ID = """
        update project set (name, owner_id, team_id, description) = (?, ?, ?, ?) where id = ?;
    """;

    //language=sql
    private static final String SQL_DELETE_USER_PROJECT = """
        delete from user_project where (user_id, project_id) = (?, ?);
    """;

    @Override
    public List<Project> getAllByTeamId(Long id) {
        List<Project> projects = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_PROJECTS_BY_TEAM_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, id);

            return mapper.mapListEntities(statement, projects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project getById(Long id) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_BY_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, id);

            return mapper.mapEntity(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project getByName(String name) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_BY_NAME);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setString(1, name);

            return mapper.mapEntity(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void leaveProject(Long userId, Long projectId) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_DELETE_USER_PROJECT);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, userId);
            statement.setLong(2, projectId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Project project) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_SAVE);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setString(1, project.getName());
            statement.setLong(2, project.getProjectOwnerId());
            statement.setLong(3, project.getProjectTeamId());
            statement.setString(4, project.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long projectId) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_DELETE);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, projectId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Project project) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_UPDATE_BY_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setString(1, project.getName());
            statement.setLong(2, project.getProjectOwnerId());
            statement.setLong(3, project.getProjectTeamId());
            statement.setString(4, project.getDescription());
            statement.setLong(5, project.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void joinProject(Long userId, Long projectId) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_SAVE_USER_PROJECT);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, userId);
            statement.setLong(2, projectId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
