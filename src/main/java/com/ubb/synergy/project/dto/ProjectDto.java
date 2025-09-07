package com.ubb.synergy.project.dto;

import com.ubb.synergy.team.dto.TeamDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private TeamDto team;
}
