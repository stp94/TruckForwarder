package com.stp94.truckforwarder;

import java.util.UUID;

public class truck {
    private String truckName;
    private String truckLoadType;
    private double truckLength;
    private double truckWidth;
    private double truckHeight;
    private double truckWeight;
    private double truckCapacity;
    private double truckPrice;
    private int truckID;
    boolean truckStatus; // false = free , true = busy

    // constructor

    truck(String truckName, String truckLoadType, double truckLength, double truckWidth, double truckHeight, double truckWeight, double truckCapacity, double truckPrice, int truckID, boolean truckStatus) {
        this.truckName=truckName;
        this.setTruckLoadType(truckLoadType);
        this.setTruckLength(truckLength);
        this.setTruckWidth(truckWidth);
        this.setTruckHeight(truckHeight);
        this.setTruckWeight(truckWeight);
        this.setTruckCapacity(truckCapacity);
        this.setTruckPrice(truckPrice);
        this.truckID=truckID;
        this.truckStatus=truckStatus;


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

    int getTruckID() {return truckID;}

    boolean getTruckStatus(){return truckStatus;}

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

    private void setTruckID(int truckID) {this.truckID = truckID;}

    private void setTruckStatus(boolean truckStatus){this.truckStatus = truckStatus;}
}









