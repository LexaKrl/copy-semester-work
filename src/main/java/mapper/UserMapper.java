package mapper;

import dto.user.UserDto;
import entity.Team;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMapper implements Mapper<User> {

    @Override
    public User mapEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return buildEntity(resultSet);
        }
        return null;
    }

    public List<User> mapListEntities(PreparedStatement statement, List<User> users) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                users.add(buildEntity(resultSet));
            }
        }
        return users;
    }

    public User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .lastname(resultSet.getString("lastname"))
                .login(resultSet.getString("login"))
                .description(resultSet.getString("description"))
                .password(resultSet.getString("password"))
                .photoUrl(resultSet.getString("photo_url"))
                .build();
    }
}
