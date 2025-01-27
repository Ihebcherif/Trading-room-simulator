package tn.esprit.fundsphere.Services.AssuranceService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AssuranceManagement.CashFlow;

import java.util.List;

@Service
@AllArgsConstructor
public class ActuarialCalculator implements IActuarialCalculator {

    @Override
    public double calculateRiskAdjustedNPV(List<CashFlow> cashFlows, double discountRate) {
        double npv = 0.0;
        for (CashFlow cashFlow : cashFlows) {
            npv += cashFlow.getAmount() / Math.pow(1 + discountRate, cashFlow.getYear());
        }
        return npv;
    }
}
