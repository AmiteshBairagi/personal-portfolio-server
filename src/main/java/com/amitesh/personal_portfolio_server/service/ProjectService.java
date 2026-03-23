package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.model.Project;
import com.amitesh.personal_portfolio_server.repo.ProjectRepo;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
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


    @Transactional
    public ResponseEntity<?> updateProject(Project project) {
        try{
            Project projectInDb = projectRepo.findById(project.getId()).orElseThrow(() -> new RuntimeException("Project not found to update"));
            projectInDb.setUpdatedAt(LocalDateTime.now());
            projectInDb.setCategory(project.getCategory());
            projectInDb.setDuration(project.getDuration());
            projectInDb.setChallenges(project.getChallenges());
            projectInDb.setDescription(project.getDescription());
            projectInDb.setFeatured(project.getFeatured());
            projectInDb.setGithubUrl(project.getGithubUrl());
            projectInDb.setImageUrl(project.getImageUrl());
            projectInDb.setTitle(project.getTitle());
            projectInDb.setTechnologies(project.getTechnologies());
            projectInDb.setTeamSize(project.getTeamSize());
            projectInDb.setSolution(project.getSolution());
            projectInDb.setShortDescription(project.getShortDescription());
            projectInDb.setProblemStatement(project.getProblemStatement());
            projectInDb.setLiveUrl(project.getLiveUrl());
            projectInDb.setPublished(project.getPublished());


            projectRepo.save(projectInDb);

            return ResponseEntity.status(200).body("Project updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Project cannot be updated, something went wrong");
        }
    }
}
