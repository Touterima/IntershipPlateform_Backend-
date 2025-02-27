package tn.esprit.pidev.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Complaint;
import tn.esprit.pidev.repositories.complaintRepository;
import tn.esprit.pidev.services.complaintService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class complaintServiceimpl implements complaintService {

    private final complaintRepository complaintRepository;

    @Override
    public Complaint createComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint updateComplaint(Long id, Complaint updatedComplaint) {
        Complaint complaint = getComplaintById(id);
        complaint.setUser(updatedComplaint.getUser());
        complaint.setEvaluation(updatedComplaint.getEvaluation());
        complaint.setSubject(updatedComplaint.getSubject());
        complaint.setStatus(updatedComplaint.getStatus());
        return complaintRepository.save(complaint);
    }

    @Override
    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}
