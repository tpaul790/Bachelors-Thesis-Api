package com.ubb.synergy.project;

import com.ubb.synergy.project.dto.CreateProjectDto;
import com.ubb.synergy.project.dto.ProjectDto;
import com.ubb.synergy.project.exception.ProjectAlreadyExistException;
import com.ubb.synergy.project.exception.ProjectNotFoundException;
import com.ubb.synergy.project.projection.ProjectSummaryProjection;
import com.ubb.synergy.team.TeamEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;
    private final EntityManager entityManager;

    public ProjectDto saveProject(CreateProjectDto projectDto) {
        if(projectRepository.existsByName(projectDto.getName())){
            throw new ProjectAlreadyExistException();
        }
        ProjectEntity project = new ProjectEntity();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setTeam(entityManager.getReference(TeamEntity.class, projectDto.getTeamId()));
        project.setCreatedAt(LocalDateTime.now());

        return mapper.entityToDto(projectRepository.save(project));
    }

    public List<ProjectDto> findAll(){
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        return mapper.entityToDto(projectEntities);
    }

    public List<ProjectSummaryProjection> findAllProjectsByUserId(Long id) {
        return projectRepository.findAllProjectsByUserId(id);
    }

    public void deleteProject(Long id) {
        if(!projectRepository.existsById(id)){
            throw new ProjectNotFoundException();
        }
        projectRepository.deleteById(id);
    }
}
