package com.stp94.truckforwarder;

public class route {
    String routeSource;
    String routeDestination;
    String routeLoadType;
    long   routeLength;
    double routeWidth;
    double routeHeight;
    double routeWeight;
    double routeCapacity;
    double routeCashReward;
    String routeCategory;

    public route(String routeSource, String routeDestination, String routeLoadType, long routeLength, double routeWidth, double routeHeight, double routeWeight, double routeCapacity, double routeCashReward, String routeCategory) {
        this.routeSource = routeSource;
        this.routeDestination = routeDestination;
        this.routeLoadType = routeLoadType;
        this.routeLength = routeLength;
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

    public String getRouteDestination() {
        return routeDestination;
    }

    public void setRouteDestination(String routeDestination) {
        this.routeDestination = routeDestination;
    }

    public String getRouteLoadType() {
        return routeLoadType;
    }

    public void setRouteLoadType(String routeLoadType) {
        this.routeLoadType = routeLoadType;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(long routeLength) {
        this.routeLength = routeLength;
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
