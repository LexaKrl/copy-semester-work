package entity;

import lombok.*;

import java.util.List;

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

    private Integer employeeNum;

    private List<User> employees;

    private String description;

    private List<User> admins;

    private List<Post> postList;
}
