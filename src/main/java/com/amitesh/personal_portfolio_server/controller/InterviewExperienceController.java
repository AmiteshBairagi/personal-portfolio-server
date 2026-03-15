package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.model.InterviewExperience;
import com.amitesh.personal_portfolio_server.service.InterviewExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class InterviewExperienceController {
    private final InterviewExperienceService service;

    public InterviewExperienceController(InterviewExperienceService service) {
        this.service = service;
    }

    @GetMapping("/get-all-interview-experiences")
    public ResponseEntity<?> getAllInterviewExperiences(){
        return service.getAllInterviewExperiences();
    }

    @PostMapping("/add-interview-experience")
    public ResponseEntity<?> addInterviewExperience(@RequestBody InterviewExperience interviewExperience){
        return service.addInterviewExperience(interviewExperience);
    }

    @DeleteMapping("/delete-interview-experience/{id}")
    public ResponseEntity<?> deleteInterviewExperience(@PathVariable String id){
        return service.deleteInterviewExperience(id);
    }
}
