package service;

import dto.team.TeamDto;
import dto.team.TeamEditDto;

import java.util.List;

public interface TeamService {
    void delete(Long id);
    List<TeamDto> getAllWhereUserMember(Long memberId);
    List<TeamDto> getAllWhereUserOwner(Long ownerId);
    TeamDto findById(Long id);
    String retrievePassword(Long id);
    boolean teamExist(Long ownerId, String teamName);
    boolean isOwner(Long userId, Long teamId);
    boolean teamExist(Long teamId);
    void update(TeamEditDto team);
    void register(TeamEditDto team);
    void joinTeam(Long userId, Long teamId);
}
