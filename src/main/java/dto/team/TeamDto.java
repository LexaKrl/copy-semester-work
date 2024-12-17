package dto.team;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private Long ownerId;
}
