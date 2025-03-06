package tn.pidev.internshipoffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pidev.internshipoffer.entities.Interaction;
import tn.pidev.internshipoffer.enums.InteractionType;

import java.util.List;

@Repository
public interface InteractionRepo extends JpaRepository<Interaction, Long> {
    List<Interaction> findByEtudiantIdAndType(Long etudiantId, InteractionType type);
    List<Interaction> findByOffreIdAndType(Long offreId, InteractionType type);

    Long countByOffreIdAndType(Long offreId, InteractionType type);
    Boolean existsByEtudiantIdAndOffreIdAndType(Long etudiantId, Long offreId, InteractionType type);
    }