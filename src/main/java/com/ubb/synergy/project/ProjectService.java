package com.ubb.synergy.project;

import com.ubb.synergy.project.dto.CreateProjectDto;
import com.ubb.synergy.project.dto.ProjectDto;
import com.ubb.synergy.project.exception.ProjectAlreadyExistException;
import com.ubb.synergy.project.projection.ProjectSummaryProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;

    public ProjectDto saveProject(CreateProjectDto projectDto) {
        if(projectRepository.existsByName(projectDto.getName())){
            throw new ProjectAlreadyExistException();
        }
        ProjectEntity project = mapper.createProjectDtoToEntity(projectDto);
        return mapper.entityToDto(projectRepository.save(project));
    }

    public List<ProjectDto> findAll(){
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        return mapper.entityToDto(projectEntities);
    }

    public List<ProjectSummaryProjection> findAllProjectsByUserId(Long id) {
        return projectRepository.findAllProjectsByUserId(id);
    }
}
