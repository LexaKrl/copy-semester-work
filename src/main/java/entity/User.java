package entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private String name;

    private String lastname;

    private String login;

    private String password;

    private String description;

    private String photoUrl;
}
