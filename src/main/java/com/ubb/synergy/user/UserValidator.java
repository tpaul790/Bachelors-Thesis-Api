package com.ubb.synergy.user;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    public String validate(UserEntity user){
        StringBuilder errors = new StringBuilder();
        errors
                .append(validateName(user))
                .append(validateEmail(user))
                .append(validatePassword(user))
                .append(validateUsername(user))
                .append(validateUserRole(user))
                .append(validateIconNumber(user));
        if(!errors.isEmpty()){
            return errors.toString();
        }
        return "";
    }

    /**
     * Validate the role of the given user
     * - ensuers that the role is not null and is one of admin or user
     * @param user
     * @return the error message if the role is not valid or an empty string otherwise
     */
    private String validateUserRole(UserEntity user) {
        if(user.getUserRole() == null ||
                (user.getUserRole() != UserRole.USER && user.getUserRole() != UserRole.ADMIN)){
            return "Invalid user role";
        }
        return "";
    }

    /**
     * Validate the name of the given user
     * - ensuers that the first and last name are not empty or null
     * @param user
     * @return the error message if the name is not valid or an empty string otherwise
     */
    private String validateName(UserEntity user){
        if(user.getFirstName()==null || user.getFirstName().isEmpty()){
            return "First name is required! ";
        }
        if(user.getLastName()==null || user.getLastName().isEmpty()){
            return "Last name is required! ";
        }
        return "";
    }


    /**
     * Validate the email adress of the given user
     *  - ensures that the email is not null or empty
     *  - ensures that the value has an email format
     *      - some text before @
     *      - some text after @, and a '.' after that text followed by other text
     * @param user
     * @return the error message if the email is invalid or an empty string otherwise
     */
    private String validateEmail(UserEntity user){
        if(user.getEmail()==null || user.getEmail().isEmpty()){
            return "Email is required! ";
        }
        Pattern pattern = Pattern.compile("[a-zA-z][a-zA-Z0-9._-]*@[a-zA-z]+\\.[a-zA-Z.]+");
        Matcher matcher = pattern.matcher(user.getEmail());
        if(!matcher.matches()){
            return "Invalid email! ";
        }
        return "";
    }

    /**
     * Validate the password of the given user
     * - ensuers that the password is not null or empty, and has at least 4 characters
     * @param user
     * @return the error message if the password is invalid or an empty string otherwise
     */
    private String validatePassword(UserEntity user){
        if(user.getPassword()==null || user.getPassword().isEmpty())
            return "Password is required! ";

        if(user.getPassword().length() < 4)
            return "Invalid password. Shoud be at least 4 characters! ";

        return "";
    }

    /**
     * Validate the username of the given user
     * - ensuers that the username is not empty or null and has at least 4 characters
     * @param user
     * @return the error message if the username is not valid or an empty string otherwise
     */
    private String validateUsername(UserEntity user){
        if(user.getUsername()==null || user.getUsername().isEmpty())
            return "Username is required! ";
        if(user.getUsername().length() < 4)
            return "Invalid username. Shoud be at least 4 characters! ";
        return "";
    }

    /**
     * Validate the iconNumber of the given user
     * - ensurers that the iconNumber is between [0,52] or not null
     * @param user
     * @return the error message if the iconNumber is not valid
     */
    private String validateIconNumber(UserEntity user){
        if(user.getIconNumber() == null){
            return "Icon number cannot be null";
        }
        if(user.getIconNumber() < 0){
            return "Icon number is invalid! ";
        }
        return "";
    }
}
