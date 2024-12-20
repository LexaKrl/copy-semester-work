package entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Long id;

    private String name;

    private Long projectOwnerId;

    private Long projectTeamId;

    private String description;
}
