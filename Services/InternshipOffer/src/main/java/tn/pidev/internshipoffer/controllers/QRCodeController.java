package tn.pidev.internshipoffer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tn.pidev.internshipoffer.Services.QRCodeService;
import tn.pidev.internshipoffer.entities.OffreDeStage;
import tn.pidev.internshipoffer.repository.OffreDeStageRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequiredArgsConstructor
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private OffreDeStageRepository offerRepository;


    @GetMapping("/api/offer/{offerId}/qrcode")
    public ResponseEntity<byte[]> getQRCode(@PathVariable Long offerId) {
        try {
            OffreDeStage offer = offerRepository.findById(offerId).orElseThrow(() -> new RuntimeException("Offre not found"));

            byte[] qrCode = qrCodeService.generateQRCodeImage(offer);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
