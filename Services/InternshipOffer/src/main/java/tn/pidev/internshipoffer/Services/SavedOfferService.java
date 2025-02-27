package tn.pidev.internshipoffer.Services;

import tn.pidev.internshipoffer.Services.IServices.ISavedOfferService;
import tn.pidev.internshipoffer.entities.SavedOffer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.pidev.internshipoffer.repository.SavedOfferRepo;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SavedOfferService implements ISavedOfferService {
    private final SavedOfferRepo savedOfferRepository;


    public void saveOrUnsaveOffer(Long userId, Long offreId) {
        Optional<SavedOffer> existingSavedOffer = savedOfferRepository.findByUserIdAndOffreId(userId, offreId);

        if (existingSavedOffer.isPresent()) {
            savedOfferRepository.delete(existingSavedOffer.get());
        } else {
            SavedOffer savedOffer = new SavedOffer();
            savedOffer.setUserId(userId);
            savedOffer.setOffreId(offreId);
            savedOfferRepository.save(savedOffer);
        }
    }


    public List<SavedOffer> getSavedOffers(Long userId) {
        return savedOfferRepository.findByUserId(userId);
    }
}
