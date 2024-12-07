package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private String name;
    private String lastname;
    private String login;
    private String description;
    private String photoUrl;
}
