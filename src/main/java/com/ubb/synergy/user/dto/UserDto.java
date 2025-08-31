package com.ubb.synergy.user.dto;

import com.ubb.synergy.user.UserRole;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private LocalDateTime lastActive;
    private Boolean active;
    private Integer iconNumber;
}
