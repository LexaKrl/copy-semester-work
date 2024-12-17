package dao;

import dto.team.TeamEditDto;
import entity.Team;

import java.util.List;

public interface TeamDao {
    void update(Team team);
    Team getById(Long id);
    Long getIdByUserIdAndName(Long ownerId, String name);
    void save(Team team);
    boolean isTeamExist(Long ownerId, String teamName);
    void saveTeamUser(Long userId, Long teamId);
    List<Team> getTeamsByUserId(Long id);
    List<Team> getTeamsByUserIdFromTeamUser(Long id);
}
