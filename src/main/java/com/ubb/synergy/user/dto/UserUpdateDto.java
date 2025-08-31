package com.ubb.synergy.user.dto;

import com.ubb.synergy.user.UserEntity;
import lombok.Data;

@Data
public class UserUpdateDto implements UpdatableUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer iconNumber;

    public UserUpdateDto() {}

    @Override
    public boolean applyUpdates(UserEntity user) {
        boolean usernameChanged = !user.getUsername().equals(this.username);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setIconNumber(iconNumber);
        return usernameChanged;
    }
}
