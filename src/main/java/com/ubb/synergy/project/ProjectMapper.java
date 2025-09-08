package com.ubb.synergy.project;

import com.ubb.synergy.project.dto.CreateProjectDto;
import com.ubb.synergy.project.dto.ProjectDto;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    //ProjectDto
    ProjectDto entityToDto(ProjectEntity entity);

    List<ProjectDto> entityToDto(List<ProjectEntity> entities);

    ProjectEntity dtoToEntity(ProjectDto dto);

    //CreateProjectDto
    CreateProjectDto entitytoCreateDto(ProjectEntity entity);

    List<CreateProjectDto> entitytoCreateDto(List<ProjectEntity> entities);

    ProjectEntity createDtoToEntity(CreateProjectDto dto);
}
