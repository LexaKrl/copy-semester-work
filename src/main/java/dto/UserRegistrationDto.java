package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRegistrationDto {

    private String name;
    private String lastname;
    private String login;
    private String password;
}
