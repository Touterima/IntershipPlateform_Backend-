package tn.pidev.internshipoffer.Services.IServices;

import tn.pidev.internshipoffer.entities.SavedOffer;

import java.util.List;

public interface ISavedOfferService {
    void saveOrUnsaveOffer(Long userId, Long offreId);
    List<SavedOffer> getSavedOffers(Long userId);
}
