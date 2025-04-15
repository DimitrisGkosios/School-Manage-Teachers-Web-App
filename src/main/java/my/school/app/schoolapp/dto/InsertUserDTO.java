package my.school.app.schoolapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InsertUserDTO extends BaseUserDTO {

    private String role;

    public InsertUserDTO(String username, String password, String confirmedPassword, String role) {
        super(username, password, confirmedPassword);
        this.role = role;
    }

}
