package uk.ac.UserAuthService.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String message;
    private String accessToken;
    private String username;
    private UUID userId;
    private boolean isAuthenticated;
}
