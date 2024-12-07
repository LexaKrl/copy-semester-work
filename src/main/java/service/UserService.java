package service;

import dto.UserDto;
import dto.UserRegistrationDto;


public interface UserService {
    UserDto get(Long id);
    UserDto getByLogin(String login);
    void register(UserRegistrationDto user);
}