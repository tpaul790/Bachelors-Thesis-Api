package com.ubb.synergy.user;

import com.ubb.synergy.user.dto.UserDto;
import com.ubb.synergy.user.exception.InvalidUserException;
import com.ubb.synergy.user.exception.UserAlreadyExistException;
import com.ubb.synergy.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator validator;
    private final UserMapper mapper;

    public List<UserDto> findAllUsers() {
        return mapper.entityToDto(userRepository.findAll());
    }

    public UserDto saveUser(UserDto userDto) {
        UserEntity user = mapper.dtoToEntity(userDto);
        //TODO - hash the passwords when implementing spring security
        String errors = validator.validate(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(errors);
        }
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException();
        }
        return mapper.entityToDto(userRepository.save(user));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        UserEntity user = mapper.dtoToEntity(userDto);
        String errors = validator.validate(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(errors);
        }
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        user.setId(id);
        return mapper.entityToDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

}
