package com.ubb.synergy.member.dto;

import com.ubb.synergy.member.MemberRole;
import com.ubb.synergy.team.dto.TeamDto;
import com.ubb.synergy.user.dto.UserDto;
import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private UserDto user;
    private TeamDto team;
    private MemberRole role;
}
