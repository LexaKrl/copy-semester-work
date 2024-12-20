package dto.post;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;

    private String name;

    private Long projectId;

    private Long assigneeId;

    private String description;

    private boolean completed;

    private String photoUrl;
}