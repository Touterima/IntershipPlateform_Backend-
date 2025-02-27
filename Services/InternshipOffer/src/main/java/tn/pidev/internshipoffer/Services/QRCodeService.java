package tn.pidev.internshipoffer.Services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import tn.pidev.internshipoffer.entities.InternshipOffer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@RequiredArgsConstructor
@Service
@Slf4j
public class QRCodeService {

    public byte[] generateQRCodeImage(InternshipOffer offer) throws Exception {
        String offerInfo = createOfferQRCodeText(offer);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(offerInfo, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }

    private String createOfferQRCodeText(InternshipOffer offer) {
        StringBuilder sb = new StringBuilder();
        sb.append("InternshipOffer ID: ").append(offer.getId()).append("\n");
        sb.append("Title: ").append(offer.getTitle()).append("\n");
        sb.append("Entreprise: ").append(offer.getEntrepriseName()).append("\n");
        sb.append("adresseOffre: ").append(offer.getAdresseOffre()).append("\n");
        sb.append("description: ").append(offer.getDescription()).append("\n");
        sb.append("publishDate: ").append(offer.getPublishDate()).append("\n");
        sb.append("salair: ").append(offer.getSalair()).append("\n");
        sb.append("status: ").append(offer.getStatus()).append("\n");
        sb.append("workMode: ").append(offer.getWorkMode()).append("\n");


        return sb.toString();
    }


}
