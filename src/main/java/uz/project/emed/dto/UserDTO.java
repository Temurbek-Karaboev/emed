package uz.project.emed.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long tgID;
    private String tgUsername;
    private String step;
    private boolean status;
}
