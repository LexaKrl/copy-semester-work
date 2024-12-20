package service;

import dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {

    void leaveProject(Long userId, Long projectId);


    List<ProjectDto> getAllByTeamId(Long teamId);

    ProjectDto findById(Long projectId);

    ProjectDto findByName(String name);

    void create(ProjectDto project);

    void delete(Long projectId);

    void update(ProjectDto project);

    void joinProject(Long userToDeleteId, Long projectId);
}
