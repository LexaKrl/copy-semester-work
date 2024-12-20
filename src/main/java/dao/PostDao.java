package dao;

import entity.Post;

import java.util.List;

public interface PostDao {

    List<Post> getAllByProjectId(Long projectId);

    Post getById(Long postId);

    List<Post> getAllByUserId(Long userId);

    List<Post> getAllByStatus(boolean b);

    void save(Post post);

    String getPhotoUrl(Long postId);

    void savePhotoUrl(String photoUrl, Long postId);

    void delete(Long id);

    boolean isAssignee(Long userId, Long postId);

    void update(Post post);
}
