package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.model.InterviewExperience;
import com.amitesh.personal_portfolio_server.repo.InterviewExperienceRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewExperienceService {
    private final InterviewExperienceRepo repo;

    public InterviewExperienceService(InterviewExperienceRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public ResponseEntity<?> addInterviewExperience(InterviewExperience interviewExperience) {
        try{
            interviewExperience.setCreatedAt(LocalDateTime.now());
            repo.save(interviewExperience);
            return ResponseEntity.status(200).body("Data saved successfully");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
    @Transactional
    public ResponseEntity<?> getAllInterviewExperiences() {
        try{
            List<InterviewExperience> result = new ArrayList<>();
            result = repo.findAll();
            return ResponseEntity.status(200).body(result);
        }catch(Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
    @Transactional
    public ResponseEntity<?> deleteInterviewExperience(String id) {
        try{
            repo.deleteById(id);
            return ResponseEntity.status(200).body("Deleted");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Cannot be deleted . something went wromg");
        }
    }
}
