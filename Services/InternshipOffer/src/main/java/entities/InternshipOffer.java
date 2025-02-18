package entities;

import enums.OfferStatus;
import enums.WorkMode;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

public class InternshipOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titreOffre;
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
