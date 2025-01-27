package tn.esprit.fundsphere.Entities.AssuranceManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; // Identifiant unique pour chaque flux de trésorerie
    double amount; // Montant du flux de trésorerie
    int year;      // Année du flux de trésorerie
}
