package tn.pidev.internshipoffer.Services.IServices;
import tn.pidev.internshipoffer.entities.OffreDeStage;

import java.util.List;
import java.util.Optional;

public interface IOfferService {
    OffreDeStage createOffre(OffreDeStage offre);
    Optional<OffreDeStage> getOffreById(Long id);
    List<OffreDeStage> getAllOffres();
    OffreDeStage updateOffre(Long id, OffreDeStage offreDetails);
    void deleteOffre(Long id);
    List<OffreDeStage> getOffresByDomaine(String domaine);
    List<OffreDeStage> getOffresByLocalisation(String localisation);
    List<OffreDeStage> getOffresByEntreprise(String entreprise);
    void likeOffre(Long offreId, Long etudiantId);
    void dislikeOffre(Long offreId, Long etudiantId);
    void addToFavorites(Long offreId, Long etudiantId);
    List<OffreDeStage> getFavoriteOffresByEtudiantId(Long etudiantId);
    List<OffreDeStage> getLikedOffresByEtudiantId(Long etudiantId);
    List<OffreDeStage> getDislikedOffresByEtudiantId(Long etudiantId);
    Long getLikeCount(Long offreId);
    Long getDislikeCount(Long offreId);
}
