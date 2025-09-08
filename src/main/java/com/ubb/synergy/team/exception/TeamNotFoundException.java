package com.ubb.synergy.team.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException() {
        super("Team not found!");
    }
}
