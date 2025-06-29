package com.example.College.controller;

import com.example.College.dto.ProjectDto;
import com.example.College.entity.Projects;
import com.example.College.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("project")
public class ProjectController {


    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    private ResponseEntity<List<ProjectDto>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id){
        Optional<ProjectDto> projectDto = projectService.getProjectById(id);
        return projectDto
                .map(projectDto1 -> ResponseEntity.ok(projectDto1))
                .orElse(null);
    }

    @PostMapping
    private ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){
        return new ResponseEntity<>(projectService.createProject(projectDto), HttpStatus.CREATED) ;
    }

    @PutMapping("{empId}/assignProjectToEmployee/{projectId}")
    private ResponseEntity<ProjectDto> assignProjectToEmployee(@PathVariable Long empId, @PathVariable Long projectId){
            return ResponseEntity.ok(projectService.assignProjectToEmployee(empId,projectId));
    }}
