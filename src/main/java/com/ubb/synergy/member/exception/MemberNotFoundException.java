package com.ubb.synergy.member.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException() {
        super("Member not found");
    }
}
