package uk.ac.UserAuthService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uk.ac.UserAuthService.dtos.UserDTo;
import uk.ac.UserAuthService.models.User;
import uk.ac.UserAuthService.response.LoginResponse;
import uk.ac.UserAuthService.response.RegisterResponse;
import uk.ac.UserAuthService.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final JwtUtil jwtUtil;

    public RegisterResponse register(UserDTo userDTo){
        userService.saveUser(userDTo);
        return RegisterResponse
                .builder().message("Thanks for creating an account with us")
                .build();
    }

    public LoginResponse login(Authentication authentication){
        String token = jwtUtil.getToken(authentication);
        User user = (User) authentication.getPrincipal();
        return LoginResponse.builder()
                .accessToken(token)
                .message("Hello "+ user.getFirstName())
                .isAuthenticated(true)
                .userId(user.getId())
                .username(user.toString())
                .build();
    }
}
