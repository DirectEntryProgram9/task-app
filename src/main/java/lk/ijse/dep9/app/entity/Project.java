package lk.ijse.dep9.app.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project implements SuperEntity {

    private int id;
    private String name;
    private User user;

    public Project(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
