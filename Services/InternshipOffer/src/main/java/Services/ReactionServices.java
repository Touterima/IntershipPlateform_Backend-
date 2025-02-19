package Services;

import Services.IServices.IReactionService;
import entities.Reaction;
import enums.ReactionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import repository.ReactionRepo;
//import entities.Reaction.reactionType;


import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReactionServices implements IReactionService {
    private final ReactionRepo reactionRepository;

    public void likeOrDislike(Long userId, Long offreId, ReactionType reactionType) {
        Optional<Reaction> existingReaction = reactionRepository.findByUserIdAndOffreId(userId, offreId);

        if (existingReaction.isPresent()) {
            Reaction reaction = existingReaction.get();
            if (reaction.getReactionType().equals(reactionType)) {
                reactionRepository.delete(reaction); // Supprimer si déjà liké/disliké
            } else {
                reaction.setReactionType(reactionType);
                reactionRepository.save(reaction);
            }
        } else {
            Reaction newReaction = new Reaction();
            newReaction.setUserId(userId);
            newReaction.setOffreId(offreId);
            newReaction.setReactionType(reactionType);
            reactionRepository.save(newReaction);
        }
    }

    public long countLikes(Long offreId) {
        return reactionRepository.countByOffreIdAndReactionType(offreId, ReactionType.LIKE);
    }

    public long countDislikes(Long offreId) {
        return reactionRepository.countByOffreIdAndReactionType(offreId, ReactionType.DISLIKE);
    }


}
