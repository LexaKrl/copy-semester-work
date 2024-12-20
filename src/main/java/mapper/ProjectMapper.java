package mapper;

import entity.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper extends BaseMapper<Project> {

    @Override
    public Project buildEntity(ResultSet resultSet) throws SQLException {
        return Project.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .projectOwnerId(resultSet.getLong("owner_id"))
                .projectTeamId(resultSet.getLong("team_id"))
                .description(resultSet.getString("description"))
                .build();
    }
}
