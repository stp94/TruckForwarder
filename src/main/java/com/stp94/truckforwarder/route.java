package com.stp94.truckforwarder;

public class route {
    String routeSource;
    String routeDestinstion;
    String routeLoadType;
    long   routeLenght;
    double routeWidth;
    double routeHeight;
    double routeWeight;
    double routeCapacity;
    double routeCashReward;
    String routeCategory;

    public route(String routeSource, String routeDestinstion, String routeLoadType, long routeLenght, double routeWidth, double routeHeight, double routeWeight, double routeCapacity, double routeCashReward, String routeCategory) {
        this.routeSource = routeSource;
        this.routeDestinstion = routeDestinstion;
        this.routeLoadType = routeLoadType;
        this.routeLenght = routeLenght;
        this.routeWidth = routeWidth;
        this.routeHeight = routeHeight;
        this.routeWeight = routeWeight;
        this.routeCapacity = routeCapacity;
        this.routeCashReward = routeCashReward;
        this.routeCategory = routeCategory;
    }


    public String getRouteSource() {
        return routeSource;
    }

    public void setRouteSource(String routeSource) {
        this.routeSource = routeSource;
    }

    public String getRouteDestinstion() {
        return routeDestinstion;
    }

    public void setRouteDestinstion(String routeDestinstion) {
        this.routeDestinstion = routeDestinstion;
    }

    public String getRouteLoadType() {
        return routeLoadType;
    }

    public void setRouteLoadType(String routeLoadType) {
        this.routeLoadType = routeLoadType;
    }

    public double getRouteLenght() {
        return routeLenght;
    }

    public void setRouteLenght(long routeLenght) {
        this.routeLenght = routeLenght;
    }

    public double getRouteWidth() {
        return routeWidth;
    }

    public void setRouteWidth(double routeWidth) {
        this.routeWidth = routeWidth;
    }

    public double getRouteHeight() {
        return routeHeight;
    }

    public void setRouteHeight(double routeHeight) {
        this.routeHeight = routeHeight;
    }

    public double getRouteWeight() {
        return routeWeight;
    }

    public void setRouteWeight(double routeWeight) {
        this.routeWeight = routeWeight;
    }

    public double getRouteCapacity() {
        return routeCapacity;
    }

    public void setRouteCapacity(double routeCapacity) {
        this.routeCapacity = routeCapacity;
    }

    public double getRouteCashReward() {
        return routeCashReward;
    }

    public void setRouteCashReward(double routeCashReward) {
        this.routeCashReward = routeCashReward;
    }

    public String getRouteCategory(){return routeCategory;}

    public void setRouteCategory(String routeCategory){this.routeCategory=routeCategory;}






}
