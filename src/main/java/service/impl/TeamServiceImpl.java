package service.impl;

import dao.TeamDao;
import dto.team.TeamDto;
import dto.team.TeamEditDto;
import entity.Team;
import helpers.PasswordHelper;
import lombok.RequiredArgsConstructor;
import service.TeamService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamDao teamDao;

    @Override
    public void delete(Long id) {
        teamDao.delete(id);
        teamDao.deleteFromTeamUser(id);
    }

    @Override
    public List<TeamDto> getAllWhereUserMember(Long memberId) {
        return teamDao.getTeamsByUserIdFromTeamUser(memberId).stream().map(
                team -> new TeamDto(team.getId(), team.getName(), team.getOwnerId())
        ).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> getAllWhereUserOwner(Long ownerId) {
        return teamDao.getTeamsByUserId(ownerId).stream().map(
                team -> new TeamDto(team.getId(), team.getName(), team.getOwnerId())
        ).collect(Collectors.toList());
    }

    @Override
    public TeamDto findById(Long id) {
        Team team = teamDao.getById(id);
        if (team == null) {
            return null;
        }
        return TeamDto.builder()
                .name(team.getName())
                .id(team.getId())
                .ownerId(team.getOwnerId())
                .build();
    }

    @Override
    public String retrievePassword(Long id) {
        return teamDao.getById(id).getPassword();
    }

    @Override
    public boolean teamExist(Long ownerId, String teamName) {
        return teamDao.isTeamExist(ownerId, teamName);
    }

    @Override
    public boolean isOwner(Long userId, Long teamId) {
        return teamDao.getById(teamId).getOwnerId().equals(userId);
    }

    @Override
    public boolean teamExist(Long teamId) {
        return teamDao.getById(teamId) != null;
    }

    @Override
    public void update(TeamEditDto team) {
        teamDao.update(Team.builder()
                .name(team.getName())
                .id(team.getId())
                .build());
    }


    @Override
    public void register(TeamEditDto teamEdit) {
        Long ownerId = teamEdit.getOwnerId();

        Team team = Team.builder()
                .name(teamEdit.getName())
                .ownerId(ownerId)
                .password(PasswordHelper.encrypt(teamEdit.getPassword()))
                .build();
        teamDao.save(team);

        if (teamDao.isTeamExist(ownerId, teamEdit.getName())) {
            Long teamId = teamDao.getIdByUserIdAndName(ownerId, team.getName());
            teamDao.saveTeamUser(ownerId, teamId);
        } else {
            throw new RuntimeException("Unexpected teamId null");
        }
    }

    @Override
    public void joinTeam(Long userId, Long teamId) {
        teamDao.saveTeamUser(userId, teamId);
    }
}
