package com.amitesh.personal_portfolio_server.controller;


import com.amitesh.personal_portfolio_server.model.Skill;
import com.amitesh.personal_portfolio_server.service.SkillsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SkillsController {

    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping("/get-all-skills")
    public ResponseEntity<?> getAllSkills(){
        return skillsService.getAllSkills();
    }

    @PostMapping("/add-skill")
    public ResponseEntity<?> addSkill(@RequestBody Skill skill){
        return skillsService.addSkill(skill);
    }


    @PutMapping("/update-skill/{skillId}")
    public ResponseEntity<?> updateSkill(@PathVariable String skillId){
        return skillsService.updateSkill(skillId);
    }

    @DeleteMapping("/delete-skill/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable String skillId){
        return skillsService.deleteSkill(skillId);
    }
}
