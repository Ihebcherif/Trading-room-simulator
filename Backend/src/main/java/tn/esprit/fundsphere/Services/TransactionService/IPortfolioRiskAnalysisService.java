package tn.esprit.fundsphere.Services.TransactionService;

import tn.esprit.fundsphere.Entities.TransactionManagement.Ordre;

import java.util.List;

public interface IPortfolioRiskAnalysisService {
    double calculatePortfolioVaR(List<Ordre> orders, double confidenceLevel);
}
