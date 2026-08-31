package com.example.transportcostcomparator;

/** Holds one transport-cost calculation. */
public class Transport {
    private long id;
    private String modeOfTransport, transportType;
    private double distancePerDay, costPerKilometre, dailyCost, monthlyCost, monthlyDistance;
    private int travelDays;

    public Transport() { }
    public Transport(String modeOfTransport, String transportType, double distancePerDay, double costPerKilometre, int travelDays, double dailyCost, double monthlyCost, double monthlyDistance) {
        this.modeOfTransport = modeOfTransport; this.transportType = transportType; this.distancePerDay = distancePerDay;
        this.costPerKilometre = costPerKilometre; this.travelDays = travelDays; this.dailyCost = dailyCost;
        this.monthlyCost = monthlyCost; this.monthlyDistance = monthlyDistance;
    }
    public long getId() { return id; } public void setId(long id) { this.id = id; }
    public String getModeOfTransport() { return modeOfTransport; } public void setModeOfTransport(String value) { modeOfTransport = value; }
    public String getTransportType() { return transportType; } public void setTransportType(String value) { transportType = value; }
    public double getDistancePerDay() { return distancePerDay; } public void setDistancePerDay(double value) { distancePerDay = value; }
    public double getCostPerKilometre() { return costPerKilometre; } public void setCostPerKilometre(double value) { costPerKilometre = value; }
    public int getTravelDays() { return travelDays; } public void setTravelDays(int value) { travelDays = value; }
    public double getDailyCost() { return dailyCost; } public void setDailyCost(double value) { dailyCost = value; }
    public double getMonthlyCost() { return monthlyCost; } public void setMonthlyCost(double value) { monthlyCost = value; }
    public double getMonthlyDistance() { return monthlyDistance; } public void setMonthlyDistance(double value) { monthlyDistance = value; }
}
