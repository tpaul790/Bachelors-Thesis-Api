package com.ubb.synergy.project.exception;

public class ProjectAlreadyExistException extends RuntimeException {
    public ProjectAlreadyExistException() {
        super("Project with this name already exists");
    }
}
