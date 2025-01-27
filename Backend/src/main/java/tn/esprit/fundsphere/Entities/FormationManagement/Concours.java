package tn.esprit.fundsphere.Entities.FormationManagement;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Concours {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idConcours ;
    String Name ;
    Date startDate;
    Date endDate;

}
