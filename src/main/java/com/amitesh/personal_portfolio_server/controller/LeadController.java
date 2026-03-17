package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.model.Lead;
import com.amitesh.personal_portfolio_server.service.LeadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LeadController {
    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping("/add-lead")
    public ResponseEntity<?> addLead(@RequestBody Lead lead){
        return leadService.addLead(lead);
    }
}
