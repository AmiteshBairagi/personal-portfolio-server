package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.model.Project;
import com.amitesh.personal_portfolio_server.service.CloudinaryService;
import com.amitesh.personal_portfolio_server.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;
    private final CloudinaryService cloudinaryService;

    public ProjectController(ProjectService projectService, CloudinaryService cloudinaryService) {
        this.projectService = projectService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping(value = "/add-project" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProject(@RequestPart("data") Project project ,
                                        @RequestPart(value = "image" , required = true)MultipartFile image){

        Map cloudinaryResponse = cloudinaryService.upload(image);
        String imageUrl = cloudinaryResponse.get("secure_url").toString();
        String imagePublicId = cloudinaryResponse.get("public_id").toString();
        project.setImageUrl(imageUrl);
        project.setImagePublicId(imagePublicId);

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
