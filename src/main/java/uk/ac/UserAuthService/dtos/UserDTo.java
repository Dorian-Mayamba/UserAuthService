package uk.ac.UserAuthService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTo {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
