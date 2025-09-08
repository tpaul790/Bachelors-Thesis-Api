package com.ubb.synergy.member.exception;

public class MemberAlreadyExistException extends RuntimeException {
    public MemberAlreadyExistException() {
        super("This user already is a member of this team");
    }
}
