package uk.ac.UserAuthService.response;

import lombok.*;
import uk.ac.UserAuthService.dtos.UserDTo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String message;
}
