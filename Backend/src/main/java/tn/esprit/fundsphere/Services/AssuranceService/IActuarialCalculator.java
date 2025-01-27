package tn.esprit.fundsphere.Services.AssuranceService;

import tn.esprit.fundsphere.Entities.AssuranceManagement.CashFlow;

import java.util.List;

public interface IActuarialCalculator {
    double calculateRiskAdjustedNPV(List<CashFlow> cashFlows, double discountRate);
}
