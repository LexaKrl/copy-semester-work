package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import dto.UserDto;
import dto.UserRegistrationDto;
import entity.User;
import helpers.PasswordHelper;
import service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao USER_DAO = new UserDaoImpl();

    @Override
    public UserDto get(Long id) {
        User u = USER_DAO.get(id);
        if (u == null) {
            return null;
        }
        return new UserDto(u.getName(), u.getLastname(), u.getLogin(), u.getDescription(), u.getPhotoUrl());
    }

    @Override
    public UserDto getByLogin(String login) {
        User u = USER_DAO.getByLogin(login);
        if (u == null) {
            return null;
        }
        return new UserDto(u.getName(), u.getLogin(), u.getLogin(), u.getDescription(), u.getPhotoUrl());
    }

    @Override
    public void register(UserRegistrationDto user) {
        USER_DAO.save(User.builder()
                .name(user.getName())
                .lastname(user.getLastname())
                .login(user.getLogin())
                .password(PasswordHelper.encrypt(user.getPassword()))
                .build()
        );
    }
}
