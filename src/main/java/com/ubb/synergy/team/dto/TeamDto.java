package com.ubb.synergy.team.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
