package tn.pidev.internshipoffer.repository;

import tn.pidev.internshipoffer.entities.Reaction;
import tn.pidev.internshipoffer.enums.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction,Long> {
    Optional<Reaction> findByUserIdAndOffreId(Long userId, Long offreId);
    long countByOffreIdAndReactionType(Long offreId, ReactionType reactionType);
}
