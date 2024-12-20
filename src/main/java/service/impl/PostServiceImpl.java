package service.impl;

import dao.PostDao;
import dto.post.PostDto;
import entity.Post;
import lombok.RequiredArgsConstructor;
import service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    @Override
    public List<PostDto> getAllByProjectId(Long projectId) {
        return postDao.getAllByProjectId(projectId).stream().map(
                post -> new PostDto(post.getId(), post.getName(), post.getProjectId(), post.getAssigneeId(), post.getDescription(), post.isCompleted(), post.getPhotoUrl())
        ).collect(Collectors.toList());
    }

    @Override
    public PostDto getById(Long postId) {
        Post post = postDao.getById(postId);
        if (post == null) {
            return null;
        }
        return PostDto.builder()
                .id(post.getId())
                .name(post.getName())
                .projectId(post.getProjectId())
                .assigneeId(post.getAssigneeId())
                .description(post.getDescription())
                .photoUrl(post.getPhotoUrl())
                .completed(post.isCompleted())
                .build();
    }

    @Override
    public boolean isInProject(Long postId, Long projectId) {
        Post post = postDao.getById(postId);
        if (post == null) {
            return false;
        }
        return post.getProjectId().equals(projectId);
    }

    @Override
    public List<PostDto> getAllByUserId(Long userId) {
        return postDao.getAllByUserId(userId).stream().map(
                post -> new PostDto(post.getId(), post.getName(), post.getProjectId(), post.getAssigneeId(), post.getDescription(), post.isCompleted(), post.getPhotoUrl())
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllByStatus(boolean b) {
        return postDao.getAllByStatus(b).stream().map(
                post -> new PostDto(post.getId(), post.getName(), post.getProjectId(), post.getAssigneeId(), post.getDescription(), post.isCompleted(), post.getPhotoUrl())
        ).collect(Collectors.toList());
    }

    @Override
    public void post(PostDto post) {
        postDao.save(Post.builder()
                .name(post.getName())
                .projectId(post.getProjectId())
                .assigneeId(post.getAssigneeId())
                .description(post.getDescription())
                .photoUrl(post.getPhotoUrl())
                .completed(post.isCompleted())
                .build()
        );
    }

    @Override
    public String getPhotoUrl(Long postId) {
        return postDao.getPhotoUrl(postId);
    }

    @Override
    public void savePhotoUrl(String photoUrl, Long postId) {
        postDao.savePhotoUrl(photoUrl, postId);
    }

    @Override
    public void delete(Long id) {
        postDao.delete(id);
    }

    @Override
    public boolean isAssignee(Long userId, Long postId) {
        return postDao.isAssignee(userId, postId);
    }

    @Override
    public void update(PostDto post) {
        postDao.update(Post.builder()
                        .id(post.getId())
                        .name(post.getName())
                        .description(post.getDescription())
                        .projectId(post.getProjectId())
                        .completed(post.isCompleted())
                        .build()
        );
    }
}
