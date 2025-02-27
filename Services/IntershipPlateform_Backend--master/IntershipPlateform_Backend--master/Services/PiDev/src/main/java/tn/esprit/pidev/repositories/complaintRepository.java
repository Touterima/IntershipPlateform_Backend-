package tn.esprit.pidev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Complaint;
@Repository
public interface complaintRepository extends JpaRepository<Complaint,Long> {
}
