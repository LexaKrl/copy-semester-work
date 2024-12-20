package entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;

    private String name;

    private Long projectId;

    private Long authorId;

    private Long assigneeId;

    private String description;

    private boolean completed;

    private String photoUrl;
}
