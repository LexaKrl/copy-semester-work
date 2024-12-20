package dto.project;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;

    private String name;

    private Long projectOwnerId;

    private Long projectTeamId;

    private String description;
}
