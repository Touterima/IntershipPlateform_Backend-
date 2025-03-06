package tn.pidev.internshipoffer.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.pidev.internshipoffer.Services.IServices.IOfferService;
import tn.pidev.internshipoffer.entities.Etudiant;
import tn.pidev.internshipoffer.entities.Interaction;
import tn.pidev.internshipoffer.entities.OffreDeStage;
import tn.pidev.internshipoffer.enums.InteractionType;
import tn.pidev.internshipoffer.repository.EtudiantRepo;
import tn.pidev.internshipoffer.repository.InteractionRepo;
import tn.pidev.internshipoffer.repository.OffreDeStageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OffreDeStageService implements IOfferService {

    private final OffreDeStageRepository offreDeStageRepository;
    private final EtudiantRepo etudiantRepository;
    private final InteractionRepo interactionRepository;

    @Override
    public OffreDeStage createOffre(OffreDeStage offre) {
        return offreDeStageRepository.save(offre);
    }

    @Override
    public Optional<OffreDeStage> getOffreById(Long id) {
        Optional<OffreDeStage> offreOptional = offreDeStageRepository.findById(id);
        offreOptional.ifPresent(this::setLikeDislikeCounts);
        return offreOptional;
    }

    @Override
    public List<OffreDeStage> getAllOffres() {
        List<OffreDeStage> offres = offreDeStageRepository.findAll();
        offres.forEach(this::setLikeDislikeCounts);
        return offres;
    }

    @Override
    public OffreDeStage updateOffre(Long id, OffreDeStage offreDetails) {
        OffreDeStage offre = offreDeStageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre not found with id " + id));

        offre.setTitre(offreDetails.getTitre());
        offre.setDescription(offreDetails.getDescription());
        offre.setDomaine(offreDetails.getDomaine());
        offre.setLocalisation(offreDetails.getLocalisation());
        offre.setTypeDeStage(offreDetails.getTypeDeStage());
        offre.setDateDebut(offreDetails.getDateDebut());
        offre.setDureeEnMois(offreDetails.getDureeEnMois());
        offre.setCompetencesRequises(offreDetails.getCompetencesRequises());
        offre.setContactEmail(offreDetails.getContactEmail());

        return offreDeStageRepository.save(offre);
    }

    @Override
    public void deleteOffre(Long id) {
        offreDeStageRepository.deleteById(id);
    }

    @Override
    public List<OffreDeStage> getOffresByDomaine(String domaine) {
        return getOffresWithCounts(offreDeStageRepository.findByDomaine(domaine));
    }

    @Override
    public List<OffreDeStage> getOffresByLocalisation(String localisation) {
        return getOffresWithCounts(offreDeStageRepository.findByLocalisation(localisation));
    }

    @Override
    public List<OffreDeStage> getOffresByEntreprise(String entreprise) {
        return getOffresWithCounts(offreDeStageRepository.findByEntreprise(entreprise));
    }

    @Transactional
    @Override
    public void likeOffre(Long offreId, Long etudiantId) {
        handleInteraction(offreId, etudiantId, InteractionType.LIKE);
    }

    @Transactional
    @Override
    public void dislikeOffre(Long offreId, Long etudiantId) {
        handleInteraction(offreId, etudiantId, InteractionType.DISLIKE);
    }

    @Transactional
    @Override
    public void addToFavorites(Long offreId, Long etudiantId) {
        handleInteraction(offreId, etudiantId, InteractionType.FAVORITE);
    }

    @Override
    public List<OffreDeStage> getFavoriteOffresByEtudiantId(Long etudiantId) {
        return getOffresByInteractionType(etudiantId, InteractionType.FAVORITE);
    }

    @Override
    public List<OffreDeStage> getLikedOffresByEtudiantId(Long etudiantId) {
        return getOffresByInteractionType(etudiantId, InteractionType.LIKE);
    }

    @Override
    public List<OffreDeStage> getDislikedOffresByEtudiantId(Long etudiantId) {
        return getOffresByInteractionType(etudiantId, InteractionType.DISLIKE);
    }

    @Override
    public Long getLikeCount(Long offreId) {
        return interactionRepository.countByOffreIdAndType(offreId, InteractionType.LIKE);
    }

    @Override
    public Long getDislikeCount(Long offreId) {
        return interactionRepository.countByOffreIdAndType(offreId, InteractionType.DISLIKE);
    }

    private void setLikeDislikeCounts(OffreDeStage offre) {
        offre.setLikeCount(interactionRepository.countByOffreIdAndType(offre.getId(), InteractionType.LIKE));
        offre.setDislikeCount(interactionRepository.countByOffreIdAndType(offre.getId(), InteractionType.DISLIKE));
    }

    private List<OffreDeStage> getOffresWithCounts(List<OffreDeStage> offres) {
        offres.forEach(this::setLikeDislikeCounts);
        return offres;
    }

    private void handleInteraction(Long offreId, Long etudiantId, InteractionType type) {
        OffreDeStage offre = offreDeStageRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offre not found with id " + offreId));
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id " + etudiantId));

        if (interactionRepository.existsByEtudiantIdAndOffreIdAndType(etudiantId, offreId, type)) {
            return;
        }

        Interaction interaction = new Interaction();
        interaction.setOffre(offre);
        interaction.setEtudiant(etudiant);
        interaction.setType(type);
        interaction.setInteractionDate(LocalDateTime.now());
        interactionRepository.save(interaction);

        setLikeDislikeCounts(offre);
        offreDeStageRepository.save(offre);
    }

    private List<OffreDeStage> getOffresByInteractionType(Long etudiantId, InteractionType type) {
        return interactionRepository.findByEtudiantIdAndType(etudiantId, type).stream()
                .map(Interaction::getOffre)
                .peek(this::setLikeDislikeCounts)
                .collect(Collectors.toList());
    }
}
