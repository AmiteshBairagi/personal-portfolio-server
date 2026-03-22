package com.amitesh.personal_portfolio_server.service;


import com.amitesh.personal_portfolio_server.model.Skill;
import com.amitesh.personal_portfolio_server.repo.SkillsRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillsService {

    private final SkillsRepo repo;

    public SkillsService(SkillsRepo repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> deleteSkill(String skillId) {
        try{
            repo.deleteById(skillId);
            return ResponseEntity.status(200).body("Skill deleted successfully");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Failed to delete item");
        }
    }

    public ResponseEntity<?> updateSkill(Skill skill) {
        try{
            Skill skillInDb = repo.findById(skill.getId()).orElseThrow(() -> new RuntimeException("skill not found"));
            skillInDb.setName(skill.getName());
            skillInDb.setProjects(skill.getProjects());
            skillInDb.setCategory(skill.getCategory());
            skillInDb.setYearsOfExperience(skill.getYearsOfExperience());
            skillInDb.setUpdatedAt(LocalDateTime.now());
            repo.save(skillInDb);
            return ResponseEntity.status(200).body("Skill updated successfully");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Skill cannot be updated, something went wrong");
        }
    }

    public ResponseEntity<?> addSkill(Skill skill) {
        try{
            skill.setCreatedAt(LocalDateTime.now());
            skill.setUpdatedAt(LocalDateTime.now());
            repo.save(skill);
            return ResponseEntity.status(200).body("Data Saved Successfully");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Failed to save data");
        }
    }

    public ResponseEntity<?> getAllSkills() {
        try{
            List<Skill> allSkills = new ArrayList<>();
            allSkills = repo.findAll();
            System.out.println(allSkills);
            return ResponseEntity.status(200).body(allSkills);
        }catch(Exception e){
            return ResponseEntity.status(500).body("Failed to fetch skills");
        }
    }
}
