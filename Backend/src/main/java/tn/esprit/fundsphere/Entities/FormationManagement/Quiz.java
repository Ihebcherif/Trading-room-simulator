package tn.esprit.fundsphere.Entities.FormationManagement;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idQuiz;

    String question;
    String reponseCorrecte; // La bonne réponse
    @ElementCollection
    List<String> options; // Liste des choix possibles

    @ManyToOne
    @JoinColumn(name = "formation_id")
    Formation formation; // Formation à laquelle ce quiz est rattaché
}
