package com.pidev.services;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pidev.entities.Application;
import com.pidev.entities.ApplicationStatus;
import com.pidev.repositories.ApplicationRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {


      private final ApplicationRepo applicationRepo;


    @Autowired
    private final Cloudinary cloudinary;

    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    // Constructeur pour initialiser Cloudinary et le service d'email
    public ApplicationServiceImpl(ApplicationRepo applicationRepo,
                                  @Value("${cloudinary.cloud.name}") String cloudName,
                                  @Value("${cloudinary.api.key}") String apiKey,
                                  @Value("${cloudinary.api.secret}") String apiSecret
                            ) {
        this.applicationRepo = applicationRepo;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret

        ));


    }

    /**
     * Télécharge un fichier vers Cloudinary.
     *
     * @param file Le fichier à télécharger.
     * @return L'URL du fichier téléchargé.
     * @throws IOException Si une erreur se produit lors du téléchargement.
     */
    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est requis");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("application/pdf") ||
                        contentType.equals("application/msword") ||
                        contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
            throw new IllegalArgumentException("Seuls les fichiers PDF et Word sont autorisés");
        }

        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "raw"));
        return uploadResult.get("public_id").toString();
    }

    /**
     * Envoie un email pour l'entretien d'une candidature.
     *
     * @param email          L'email du candidat.
     * @param candidateName  Le nom du candidat.
     * @param calendlyLink   Le lien Calendly pour l'entretien.
     */


    /**
     * Enregistre une nouvelle candidature.
     *
     * @param resume      Le CV de l'utilisateur.
     * @param coverLetter La lettre de motivation.
     * @param jobOfferId  L'ID de l'offre d'emploi.
     * @param userEmail   L'adresse e-mail de l'utilisateur.
     * @return La candidature enregistrée.
     * @throws IOException Si une erreur se produit lors du téléchargement des fichiers.
     */
    public Application submitApplication(MultipartFile resume, MultipartFile coverLetter, Long jobOfferId, String userEmail) throws IOException {
        log.info("Traitement de la candidature pour l'utilisateur {}", userEmail);

        String resumeUrl = uploadFile(resume);
        String coverLetterUrl = uploadFile(coverLetter);

        Application application = new Application(
                LocalDate.now(),
                ApplicationStatus.SUBMITTED,
                resumeUrl,
                coverLetterUrl,
                "Pending response",
                "Pending response",
                jobOfferId,
                userEmail
        );

        log.info("Enregistrement de la candidature pour l'utilisateur {}", userEmail);
        return applicationRepo.save(application);
    }

    /**
     * Met à jour le statut d'une candidature.
     *
     * @param id     L'ID de la candidature.
     * @param status Le nouveau statut de la candidature.
     * @return La candidature mise à jour.
     * @throws Exception Si la candidature n'est pas trouvée.
     */
    @Override
    public Application updateStatus(Long id, ApplicationStatus status) throws Exception {
        Application application = applicationRepo.findById(id)
                .orElseThrow(() -> new Exception("Application not found"));

        application.setStatus(status);


        return applicationRepo.save(application);
    }

    /**
     * Récupère toutes les candidatures.
     *
     * @return Une liste de toutes les candidatures.
     */
    @Override
    public List<Application> getAllApplications() {
        return applicationRepo.findAll();
    }

    /**
     * Supprime une candidature par son ID.
     *
     * @param id L'ID de la candidature à supprimer.
     */
    @Override
    public void deleteApplication(Long id) throws Exception {
        Application application = applicationRepo.findById(id)
                .orElseThrow(() -> new Exception("Application not found"));
        applicationRepo.deleteById(id);
    }

    /**
     * Récupère les statistiques sur les candidatures.
     *
     * @return Un map contenant le nombre total de candidatures, acceptées, rejetées et soumises.
     */
    public Map<String, Long> getStatisticsByUserEmail(String userEmail) {
        long totalApplications = applicationRepo.countByUserEmail(userEmail);
        long accepted = applicationRepo.countByStatusAndUserEmail(ApplicationStatus.ACCEPTED, userEmail);
        long rejected = applicationRepo.countByStatusAndUserEmail(ApplicationStatus.REJECTED, userEmail);
        long submitted = applicationRepo.countByStatusAndUserEmail(ApplicationStatus.SUBMITTED, userEmail);

        Map<String, Long> stats = new HashMap<>();
        stats.put("total", totalApplications);
        stats.put("accepted", accepted);
        stats.put("rejected", rejected);
        stats.put("submitted", submitted);
        return stats;
    }

    public Map<String, Long> getGeneralStatistics() {
        long totalApplications = applicationRepo.count();
        long accepted = applicationRepo.countByStatus(ApplicationStatus.ACCEPTED);
        long rejected = applicationRepo.countByStatus(ApplicationStatus.REJECTED);
        long submitted = applicationRepo.countByStatus(ApplicationStatus.SUBMITTED);

        Map<String, Long> stats = new HashMap<>();
        stats.put("total", totalApplications);
        stats.put("accepted", accepted);
        stats.put("rejected", rejected);
        stats.put("submitted", submitted);
        return stats;
    }
    @Override
    public List<Application> getApplicationsByUserEmail(String userEmail) {
        return applicationRepo.findByUserEmail(userEmail);
    }

    @Override
    public Map<String, Long> getStatistics(String userEmail) {
        long totalApplications;
        long accepted;
        long rejected;
        long submitted;

        if (userEmail == null || userEmail.isEmpty()) {
            // Admin statistics (all applications)
            totalApplications = applicationRepo.count();
            accepted = applicationRepo.countByStatus(ApplicationStatus.ACCEPTED);
            rejected = applicationRepo.countByStatus(ApplicationStatus.REJECTED);
            submitted = applicationRepo.countByStatus(ApplicationStatus.SUBMITTED);
        } else {
            // User-specific statistics
            totalApplications = applicationRepo.countByUserEmail(userEmail);
            accepted = applicationRepo.countByStatusAndUserEmail(ApplicationStatus.ACCEPTED, userEmail);
            rejected = applicationRepo.countByStatusAndUserEmail(ApplicationStatus.REJECTED, userEmail);
            submitted = applicationRepo.countByStatusAndUserEmail(ApplicationStatus.SUBMITTED, userEmail);
        }

        Map<String, Long> stats = new HashMap<>();
        stats.put("total", totalApplications);
        stats.put("accepted", accepted);
        stats.put("rejected", rejected);
        stats.put("submitted", submitted);
        return stats;
    }

//    @Override
//    public  void sendEmailForCandidate(String userEmail){
//
//        emailService.EnvoyerUnEmail(userEmail,"Interview Invitation ","template_Email");
//    }

}
