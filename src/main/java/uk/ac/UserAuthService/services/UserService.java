package uk.ac.UserAuthService.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.ac.UserAuthService.dtos.UserDTo;
import uk.ac.UserAuthService.models.User;
import uk.ac.UserAuthService.repository.UserRepository;
import uk.ac.UserAuthService.util.JwtUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByEmail(username).
               orElse(null);
    }

    public List<UserDTo> findAll(){
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTo.class))
                .collect(Collectors.toList());
    }

    public User findById(UUID userId) throws UsernameNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(()->
                new UsernameNotFoundException("Could not find username with "+ userId));
        return user;
    }

    public User findByUsername(String username){
        return (User) loadUserByUsername(username);
    }

    public User updateUser(UUID userId, UserDTo userDTo){
        User user = findById(userId);
        mapper.map(userDTo, user);
        return userRepository.save(user);
    }

    public void deleteUser(UUID userId){
        User user = mapper.map(findById(userId), User.class);
        userRepository.delete(user);
    }

    public User saveUser(UserDTo userDTo){
        User user = (User) loadUserByUsername(userDTo.getEmail());
        if(user == null) {
            User newUser = mapper.map(userDTo, User.class);
            assert newUser != null : "New user cannot be null";
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            return userRepository.insert(newUser);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This user is already registered");
    }
}
