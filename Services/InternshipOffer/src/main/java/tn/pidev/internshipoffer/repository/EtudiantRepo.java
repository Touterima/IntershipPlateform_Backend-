package tn.pidev.internshipoffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pidev.internshipoffer.entities.Etudiant;

@Repository
public interface EtudiantRepo extends JpaRepository<Etudiant, Long> {
}
