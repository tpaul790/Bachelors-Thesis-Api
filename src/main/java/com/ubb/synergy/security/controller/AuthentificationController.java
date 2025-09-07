package com.ubb.synergy.security.controller;

import com.ubb.synergy.security.config.JwtTokenService;
import com.ubb.synergy.user.UserRole;
import com.ubb.synergy.user.UserService;
import com.ubb.synergy.user.dto.UserDto;
import com.ubb.synergy.user.exception.InvalidUserException;
import com.ubb.synergy.user.exception.LoginFaildException;
import com.ubb.synergy.user.exception.UserAlreadyExistException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthentificationController {

    private  final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final PasswordEncoder encoder;

    @PostMapping(value = "/login")
    @SneakyThrows
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        UserDto user;
        try{
            user = userService.login(request);
        } catch (LoginFaildException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }

        String jwt = jwtTokenService.createJwtToken(user.getId(), user.getUsername(), user.getUserRole());
        Cookie cookie = new Cookie("auth-cookie",jwt);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("token", jwt));
    }

    @PostMapping(value = "/register")
    @SneakyThrows
    public ResponseEntity<?> register(@RequestBody UserDto user){
        try {
            user.setUserRole(UserRole.USER);
            return ResponseEntity.ok(userService.saveUser(user));
        }catch (InvalidUserException | UserAlreadyExistException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
