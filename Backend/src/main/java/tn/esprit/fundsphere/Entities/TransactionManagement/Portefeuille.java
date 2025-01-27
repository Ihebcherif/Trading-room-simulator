package tn.esprit.fundsphere.Entities.TransactionManagement;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import tn.esprit.fundsphere.Entities.AssuranceManagement.Contract;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Portefeuille {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idPortefeuille;

    // La valeur totale des actifs dans le portefeuille
    double totalValue;

    // Liquidité disponible dans le portefeuille (argent liquide)
    double liquidity;
    // Relation One-to-Many avec OrdreRepository (Un portefeuille peut avoir plusieurs ordres)
    @JsonManagedReference
    @OneToMany(mappedBy = "portefeuille", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ordre> ordres = new ArrayList<>();

    @OneToMany(mappedBy = "portefeuille",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Contract> contracts;


    @OneToOne(mappedBy="portefeuille" ,fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private User user;


}
