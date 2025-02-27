package tn.esprit.pidev.services;

import tn.esprit.pidev.entities.Complaint;

import java.util.List;

public interface complaintService {
    Complaint createComplaint(Complaint complaint);
    Complaint getComplaintById(Long id);
    List<Complaint> getAllComplaints();
    Complaint updateComplaint(Long id, Complaint updatedComplaint);
    void deleteComplaint(Long id);
}