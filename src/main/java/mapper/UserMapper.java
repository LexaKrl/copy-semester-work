package mapper;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends BaseMapper<User> {

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
