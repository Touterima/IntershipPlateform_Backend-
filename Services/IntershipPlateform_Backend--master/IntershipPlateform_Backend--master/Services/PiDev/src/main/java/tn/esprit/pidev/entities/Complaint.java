package tn.esprit.pidev.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Complaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;

    private String evaluation;

    private String subject;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        SUBMITTED,
        BEING_TREATED,
        DONE
    }
}
