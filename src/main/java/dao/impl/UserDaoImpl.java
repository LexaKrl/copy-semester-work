package dao.impl;

import config.Configuration;
import dao.UserDao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    //language=sql
    private static final String SQL_GET_BY_ID = """
                select * from users where id = ?
            """;

    //language=sql
    private static final String SQL_GET_BY_LOGIN = """
                select * from users where login = ?
            """;

    //language=sql
    private static final String SQL_SAVE = """
                insert into users (name, lastname, login, password, description, photo_url) values (?, ?, ?, ?, ?, ?)
            """;

    //language=sql
    private static final String SQL_DELETE = """
                delete from users where id = ?
            """;

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
            return mapEntity(statement);
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
            return mapEntity(statement);
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

    private User mapEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return User.builder()
                    .name(resultSet.getString("name"))
                    .lastname(resultSet.getString("lastname"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .description(resultSet.getString("description"))
                    .photoUrl(resultSet.getString("photo_url"))
                    .build();
        }
        return null;
    }

    private static Optional<Connection> getConnection() {
        return Configuration.getConnection();
    }
}
