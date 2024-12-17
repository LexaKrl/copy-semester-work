package dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String login;
    private String description;
    private String photoUrl;
}
