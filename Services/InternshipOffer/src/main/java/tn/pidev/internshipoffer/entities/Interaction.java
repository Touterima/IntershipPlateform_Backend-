package tn.pidev.internshipoffer.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.pidev.internshipoffer.enums.InteractionType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offre_id")
    private OffreDeStage offre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @Enumerated(EnumType.STRING)
    private InteractionType type;

    private LocalDateTime interactionDate;
}
