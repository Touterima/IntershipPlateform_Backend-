package tn.pidev.internshipoffer.controllers;

import tn.pidev.internshipoffer.Services.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.pidev.internshipoffer.enums.ReactionType;

@RestController
@RequestMapping("/offres/reactions")
@RequiredArgsConstructor
public class ReactionController {
    private final ReactionService reactionService;

    @PostMapping("/{userId}/{offreId}/like")
    public ResponseEntity<?> like(@PathVariable Long userId, @PathVariable Long offreId) {
        reactionService.likeOrDislike(userId, offreId, ReactionType.LIKE);
        return ResponseEntity.ok("Like enregistré !");
    }

    @PostMapping("/{userId}/{offreId}/dislike")
    public ResponseEntity<?> dislike(@PathVariable Long userId, @PathVariable Long offreId) {
        reactionService.likeOrDislike(userId, offreId, ReactionType.DISLIKE);
        return ResponseEntity.ok("Dislike enregistré !");
    }

    @GetMapping("/{offreId}/likes")
    public ResponseEntity<Long> getLikes(@PathVariable Long offreId) {
        return ResponseEntity.ok(reactionService.countLikes(offreId));
    }

    @GetMapping("/{offreId}/dislikes")
    public ResponseEntity<Long> getDislikes(@PathVariable Long offreId) {
        return ResponseEntity.ok(reactionService.countDislikes(offreId));
    }
}

