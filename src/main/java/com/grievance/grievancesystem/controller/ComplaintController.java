package com.grievance.grievancesystem.controller;

import com.grievance.grievancesystem.model.Complaint;
import com.grievance.grievancesystem.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")   // Allows requests from different ports like 63342
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepository;

    // Submit a complaint
    @PostMapping
    public Complaint submitComplaint(@RequestBody Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    // Get all complaints
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // Get complaint by ID
    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    // Update complaint status
    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody Complaint updatedComplaint) {
        Complaint complaint = complaintRepository.findById(id).orElse(null);

        if (complaint != null) {
            complaint.setStatus(updatedComplaint.getStatus());
            return complaintRepository.save(complaint);
        }

        return null;
    }

    // Delete complaint
    @DeleteMapping("/{id}")
    public String deleteComplaint(@PathVariable Long id) {
        complaintRepository.deleteById(id);
        return "Complaint deleted successfully";
    }
}