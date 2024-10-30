package uk.ac.UserAuthService.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.ac.UserAuthService.dtos.UserDTo;
import uk.ac.UserAuthService.models.User;
import uk.ac.UserAuthService.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final ModelMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTo> findAll(){
        return userService.findAll();
    }

    @GetMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTo findByUsername(@PathVariable String username){
        return mapper.map(userService.findByUsername(username), UserDTo.class);
    }

    @PostMapping
    public UserDTo addUser(UserDTo userDTo){
        return mapper.map(userService.saveUser(userDTo), UserDTo.class);
    }

    @PutMapping("{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTo updateUser(@PathVariable UUID userId, @RequestBody UserDTo userDTo){
        return mapper.map(userService.updateUser(userId,userDTo), UserDTo.class);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
    }


}
