package service;

import dto.team.TeamDto;
import dto.team.TeamEditDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> retrieveTeamListWhereUserMember(Long memberId);
    List<TeamDto> retrieveTeamListWhereUserOwner(Long ownerId);
    TeamDto retrieveTeamById(Long id);
    String retrievePassword(Long id);
    boolean teamExist(Long ownerId, String teamName);
    boolean teamExist(Long teamId);
    void update(TeamEditDto team);
    void register(TeamEditDto team);
    void joinTeam(Long userId, Long teamId);
}
