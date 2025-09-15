package com.ubb.synergy.user;

import com.ubb.synergy.project.projection.ProjectSummaryProjection;
import com.ubb.synergy.security.controller.LoginRequest;
import com.ubb.synergy.user.dto.AdminUpdateDto;
import com.ubb.synergy.user.dto.UpdatableUserDto;
import com.ubb.synergy.user.dto.UserDto;
import com.ubb.synergy.user.dto.UserUpdateDto;
import com.ubb.synergy.user.exception.InvalidUserException;
import com.ubb.synergy.user.exception.LoginFaildException;
import com.ubb.synergy.user.exception.UserAlreadyExistException;
import com.ubb.synergy.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator validator;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    public UserDto findUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return mapper.entityToDto(user);
    }

    public List<UserDto> findAllUsers() {
        return mapper.entityToDto(userRepository.findAllByOrderById());
    }

    public UserDto saveUser(UserDto userDto) {
        UserEntity user = mapper.dtoToEntity(userDto);
        String errors = validator.validate(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(errors);
        }
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException();
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return mapper.entityToDto(userRepository.save(user));
    }

    public <E extends UpdatableUserDto> UserDto updateUser(Long id, E userDto) {
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        boolean usernameChanged = userDto.applyUpdates(user);

        if(usernameChanged && userRepository.existsByUsername(user.getUsername())) {
            throw new InvalidUserException("Username already exists");
        }
        String errors = validator.validate(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(errors);
        }

        return mapper.entityToDto(userRepository.save(user));
    }


    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    public UserDto login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if(user == null) {
            throw new LoginFaildException("There is no user with the username: " + request.getUsername());
        }
        if(!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new LoginFaildException("Wrong password");
        }
        return mapper.entityToDto(user);
    }
}
