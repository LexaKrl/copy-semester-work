package dao;

import entity.User;

public interface UserDao {

    User get(Long id);

    User getByLogin(String login);

    void save(User user);

    void delete(Long id);
}
