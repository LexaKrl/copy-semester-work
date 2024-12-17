package entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Long id;
    private String name;
    private Long ownerId;
    private String password;
}
