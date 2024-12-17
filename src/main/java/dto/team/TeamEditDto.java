package dto.team;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamEditDto {
    private Long id;
    private String name;
    private Long ownerId;
    private String password;
}
