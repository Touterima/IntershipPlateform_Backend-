package tn.pidev.internshipoffer.controllers;

import tn.pidev.internshipoffer.Services.SavedOfferService;
import tn.pidev.internshipoffer.entities.SavedOffer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saved-offers")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Slf4j
public class SavedOfferController {
    private final SavedOfferService savedOfferService;

    // Enregistrer ou retirer une offre des favoris
    @PostMapping("/{userId}/{offreId}")
    public ResponseEntity<String> saveOrUnsaveOffer(@PathVariable Long userId, @PathVariable Long offreId) {
        savedOfferService.saveOrUnsaveOffer(userId, offreId);
        return ResponseEntity.ok("Action enregistrée avec succès !");
    }

    // Récupérer la liste des offres enregistrées par un utilisateur
    @GetMapping("/{userId}")
    public ResponseEntity<List<SavedOffer>> getSavedOffers(@PathVariable Long userId) {
        List<SavedOffer> savedOffers = savedOfferService.getSavedOffers(userId);
        return ResponseEntity.ok(savedOffers);
    }
}
