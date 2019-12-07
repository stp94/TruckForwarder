package com.stp94.truckforwarder;

public class truck {
    private String truckName;
    private String truckLoadType;
    private double truckLength;
    private double truckWidth;
    private double truckHeight;
    private double truckWeight;
    private double truckCapacity;
    private double truckPrice;

    // constructor

    truck(String truckName, String truckLoadType, double truckLength, double truckWidth, double truckHeight, double truckWeight, double truckCapacity, double truckPrice) {
        this.truckName=truckName;
        this.setTruckLoadType(truckLoadType);
        this.setTruckLength(truckLength);
        this.setTruckWidth(truckWidth);
        this.setTruckHeight(truckHeight);
        this.setTruckWeight(truckWeight);
        this.setTruckCapacity(truckCapacity);
        this.setTruckPrice(truckPrice);


    }

    //getter






    public String getTruckName() {
        return truckName;
    }

    public String getTruckLoadType() {
        return truckLoadType;
    }

    double getTruckLength() {
        return truckLength;
    }

    double getTruckWidth() {
        return truckWidth;
    }

    double getTruckHeight() {
        return truckHeight;
    }

    double getTruckWeight() {
        return truckWeight;
    }

    double getTruckCapacity() {
        return truckCapacity;
    }

    double getTruckPrice() {
        return truckPrice;
    }

    //setter


    private void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    private void setTruckLoadType(String truckLoadType) {
        this.truckLoadType = truckLoadType;
    }

    private void setTruckLength(double truckLength) {
        this.truckLength = truckLength;
    }

    private void setTruckWidth(double truckWidth) {
        this.truckWidth = truckWidth;
    }

    private void setTruckHeight(double truckHeight) {
        this.truckHeight = truckHeight;
    }

    private void setTruckWeight(double truckWeight) {
        this.truckWeight = truckWeight;
    }

    private void setTruckCapacity(double truckCapacity) {
        this.truckCapacity = truckCapacity;
    }

    private void setTruckPrice(double truckPrice) {
        this.truckPrice = truckPrice;
    }
}









