package com.example.wpzhao_mycarfootprint;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class User {
    // User is responsible for storing the current users visits as well as modification of these visits such as adding and deleting
    public ArrayList<Visit> visits;
    private float totalFuelCost;
    private double totalCarbonFootprint;

    public User(){
        this.visits = new ArrayList<Visit>();
        this.totalFuelCost = 0;
        this.totalCarbonFootprint = 0;

    }

    public void addStation(Visit visit){
        this.totalFuelCost = this.totalFuelCost + visit.getFuelCost();
        this.totalCarbonFootprint = this.totalCarbonFootprint + visit.getCarbonFootprint();
    }

    public void deleteStation(Visit visit){
        this.totalFuelCost = this.totalFuelCost - visit.getFuelCost();
        this.totalCarbonFootprint = this.totalCarbonFootprint - visit.getCarbonFootprint();
    }

    public void editDetails(){
        calculateTotalFuelCost();
        calculateTotalCarbonFootprint();
    }

    public void calculateTotalFuelCost(){
        this.totalFuelCost = 0;
        for (int i = 0; i < visits.size(); i++) {
            this.totalFuelCost = this.totalFuelCost + visits.get(i).getFuelCost();
        }
    }

    public void calculateTotalCarbonFootprint(){
        this.totalCarbonFootprint = 0;
        for (int i = 0; i < visits.size(); i++) {
            this.totalCarbonFootprint = this.totalCarbonFootprint + visits.get(i).getCarbonFootprint();
        }
    }

    public int getTotalCarbonFootprint(){
        return (int) Math.round(this.totalCarbonFootprint);
    }

    public float getTotalFuelCost(){
        return this.totalFuelCost;
    }

}
