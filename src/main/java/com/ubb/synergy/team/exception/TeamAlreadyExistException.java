package com.ubb.synergy.team.exception;

public class TeamAlreadyExistException extends RuntimeException {
    public TeamAlreadyExistException() {
        super("Team with this name already exists");
    }
}
