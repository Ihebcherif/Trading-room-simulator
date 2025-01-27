package tn.esprit.fundsphere.Services.ConcoursService;

import tn.esprit.fundsphere.Entities.FormationManagement.Concours;

import java.util.List;

public interface IConcoursService {
    Concours addConcours(Concours concours);

    void deleteConcours(int idConcours);

    Concours updateConcours(Concours concours);

    List<Concours> getAllConcours();

    Concours getConcour(int idConcours);
}
