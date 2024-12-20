package service;

import dto.user.UserDto;
import dto.user.UserEditDto;
import dto.user.UserRegistrationDto;

import java.util.List;


public interface UserService {
    void delete(Long userId);
    List<UserDto> retrieveAllByTeamId(Long teamId);
    boolean isInTeam(Long userId, Long teamId);
    UserDto get(Long id);
    UserDto getByLogin(String login);
    void register(UserRegistrationDto user);
    void editInfo(UserEditDto user);
    void editPassword(String password, String login);
    void savePhotoUrl(String photoUrl, Long id);
    String retrievePassword(String login);
    UserEditDto getUserEditDto(String login);
    String getPhotoUrl(String login);

    void deleteFromTeamUser(Long userId);

    List<UserDto> retrieveAllByProjectId(Long projectId);
}
