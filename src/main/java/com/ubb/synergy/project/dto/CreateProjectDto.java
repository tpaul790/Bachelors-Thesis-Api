package com.ubb.synergy.project.dto;
import lombok.Data;


@Data
public class CreateProjectDto {
    private String name;
    private String description;
    private Long teamId;
}
