package dao.impl;

import config.Configuration;
import dao.UserDao;
import dto.user.UserDto;
import entity.User;
import mapper.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    UserMapper mapper = new UserMapper();

    //language=sql
    private static final String SQL_UPDATE_BY_ID = """
                UPDATE users 
                SET name = ?,
                lastname = ?,
                login = ?,
                description = ? 
                WHERE id = ?;
            """;

    //language=sql
    private static final String SQL_UPDATE_PHOTO_URL_BY_ID = """
                UPDATE users SET photo_url = ? WHERE id = ?;
            """;

    //language=sql
    private static final String SQL_PASSWORD_BY_LOGIN = """
                UPDATE users SET password = ? WHERE login = ?;
            """;

    //language=sql
    private static final String SQL_GET_PHOTO_URL_BY_LOGIN = """
                select photo_url from users where login = ?;
            """;

    //language=sql
    private static final String SQL_GET_PASSWORD_BY_LOGIN = """
                select password from users where login = ?;
            """;

    //language=sql
    private static final String SQL_GET_BY_ID = """
                select * from users where id = ?;
            """;

    //language=sql
    private static final String SQL_GET_BY_LOGIN = """
                select * from users where login = ?;
            """;

    //language=sql
    private static final String SQL_SAVE = """
                insert into users (name, lastname, login, password, description, photo_url) values (?, ?, ?, ?, ?, ?);
            """;

    //language=sql
    private static final String SQL_DELETE = """
                delete from users where id = ?;
            """;

    //language=sql
    private static final String SQL_GET_ALL_BY_TEAM_ID = """
                select * from users where id in
                (select user_id from team_user where team_id = ?);
            """;

    @Override
    public List<User> getAllByTeamId(Long teamId) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_ALL_BY_TEAM_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, teamId);

            return mapper.mapListEntities(statement, users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(Long id) {
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
    public User getByLogin(String login) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_BY_LOGIN);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setString(1, login);
            return mapper.mapEntity(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save(User user) {
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

                statement.setString(1, user.getName());
                statement.setString(2, user.getLastname());
                statement.setString(3, user.getLogin());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getDescription());
                statement.setString(6, user.getPhotoUrl());

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
    public void updateInfo(User user) {
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

            statement.setString(1, user.getName());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getDescription());
            statement.setLong(5, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePassword(String password, String login) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_PASSWORD_BY_LOGIN);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setString(1, password);
            statement.setString(2, login);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveMediaUrl(String url, Long id) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_UPDATE_PHOTO_URL_BY_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setString(1, url);
            statement.setLong(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPasswordByLogin(String login) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_PASSWORD_BY_LOGIN);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPhotoUrl(String login) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_PHOTO_URL_BY_LOGIN);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("photo_url");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Optional<Connection> getConnection() {
        return Configuration.getConnection();
    }
}
