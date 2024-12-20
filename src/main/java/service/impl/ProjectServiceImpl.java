package service.impl;

import dao.ProjectDao;
import dto.project.ProjectDto;
import entity.Project;
import lombok.RequiredArgsConstructor;
import service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;

    @Override
    public void leaveProject(Long userId, Long projectId) {
        projectDao.leaveProject(userId, projectId);
    }

    @Override
    public List<ProjectDto> getAllByTeamId(Long teamId) {
        return projectDao.getAllByTeamId(teamId).stream().map(
                project -> new ProjectDto(project.getId(), project.getName(), project.getProjectOwnerId(), project.getProjectTeamId(), project.getDescription())
        ).collect(Collectors.toList());
    }

    @Override
    public ProjectDto findById(Long projectId) {
        Project project = projectDao.getById(projectId);
        if (project == null) {
            return null;
        }
        return mapToDto(project);
    }

    @Override
    public ProjectDto findByName(String name) {
        Project project = projectDao.getByName(name);
        if (project == null) {
            return null;
        }
        return mapToDto(project);
    }

    @Override
    public void create(ProjectDto project) {
        projectDao.save(Project.builder()
                .id(project.getId())
                .name(project.getName())
                .projectOwnerId(project.getProjectOwnerId())
                .projectTeamId(project.getProjectTeamId())
                .description(project.getDescription())
                .build()
        );

        Project tmp = projectDao.getByName(project.getName());

        projectDao.joinProject(project.getProjectOwnerId(), tmp.getId());
    }

    @Override
    public void delete(Long projectId) {
        projectDao.delete(projectId);
    }

    @Override
    public void update(ProjectDto project) {
        projectDao.update(
                Project.builder()
                        .id(project.getId())
                        .name(project.getName())
                        .projectOwnerId(project.getProjectOwnerId())
                        .projectTeamId(project.getProjectTeamId())
                        .description(project.getDescription())
                        .build()
        );
    }

    @Override
    public void joinProject(Long userToDeleteId, Long projectId) {
        projectDao.joinProject(userToDeleteId, projectId);
    }

    private ProjectDto mapToDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .projectTeamId(project.getProjectTeamId())
                .projectOwnerId(project.getProjectOwnerId())
                .description(project.getDescription())
                .build();
    }
}
