package dao.impl;

import dao.PostDao;
import entity.Post;
import lombok.RequiredArgsConstructor;
import mapper.PostMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.Configuration.getConnection;

@RequiredArgsConstructor
public class PostDaoImpl implements PostDao {

    private final PostMapper mapper;

    //language=sql
    private static final String SQL_GET_ALL_BY_PROJECT_ID = """
        select * from post where project_id = ?;
    """;

    //language=sql
    private static final String SQL_UPDATE = """
        update post set (name, project_id, description, completed) = (?, ?, ?, ?) where id = ?;
    """;

    //language=sql
    private static final String SQL_SAVE_PHOTO_URL = """
        update post set photo_url = ? where id = ?;
    """;

    //language=sql
    private static final String SQL_DELETE = """
        delete from post where id = ?;
    """;

    //language=sql
    private static final String SQL_GET_BY_ID = """
        select * from post where id = ?;
    """;

    //language=sql
    private static final String SQL_GET_ALL_BY_USER_ID = """
        select * from post where assignee_id = ?;
    """;

    //language=sql
    private static final String SQL_GET_ALL_BY_COMPLETED = """
        select * from post where completed = ?;
    """;

    //language=sql
    private static final String SQL_SAVE = """
        insert into post (name, project_id, assignee_id, description, photo_url, completed) values (?, ?, ?, ?, ?, ?);
    """;

    //language=sql
    private static final String SQL_GET_PHOTO_URL_BY_ID = """
        select photo_url from post where id = ?;
    """;

    //language=sql
    private static final String SQL_IS_ASSIGNEE = """
        select assignee_id from post where id = ?;
    """;

    @Override
    public List<Post> getAllByProjectId(Long projectId) {
        List<Post> projects = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_ALL_BY_PROJECT_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, projectId);

            return mapper.mapListEntities(statement, projects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post getById(Long postId) {
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

            statement.setLong(1, postId);

            return mapper.mapEntity(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAllByUserId(Long userId) {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_ALL_BY_USER_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, userId);

            return mapper.mapListEntities(statement, posts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAllByStatus(boolean b) {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_ALL_BY_COMPLETED);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
        statement.setBoolean(1, b);

        return mapper.mapListEntities(statement, posts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Post post) {
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
            statement.setString(1, post.getName());
            statement.setLong(2, post.getProjectId());
            statement.setLong(3, post.getAssigneeId());
            statement.setString(4, post.getDescription());
            statement.setString(5, post.getPhotoUrl());
            statement.setBoolean(6, post.isCompleted());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPhotoUrl(Long postId) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_PHOTO_URL_BY_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setLong(1, postId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("photo_url");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePhotoUrl(String photoUrl, Long postId) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_SAVE_PHOTO_URL);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setString(1, photoUrl);
            statement.setLong(2, postId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
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
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isAssignee(Long userId, Long postId) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_IS_ASSIGNEE);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, postId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return userId.equals(resultSet.getLong("assignee_id"));
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Post post) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_UPDATE);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setString(1, post.getName());
            statement.setLong(2, post.getProjectId());
            statement.setString(3, post.getDescription());
            statement.setBoolean(4, post.isCompleted());
            statement.setLong(5, post.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
