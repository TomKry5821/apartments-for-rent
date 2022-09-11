package pl.polsl.krypczyk.apartmentsforrent.userservice.user.userdetails.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDetailsDTO {

    private String name;

    private String surname;

    private String email;

    private Boolean isActive;

    private String password;

    private LocalDateTime creationDate;
}
