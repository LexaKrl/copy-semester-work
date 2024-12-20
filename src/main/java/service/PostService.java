package service;

import dto.post.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllByProjectId(Long projectId);

    PostDto getById(Long postId);

    boolean isInProject(Long postId, Long projectId);

    List<PostDto> getAllByUserId(Long userId);

    List<PostDto> getAllByStatus(boolean b);

    void post(PostDto post);

    String getPhotoUrl(Long postId);

    void savePhotoUrl(String photoUrl, Long postId);

    void delete(Long id);

    boolean isAssignee(Long userId, Long postId);

    void update(PostDto post);
}
