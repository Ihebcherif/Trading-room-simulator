package tn.esprit.fundsphere.Services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.TransactionManagement.Actif;
import tn.esprit.fundsphere.Entities.TransactionManagement.Ordre;
import tn.esprit.fundsphere.Entities.TransactionManagement.Portefeuille;
import tn.esprit.fundsphere.Entities.TransactionManagement.TypeOrdre;
import tn.esprit.fundsphere.Exceptions.OrdreNotFoundException;
import tn.esprit.fundsphere.Exceptions.PortefeuilleNotFoundException;
import tn.esprit.fundsphere.Repositories.TransactionRepository.OrdreRepository;
import tn.esprit.fundsphere.Repositories.TransactionRepository.PortefeuilleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdreServiceImpl implements OrdreService {

    @Autowired
    private OrdreRepository ordreRepository;

    @Autowired
    private PortefeuilleRepository portefeuilleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CurrencyExchangeService currencyExchangeService; // Forex service

    @Autowired
    private CryptoExchangeService cryptoExchangeService; // Crypto service

    @Override
    public List<Ordre> getAllOrdres() {
        return ordreRepository.findAll(); // Returns all orders
    }

    @Override
    public Optional<Ordre> getOrdreById(Long id) {
        return ordreRepository.findById(id);
    }


    @Override
    public Ordre updateOrdre(Long id, Ordre ordre) {
        if (ordreRepository.existsById(id)) {
            ordre.setIdOrdre(id); // Ensure the order ID is set correctly
            return ordreRepository.save(ordre);
        } else {
            throw new OrdreNotFoundException("Ordre not found with id: " + id); // Exception to throw if ordre not found
        }
    }
    @Override
    public Ordre createOrder(Ordre ordre) {
        Portefeuille portefeuille = portefeuilleRepository.findById(ordre.getPortefeuille().getIdPortefeuille())
                .orElseThrow(() -> new PortefeuilleNotFoundException("Portefeuille non trouvé"));

        double liquidity = portefeuille.getLiquidity();
        double ordrePrice = ordre.getUnitPrice();

        if (ordre.getActif() == Actif.FOREX) {
            return handleForexOrder(ordre, liquidity, portefeuille, ordrePrice);
        } else if (ordre.getActif() == Actif.CRYPTO) {
            return handleCryptoOrder(ordre, liquidity, portefeuille, ordrePrice);
        } else {
            return handleStandardOrder(ordre, liquidity, portefeuille, ordrePrice);
        }
    }

    private Ordre handleCryptoOrder(Ordre ordre, double liquidity, Portefeuille portefeuille, double ordrePrice) {
        double tauxChangeBtcToDinar = cryptoExchangeService.getBtcToDinarRate();
        double totalValueInDinar = ordre.getQuantity() * tauxChangeBtcToDinar * ordrePrice;

        if (ordre.getTypeOrdre() == TypeOrdre.BUY) {
            if (liquidity >= totalValueInDinar) {
                portefeuille.setLiquidity(liquidity - totalValueInDinar);
                portefeuilleRepository.save(portefeuille);
                ordre.setStatus(true);
                ordre.setCrypto(ordre.getQuantity());
                return ordreRepository.save(ordre); // Enregistrer l'ordre
            } else {
                throw new IllegalArgumentException("Fonds insuffisants pour l'achat de crypto");
            }
        } else if (ordre.getTypeOrdre() == TypeOrdre.SELL) {
            if (ordre.getQuantity() <= portefeuille.getLiquidity()) {
                portefeuille.setLiquidity(liquidity + totalValueInDinar);
                portefeuilleRepository.save(portefeuille);
                ordre.setStatus(true);
                return ordreRepository.save(ordre); // Enregistrer l'ordre
            } else {
                throw new IllegalArgumentException("Fonds insuffisants pour vendre la crypto");
            }
        } else {
            throw new IllegalArgumentException("Type d'ordre inconnu pour la crypto");
        }
    }

    private Ordre handleForexOrder(Ordre ordre, double liquidity, Portefeuille portefeuille, double ordrePrice) {
        double tauxChangeEuroToDinar = currencyExchangeService.getEuroToDinarRate();
        double totalValueInDinar = ordre.getQuantity() * tauxChangeEuroToDinar * ordrePrice;

        if (ordre.getTypeOrdre() == TypeOrdre.BUY) {
            if (liquidity >= totalValueInDinar) {
                portefeuille.setLiquidity(liquidity - totalValueInDinar);
                portefeuilleRepository.save(portefeuille);
                ordre.setStatus(true);
                ordre.setEuro(ordre.getQuantity());
                return ordreRepository.save(ordre); // Enregistrer l'ordre
            } else {
                throw new IllegalArgumentException("Fonds insuffisants pour l'achat de forex");
            }
        } else if (ordre.getTypeOrdre() == TypeOrdre.SELL) {
            if (ordre.getQuantity() <= portefeuille.getLiquidity()) {
                portefeuille.setLiquidity(liquidity + totalValueInDinar);
                portefeuilleRepository.save(portefeuille);
                ordre.setStatus(true);
                return ordreRepository.save(ordre); // Enregistrer l'ordre
            } else {
                throw new IllegalArgumentException("Fonds insuffisants pour vendre le forex");
            }
        } else {
            throw new IllegalArgumentException("Type d'ordre inconnu pour le forex");
        }
    }

    private Ordre handleStandardOrder(Ordre ordre, double liquidity, Portefeuille portefeuille, double ordrePrice) {
        // Logique de traitement des ordres standards
        return null; // A adapter selon le cas d'utilisation
    }
    @Override
    public void deleteOrdre(Long id) {
        // Check if the order exists
        Optional<Ordre> ordreOptional = ordreRepository.findById(id);
        if (ordreOptional.isPresent()) {
            ordreRepository.deleteById(id); // Delete the order
        } else {
            throw new OrdreNotFoundException("Ordre not found with id: " + id);
        }
    }
}

