package dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class UserEditDto {
    private Long id;
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String description;
    private String photoUrl;
}