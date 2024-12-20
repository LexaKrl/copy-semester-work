package mapper;

import entity.Team;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMapper extends BaseMapper<Team> {

    @Override
    public Team buildEntity(ResultSet resultSet) throws SQLException {
        return Team.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .ownerId(resultSet.getLong("owner_id"))
                .password(resultSet.getString("password"))
                .build();
    }
}
