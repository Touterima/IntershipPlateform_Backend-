package tn.pidev.internshipoffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pidev.internshipoffer.entities.OffreDeStage;

import java.util.List;

@Repository
public interface OffreDeStageRepository extends JpaRepository<OffreDeStage, Long>{
    List<OffreDeStage> findByDomaine(String domaine);
    List<OffreDeStage> findByLocalisation(String localisation);
    List<OffreDeStage> findByEntreprise(String entreprise);
}
