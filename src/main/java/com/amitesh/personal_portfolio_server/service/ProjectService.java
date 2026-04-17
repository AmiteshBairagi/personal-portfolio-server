package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.dto.ProjectUpdateRequest;
import com.amitesh.personal_portfolio_server.model.Project;
import com.amitesh.personal_portfolio_server.repo.ProjectRepo;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final CloudinaryService cloudinaryService;

    public ProjectService(ProjectRepo projectRepo, CloudinaryService cloudinaryService) {
        this.projectRepo = projectRepo;
        this.cloudinaryService = cloudinaryService;
    }

    @Transactional
    public ResponseEntity<?> addProject(Project project) {
        try{
            project.setCreatedAt(LocalDateTime.now());
            project.setUpdatedAt(LocalDateTime.now());
            projectRepo.save(project);

            return ResponseEntity.status(200).body("Project Saved Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Project cannot be saved , something went wrong");
        }
    }

    public ResponseEntity<?> getAllProjects() {
        try{
            List<Project> projects = new ArrayList<>();
            projects = projectRepo.findAll();

            return ResponseEntity.status(200).body(projects);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Cannot get projects , something went wrong");
        }
    }

    @Transactional
    public ResponseEntity<?> deleteProject(String projectId) {
        try{
            projectRepo.deleteById(projectId);
            return ResponseEntity.status(200).body("Project deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Project cannot be deleted , something went wrong");
        }
    }


    public ResponseEntity<?> updateProject(ProjectUpdateRequest project, MultipartFile image, String projectId) {
        try{
            Project projectInDb = projectRepo.findById(projectId).orElseThrow(() -> new RuntimeException("No project found with the given id"));

            if(image != null && !image.isEmpty()){
                Map cloudinaryResponse = cloudinaryService.update(image,projectInDb.getImagePublicId());

                String imageUrl = cloudinaryResponse.get("secure_url").toString();
                String imagePublicId = cloudinaryResponse.get("public_id").toString();

                projectInDb.setImageUrl(imageUrl);
                projectInDb.setImagePublicId(imagePublicId);
            }

            if(project.getTitle() != null){
                projectInDb.setTitle(project.getTitle());
            }

            if(project.getDescription() != null){
                projectInDb.setDescription(project.getDescription());
            }

            if(project.getShortDescription() != null){
                projectInDb.setShortDescription(project.getShortDescription());
            }

            if(project.getTechnologies() != null){
                projectInDb.setTechnologies(project.getTechnologies());
            }

            if(project.getGithubUrl() != null){
                projectInDb.setGithubUrl(project.getGithubUrl());
            }

            if(project.getLiveUrl() != null){
                projectInDb.setLiveUrl(project.getLiveUrl());
            }

            if(project.getCategory() != null){
                projectInDb.setCategory(project.getCategory());
            }

            if(project.getFeatured() != null){
                projectInDb.setFeatured(project.getFeatured());
            }

            if(project.getDuration() != null){
                projectInDb.setDuration(project.getDuration());
            }

            if(project.getTeamSize() != null){
                projectInDb.setTeamSize(project.getTeamSize());
            }

            if(project.getSolution() != null){
                projectInDb.setSolution(project.getSolution());
            }

            if(project.getChallenges() != null){
                projectInDb.setChallenges(project.getChallenges());
            }

            if(project.getFeatures() != null){
                projectInDb.setFeatures(project.getFeatures());
            }

            projectInDb.setUpdatedAt(LocalDateTime.now());

            projectRepo.save(projectInDb);

            return ResponseEntity.status(200).body("Project Saved Successfully");


        } catch (Exception e) {
            return ResponseEntity.status(500).body("Project Cannot be updated");
        }
    }
}
