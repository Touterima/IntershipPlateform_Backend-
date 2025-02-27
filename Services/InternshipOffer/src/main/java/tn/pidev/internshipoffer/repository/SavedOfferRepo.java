package tn.pidev.internshipoffer.repository;

import tn.pidev.internshipoffer.entities.SavedOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedOfferRepo extends JpaRepository<SavedOffer, Long> {
    Optional<SavedOffer> findByUserIdAndOffreId(Long userId, Long offreId);
    List<SavedOffer> findByUserId(Long userId);
}
