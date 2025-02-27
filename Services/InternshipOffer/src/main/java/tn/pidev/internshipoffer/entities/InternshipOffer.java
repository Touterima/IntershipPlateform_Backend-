package tn.pidev.internshipoffer.entities;

import tn.pidev.internshipoffer.enums.OfferStatus;
import tn.pidev.internshipoffer.enums.WorkMode;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternshipOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String EntrepriseName; // à voir
    private String adresseOffre;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    private float salair;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;
}
