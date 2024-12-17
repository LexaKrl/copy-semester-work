package mapper;

import entity.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamMapper implements Mapper<Team> {
    @Override
    public Team mapEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return Team.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .ownerId(resultSet.getLong("owner_id"))
                    .password(resultSet.getString("password"))
                    .build();
        }
        return null;
    }

    public List<Team> mapListEntities(PreparedStatement statement, List<Team> teams) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                teams.add(Team.builder()
                        .name(resultSet.getString("name"))
                        .ownerId(resultSet.getLong("owner_id"))
                        .id(resultSet.getLong("id"))
                        .build()
                );
            }
        }
        return teams;
    }
}
