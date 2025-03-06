package tn.pidev.internshipoffer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.pidev.internshipoffer.Services.OffreDeStageService;
import tn.pidev.internshipoffer.Services.QRCodeService;
import tn.pidev.internshipoffer.entities.OffreDeStage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/offres")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
public class OffreDeStageController {

    private final OffreDeStageService offreDeStageService;
    private final QRCodeService qrCodeService;

    @PostMapping("/addInternshipOffer")
    public ResponseEntity<OffreDeStage> createOffre(@RequestBody OffreDeStage offreDeStage) {
        try {
            // Crée l'offre de stage via le service
            OffreDeStage createdOffre = offreDeStageService.createOffre(offreDeStage);

            // Retourne la réponse avec le statut 201 (Created) et l'objet créé
            return new ResponseEntity<>(createdOffre, HttpStatus.CREATED);
        } catch (Exception e) {
            // En cas d'erreur, retourne un statut 500 (Internal Server Error)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


/*
    @PostMapping("/addIntershipOffer")
    public ResponseEntity<OffreDeStage> createOffre(
            @RequestPart("entrepriseLogo") MultipartFile logo,
            @RequestPart(value = "offre", required = true) String offreJson
    ) {
        try {
            // Convert JSON string to OffreDeStage object
            ObjectMapper objectMapper = new ObjectMapper();
            OffreDeStage offre = objectMapper.readValue(offreJson, OffreDeStage.class);

            // Process the logo
            String logoFileName = logo.getOriginalFilename();
            Path logoPath = Paths.get("logos", logoFileName);
            Files.copy(logo.getInputStream(), logoPath, StandardCopyOption.REPLACE_EXISTING);
            offre.setEntrepriseLogo(logoFileName);

            // Create the offer
            OffreDeStage createdOffre = offreDeStageService.createOffre(offre);
            return new ResponseEntity<>(createdOffre, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

    @GetMapping("/{id}")
    public ResponseEntity<OffreDeStage> getOffreById(@PathVariable Long id) {
        return offreDeStageService.getOffreById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OffreDeStage>> getAllOffres() {
        return ResponseEntity.ok(offreDeStageService.getAllOffres());
    }

    @PutMapping("/updateOffre/{id}")
    public ResponseEntity<OffreDeStage> updateOffre(@PathVariable Long id, @RequestBody OffreDeStage offreDetails) {
        try {
            OffreDeStage updatedOffre = offreDeStageService.updateOffre(id, offreDetails);
            return ResponseEntity.ok(updatedOffre);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        offreDeStageService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/domaine/{domaine}")
    public ResponseEntity<List<OffreDeStage>> getOffresByDomaine(@PathVariable String domaine) {
        return ResponseEntity.ok(offreDeStageService.getOffresByDomaine(domaine));
    }

    @GetMapping("/localisation/{localisation}")
    public ResponseEntity<List<OffreDeStage>> getOffresByLocalisation(@PathVariable String localisation) {
        return ResponseEntity.ok(offreDeStageService.getOffresByLocalisation(localisation));
    }

    @GetMapping("/entreprise/{entreprise}")
    public ResponseEntity<List<OffreDeStage>> getOffresByEntreprise(@PathVariable String entreprise) {
        return ResponseEntity.ok(offreDeStageService.getOffresByEntreprise(entreprise));
    }

    @PostMapping("/{offreId}/like/{etudiantId}")
    public ResponseEntity<Void> likeOffre(@PathVariable Long offreId, @PathVariable Long etudiantId) {
        offreDeStageService.likeOffre(offreId, etudiantId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{offreId}/dislike/{etudiantId}")
    public ResponseEntity<Void> dislikeOffre(@PathVariable Long offreId, @PathVariable Long etudiantId) {
        offreDeStageService.dislikeOffre(offreId, etudiantId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{offreId}/favorite/{etudiantId}")
    public ResponseEntity<Void> addToFavorites(@PathVariable Long offreId, @PathVariable Long etudiantId) {
        offreDeStageService.addToFavorites(offreId, etudiantId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/etudiant/{etudiantId}/favorites")
    public ResponseEntity<List<OffreDeStage>> getFavoriteOffresByEtudiantId(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(offreDeStageService.getFavoriteOffresByEtudiantId(etudiantId));
    }

    @GetMapping("/etudiant/{etudiantId}/liked")
    public ResponseEntity<List<OffreDeStage>> getLikedOffresByEtudiantId(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(offreDeStageService.getLikedOffresByEtudiantId(etudiantId));
    }

    @GetMapping("/etudiant/{etudiantId}/disliked")
    public ResponseEntity<List<OffreDeStage>> getDislikedOffresByEtudiantId(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(offreDeStageService.getDislikedOffresByEtudiantId(etudiantId));
    }

    @GetMapping("/{offreId}/like-count")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long offreId) {
        return ResponseEntity.ok(offreDeStageService.getLikeCount(offreId));
    }

    @GetMapping("/{offreId}/dislike-count")
    public ResponseEntity<Long> getDislikeCount(@PathVariable Long offreId) {
        return ResponseEntity.ok(offreDeStageService.getDislikeCount(offreId));
    }


    /*********************** QR Code *************************/

    @GetMapping("/{offerId}/qrcode")
    public ResponseEntity<byte[]> getQRCode(@PathVariable Long offerId) {
        try {
            OffreDeStage offer = offreDeStageService.getOffreById(offerId).orElseThrow(() -> new RuntimeException("Offre not found"));

            byte[] qrCode = qrCodeService.generateQRCodeImage(offer);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
