package dao;

import entity.Project;

import java.util.List;

public interface ProjectDao {
    List<Project> getAllByTeamId(Long id);

    Project getById(Long id);

    Project getByName(String name);

    void leaveProject(Long userId, Long projectId);

    void save(Project project);

    void delete(Long projectId);

    void update(Project project);

    void joinProject(Long userId, Long projectId);
}
