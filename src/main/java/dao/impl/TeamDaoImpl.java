package dao.impl;

import dao.TeamDao;
import entity.Team;
import mapper.TeamMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.Configuration.getConnection;

public class TeamDaoImpl implements TeamDao {

    TeamMapper mapper = new TeamMapper();

    //language=sql
    private static final String SQL_GET_TEAM_BY_ID = """
        select * from team where id = ?
    """;

    //language=sql
    private static final String SQL_GET_TEAM_BY_USER_ID_AND_NAME = """
        select id from team where (owner_id, name) = (?, ?);
    """;

    //language=sql
    private static final String SQL_GET_TEAMS_BY_OWNER_ID_FROM_TEAM_USER = """
        select * from team_user
        left join public.team team on team_user.team_id = team.id
        where user_id = ?;
    """;

    //language=sql
    private static final String SQL_GET_TEAMS_BY_OWNER_ID = """
        select * from team where owner_id = ?
    """;

    //language=sql
    private static final String SQL_SAVE_TEAM = """
        insert into team (name, owner_id, password) values (?, ?, ?);
    """;

    //language=sql
    private static final String SQL_SAVE_TEAM_USER = """
        insert into team_user (user_id, team_id) values (?, ?);
    """;

    //language=sql
    private static final String SQL_UPDATE_BY_ID = """
        update team set name = ?, password = ? where id = ?;
    """;


    @Override
    public void update(Team team) {
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
            statement.setString(1, team.getName());
            statement.setString(2, team.getPassword());
            statement.setLong(3, team.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Team getById(Long id) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_TEAM_BY_ID);
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
    public Long getIdByUserIdAndName(Long ownerId, String name) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_TEAM_BY_USER_ID_AND_NAME);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, ownerId);
            statement.setString(2, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Team team) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_SAVE_TEAM);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setString(1, team.getName());
            statement.setLong(2, team.getOwnerId());
            statement.setString(3, team.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isTeamExist(Long ownerId, String name) {
        Long longResult = getIdByUserIdAndName(ownerId, name);
        if (longResult == null) {
            return false;
        }
        return longResult >= 1;
    }

    @Override
    public void saveTeamUser(Long userId, Long teamId) {
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_SAVE_TEAM_USER);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setLong(1, userId);
            statement.setLong(2, teamId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getTeamsByUserId(Long id) {
        List<Team> teams = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_TEAMS_BY_OWNER_ID);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);

            statement.setLong(1, id);

            return mapper.mapListEntities(statement, teams);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getTeamsByUserIdFromTeamUser(Long id) {
        List<Team> teams = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection()
                    .map(connection -> {
                        try {
                            return connection.prepareStatement(SQL_GET_TEAMS_BY_OWNER_ID_FROM_TEAM_USER);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(RuntimeException::new);
            statement.setLong(1, id);

            return mapper.mapListEntities(statement, teams);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
