package com.ubb.synergy.security.exceptions;

public class AuthenticationCredentialsNotFoundException extends RuntimeException{
    public AuthenticationCredentialsNotFoundException(final String message){
        super(message);
    }
}
