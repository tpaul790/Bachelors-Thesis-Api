package com.ubb.synergy.project;

import com.ubb.synergy.project.dto.CreateProjectDto;
import com.ubb.synergy.project.dto.ProjectDto;
import com.ubb.synergy.project.exception.ProjectAlreadyExistException;
import com.ubb.synergy.project.exception.ProjectNotFoundException;
import com.ubb.synergy.project.projection.ProjectSummaryProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {this.projectService = projectService;}

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAllProjects(){
        return ResponseEntity.ok(projectService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> saveProject(@RequestBody CreateProjectDto projectDto) {
        try{
            return ResponseEntity.ok(projectService.saveProject(projectDto));
        }catch(ProjectAlreadyExistException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error",e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try{
            projectService.deleteProject(id);
            return ResponseEntity.ok().build();
        }catch (ProjectNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
