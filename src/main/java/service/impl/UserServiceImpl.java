package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import dto.user.UserDto;
import dto.user.UserEditDto;
import dto.user.UserRegistrationDto;
import entity.User;
import helpers.PasswordHelper;
import mapper.Mapper;
import mapper.UserMapper;
import service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void delete(Long userId) {
        userDao.delete(userId);
    }

    @Override
    public List<UserDto> retrieveAllByTeamId(Long teamId) {
        return userDao.getAllByTeamId(teamId).stream().map(
                u -> new UserDto(u.getId(), u.getName(), u.getLastname(), u.getLogin(), u.getDescription(), u.getPhotoUrl())
        ).collect(Collectors.toList());
    }

    @Override
    public boolean isInTeam(Long userId, Long teamId) {
        return userDao.getAllByTeamId(teamId).stream().anyMatch(u -> u.getId().equals(userId));
    }

    @Override
    public UserDto get(Long id) {
        User u = userDao.get(id);
        if (u == null) {
            return null;
        }
        return new UserDto(u.getId(), u.getName(), u.getLastname(), u.getLogin(), u.getDescription(), u.getPhotoUrl());
    }

    @Override
    public UserDto getByLogin(String login) {
        User u = userDao.getByLogin(login);
        if (u == null) {
            return null;
        }
        return new UserDto(u.getId(), u.getName(), u.getLastname(), u.getLogin(), u.getDescription(), u.getPhotoUrl());
    }

    @Override
    public void register(UserRegistrationDto user) {
        userDao.save(User.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .login(user.getLogin())
                .password(PasswordHelper.encrypt(user.getPassword()))
                .build()
        );
    }

    @Override
    public void editInfo(UserEditDto user) {
        userDao.updateInfo(User.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .login(user.getLogin())
                .description(user.getDescription())
                .build());
    }

    @Override
    public void editPassword(String password, String login) {
        userDao.updatePassword(PasswordHelper.encrypt(password), login);
    }

    @Override
    public void savePhotoUrl(String photoUrl, Long id) {
        userDao.saveMediaUrl(photoUrl, id);
    }

    @Override
    public String retrievePassword(String login) {
        return userDao.getPasswordByLogin(login);
    }

    @Override
    public UserEditDto getUserEditDto(String login) {
        User u = userDao.getByLogin(login);
        if (u == null) {
            return null;
        }
        return UserEditDto.builder()
                .name(u.getName())
                .lastname(u.getLastname())
                .id(u.getId())
                .login(u.getLogin())
                .password(u.getPassword())
                .description(u.getDescription())
                .photoUrl(u.getPhotoUrl())
                .build();
    }

    @Override
    public String getPhotoUrl(String login) {
        return userDao.getPhotoUrl(login);
    }
}
