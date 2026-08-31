package com.example.transportcostcomparator;

public class TransportCalculator {
    public double calculateDailyTransportCost(double distancePerDay, double costPerKilometre) { return distancePerDay * costPerKilometre; }
    public double calculateMonthlyTransportCost(double dailyCost, int travelDays) { return dailyCost * travelDays; }
    public double calculateMonthlyTravelDistance(double distancePerDay, int travelDays) { return distancePerDay * travelDays; }
}
