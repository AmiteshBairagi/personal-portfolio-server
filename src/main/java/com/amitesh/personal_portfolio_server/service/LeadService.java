package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.model.Lead;
import com.amitesh.personal_portfolio_server.repo.LeadRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    private final LeadRepo repo;

    public LeadService(LeadRepo repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> addLead(Lead lead) {
        try{
            lead.setCreatedAt(LocalDateTime.now());
            repo.save(lead);
            return ResponseEntity.status(200).body("Lead Saved");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Lead Cannot be saved , something went wrong");
        }
    }

    public ResponseEntity<?> getAllLeads() {
        try{
            List<Lead> leads = new ArrayList<>();
            leads = repo.findAll();
            return ResponseEntity.status(200).body(leads);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Cannon fetch leads , something went wrong");
        }
    }
}
