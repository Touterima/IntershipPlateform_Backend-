package tn.pidev.internshipoffer.Services.IServices;

import tn.pidev.internshipoffer.enums.ReactionType;

public interface IReactionService {
    void likeOrDislike(Long userId, Long offreId, ReactionType reactionType);
    long countLikes(Long offreId);
}
