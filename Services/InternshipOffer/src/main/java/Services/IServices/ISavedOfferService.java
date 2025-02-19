package Services.IServices;

import entities.SavedOffer;

import java.util.List;

public interface ISavedOfferService {
    void saveOrUnsaveOffer(Long userId, Long offreId);
    List<SavedOffer> getSavedOffers(Long userId);
}
