package Services.IServices;

import enums.ReactionType;

public interface IReactionService {
    void likeOrDislike(Long userId, Long offreId, ReactionType reactionType);
    long countLikes(Long offreId);
}
