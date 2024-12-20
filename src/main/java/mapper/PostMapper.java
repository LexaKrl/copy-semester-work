package mapper;

import entity.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper extends BaseMapper<Post> {

    @Override
    public Post buildEntity(ResultSet resultSet) throws SQLException {
        return Post.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .projectId(resultSet.getLong("project_id"))
                .assigneeId(resultSet.getLong("assignee_id"))
                .description(resultSet.getString("description"))
                .completed(resultSet.getBoolean("completed"))
                .photoUrl(resultSet.getString("photo_url"))
                .build();
    }
}
