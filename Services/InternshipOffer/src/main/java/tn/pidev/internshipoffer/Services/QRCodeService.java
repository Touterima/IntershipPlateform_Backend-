package tn.pidev.internshipoffer.Services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.pidev.internshipoffer.entities.OffreDeStage;

import java.io.ByteArrayOutputStream;

@RequiredArgsConstructor
@Service
@Slf4j
public class QRCodeService {

    // Méthode pour générer une image QR code à partir de l'offre de stage
    public byte[] generateQRCodeImage(OffreDeStage offer) throws Exception {
        String offerInfo = createOfferQRCodeText(offer);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // Génération du QR code à partir du texte de l'offre
        BitMatrix bitMatrix = qrCodeWriter.encode(offerInfo, BarcodeFormat.QR_CODE, 200, 200);

        // Conversion du QR code en image PNG
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }

    // Méthode pour créer le texte qui sera codé dans le QR code
    private String createOfferQRCodeText(OffreDeStage offer) {
        StringBuilder sb = new StringBuilder();
        sb.append("Offre de Stage ID: ").append(offer.getId()).append("\n");
        sb.append("Titre: ").append(offer.getTitre()).append("\n");
        sb.append("Entreprise: ").append(offer.getEntreprise()).append("\n");
        sb.append("Domaine: ").append(offer.getDomaine()).append("\n");
        sb.append("Localisation: ").append(offer.getLocalisation()).append("\n");
        sb.append("Type de Stage: ").append(offer.getTypeDeStage()).append("\n");
        sb.append("Date de début: ").append(offer.getDateDebut()).append("\n");
        sb.append("Durée: ").append(offer.getDureeEnMois()).append(" mois\n");
        sb.append("Compétences requises: ").append(offer.getCompetencesRequises()).append("\n");
        sb.append("Email de contact: ").append(offer.getContactEmail()).append("\n");
        sb.append("Source: ").append(offer.getSource()).append("\n");
        sb.append("Date de publication: ").append(offer.getDatePublication()).append("\n");
        sb.append("Status: ").append(offer.getStatus()).append("\n");
        sb.append("Mode de travail: ").append(offer.getWorkMode()).append("\n");

        return sb.toString();
    }
}