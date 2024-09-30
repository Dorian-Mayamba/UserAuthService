package uk.ac.UserAuthService.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uk.ac.UserAuthService.dtos.UserDTo;
import uk.ac.UserAuthService.response.LoginResponse;
import uk.ac.UserAuthService.response.RegisterResponse;
import uk.ac.UserAuthService.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register to the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Registration successful",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RegisterResponse.class))}
            ),
            @ApiResponse(responseCode = "403", description = "Forbidden, you have already registered",
                content = @Content),
    })

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody UserDTo userDTo){
        return authService.register(userDTo);
    }

    @Operation(summary = "Login to the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginResponse.class)
                )}
            ),
            @ApiResponse(responseCode = "404", description = "Username not found",
                content = {@Content}
            )
    })
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(Authentication authentication){
        return authService.login(authentication);
    }
}
