package com.example.wpzhao_mycarfootprint;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.text.DecimalFormat;

public class Visit implements Serializable{
    // Visit is responsible for storing the information of each visit as well as modification of those information
    private String gasStationName;
    private Date date;
    private String fuelType;
    private int amount;
    private float pricePerLitre;
    private float fuelCost;
    private double carbonFootprint;

    public Visit(String name, Date date, String type, int amount, float price){

        // set all the parameters
        this.gasStationName = name;
        this.date = date;

        // check case sensitivity
        if (type.equalsIgnoreCase("Gasoline")){
            this.fuelType = "Gasoline";
        }
        else if (type.equalsIgnoreCase("Diesel")) {
            this.fuelType = "Diesel";
        }
        else {
            // is none of the two gunna have to tell the user to enter a valid fuel type
            System.out.println("please type the correct use of the object");
        }

        this.amount = amount;
        this.pricePerLitre = price;

        // calculate with the private
        setChange();

    }

    public void setChange(){
        // calculate with the private
        calculateFuelCost();
        calculateCarbonFootprint();
    }

    private void calculateFuelCost() {
        this.fuelCost = ((float) this.amount) * this.pricePerLitre;
    }

    private void calculateCarbonFootprint() {
        if (this.fuelType == "Gasoline"){
            this.carbonFootprint = 2.32 * this.amount;
        }
        else if (this.fuelType == "Diesel"){
            this.carbonFootprint = 2.69 * this.amount;
        }
    }

    // setter methods
    public void setStationName(String name) {
        this.gasStationName = name;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setFuelType(String type) {
        this.fuelType = type;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setPrice(float price) {
        this.pricePerLitre = price;
    }

    // getter methods
    public String getStationName() {
        return this.gasStationName;
    }
    public Date getDate() {
        return this.date;
    }
    public String getFuelType() {
        return this.fuelType;
    }
    public int getAmount() {
        return this.amount;
    }
    public float getPrice() {
        return this.pricePerLitre;
    }
    public float getFuelCost() {
        return this.fuelCost;
    }
    public double getCarbonFootprint() {
        return this.carbonFootprint;
    }

}
