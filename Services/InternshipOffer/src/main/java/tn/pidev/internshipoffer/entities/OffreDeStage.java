package tn.pidev.internshipoffer.entities;

import tn.pidev.internshipoffer.enums.OfferStatus;
import tn.pidev.internshipoffer.enums.WorkMode;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffreDeStage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String entreprise;
    private String entrepriseLogo;// Or you can create an Entreprise entity
    private String domaine;
    private String localisation;
    private String typeDeStage; // e.g., "Stage d'été", "Stage de fin d'études"
    private LocalDate dateDebut;
    private Integer dureeEnMois;
    private String competencesRequises;
    private String contactEmail;
    private String source; // e.g., "LinkedIn", "Entreprise"
    private LocalDate datePublication;

    @Transient // Not persisted in the database
    private Long likeCount;

    @Transient // Not persisted in the database
    private Long dislikeCount;

    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Interaction> interactions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;
}
