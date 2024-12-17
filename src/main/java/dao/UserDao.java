package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllByTeamId(Long teamId);

    User get(Long id);

    User getByLogin(String login);

    void save(User user);

    void delete(Long id);

    void updateInfo(User user);

    void updatePassword(String password, String login);

    void saveMediaUrl(String url, Long id);

    String getPasswordByLogin(String login);

    String getPhotoUrl(String login);
}
