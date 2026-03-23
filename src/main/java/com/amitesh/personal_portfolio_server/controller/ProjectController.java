package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.model.Project;
import com.amitesh.personal_portfolio_server.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/add-project")
    public ResponseEntity<?> addProject(@RequestBody Project project){
        return projectService.addProject(project);
    }

    @GetMapping("/get-all-projects")
    public ResponseEntity<?> getAllProjects(){
        return projectService.getAllProjects();
    }

    @DeleteMapping("/delete-project/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        return projectService.deleteProject(projectId);
    }

    @PutMapping("/update-project")
    public ResponseEntity<?> updateProject(@RequestBody Project project){
        return projectService.updateProject(project);
    }
}
