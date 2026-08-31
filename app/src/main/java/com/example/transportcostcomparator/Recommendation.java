package com.example.transportcostcomparator;

public class Recommendation {
    public String getCostCategory(double monthlyCost) {
        if (monthlyCost < 1000) return "Low Cost";
        if (monthlyCost < 2000) return "Moderate";
        if (monthlyCost < 3000) return "High";
        if (monthlyCost < 5000) return "Very High";
        return "Excessive";
    }
    public String getRecommendation(double monthlyCost) {
        switch (getCostCategory(monthlyCost)) {
            case "Low Cost": return "Continue using your current transport method.";
            case "Moderate": return "Consider carpooling where possible.";
            case "High": return "Reduce unnecessary trips and combine errands.";
            case "Very High": return "Consider public transport for regular commuting.";
            default: return "Immediate action is recommended to reduce transport costs.";
        }
    }
}
