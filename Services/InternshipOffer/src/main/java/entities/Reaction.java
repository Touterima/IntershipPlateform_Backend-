package entities;

import enums.ReactionType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;  // ID de l'utilisateur provenant du UserService
    private Long offreId; // ID de l'offre (clé étrangère vers Offre)

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;



    // ✅ Ajout des getters et setters
    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

}
