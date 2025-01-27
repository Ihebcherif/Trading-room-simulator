package tn.esprit.fundsphere.Entities.FormationManagement;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Formation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idFormation;

    String title;
    String content; // Contenu principal de la formation (ex. texte, HTML, Markdown)
    String description;
    boolean certifiante; // Indique si cette formation donne une certification

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Quiz> quizzes; // Liste de quiz associés à cette formation

    LocalDateTime dateCreation;
    LocalDateTime dateDerniereModification;
    String niveauDeDifficulte; // Exemple : "Débutant", "Intermédiaire", "Avancé"
}
