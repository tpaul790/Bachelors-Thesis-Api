package com.ubb.synergy.member.dto;

import com.ubb.synergy.member.MemberRole;
import lombok.Data;

@Data
public class CreateMemberDto {
    private Long userId;
    private Long teamId;
    private MemberRole role;
}
