package com.stp94.truckforwarder;

import com.stp94.truckforwarder.database.poiXLSread;
import org.apache.poi.POIDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.integration.support.json.JsonObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class trucks_lists {

    private truck Tilt = new truck("Tilt", "bs", 7.40, 2.45, 3, 5, 12, 10000);
    private truck Standard = new truck("Standard", "b", 13.6, 2.48, 2.8, 24, 33, 35000);
    private truck Set = new truck("Set", "b", 15.4, 2.48, 3, 24, 38, 75000);
    private truck Tank = new truck("Tank", "u", 13.4, 2.55, 3.7, 34, 51, 45000);
    private truck TipCart = new truck("Tip-cart", "uw", 13.6, 2.45, 2.6, 25, 33, 45000);

    private poiXLSread RouteDatabase = new poiXLSread();

    // Type of Trucks


    List<truck> equipTruckTilt = new ArrayList<>();
    List<truck> equipTruckStandard = new ArrayList<>();
    List<truck> equipTruckSet = new ArrayList<>();
    List<truck> equipTruckTank = new ArrayList<>();
    List<truck> equipTruckTipCart = new ArrayList<>(); // Bought Trucks

    List<truck> activeTruckTilt = new ArrayList<>();
    List<truck> activeTruckStandard = new ArrayList<>();
    List<truck> activeTruckSet = new ArrayList<>();
    List<truck> activeTruckTank = new ArrayList<>();
    List<truck> activeTruckTipCart = new ArrayList<>();






    private List<route> AvailableRoutes = new ArrayList<>(); // Routes


    void BuyTruckTilt() {
        equipTruckTilt.add(Tilt);
    }

    void BuyTruckStandard() {
        equipTruckStandard.add(Standard);
    }

    void BuyTruckSet() {
        equipTruckSet.add(Set);
    }

    void BuyTruckTank() {
        equipTruckTank.add(Tank);
    }

    void BuyTruckTipCart() {
        equipTruckTipCart.add(TipCart);
    }

    void GenerateAvailableRoutes() {


        AvailableRoutes=RouteDatabase.GetRoutefromFile();



    }

    public String GetElementFromAvailableRoutes(int pos) {
        return AvailableRoutes.get(pos).routeSource + AvailableRoutes.get(pos).routeDestination + AvailableRoutes.get(pos).routeLength + AvailableRoutes.get(pos).routeCashReward;
    }

    ;

    public String GetSource(int pos) {
        return AvailableRoutes.get(pos).routeSource;
    }

    public String GetDestination(int pos) {
        return AvailableRoutes.get(pos).routeDestination;
    }

    public long GetLength(int pos) {
        return AvailableRoutes.get(pos).routeLength;
    }

    public double GetReward(int pos) {
        return AvailableRoutes.get(pos).routeCashReward;
    }


    public String showTiltPrice() {

        return String.format("%.0f", Tilt.getTruckPrice()) + "$";

    }

    public String showStandardPrice() {

        return String.format("%.0f", Standard.getTruckPrice()) + "$";

    }

    public String showSetPrice() {

        return String.format("%.0f", Set.getTruckPrice()) + "$";

    }

    public String showTankPrice() {

        return String.format("%.0f", Tank.getTruckPrice()) + "$";

    }

    public String showTipCartPrice() {

        return String.format("%.0f", TipCart.getTruckPrice()) + "$";

    }


    public String showTiltInfo() {

        return "Plandeka " + "\nTyl" + "\nDlugosc: " + String.format("%.2f", Tilt.getTruckLength()) + "\n" + String.format("%.2f", Tilt.getTruckWidth()) + "\n"
                + String.format("%.2f", Tilt.getTruckHeight()) + "\n" + String.format("%.2f", Tilt.getTruckWeight()) + "\n" + String.format("%.2f", Tilt.getTruckCapacity()) + "\n";

    }

    public String showStandardInfo() {

        return "Standard " + "\nTyl, Bok" + "\n" + String.format("%.2f", Standard.getTruckLength()) + "\n" + String.format("%.2f", Standard.getTruckWidth()) + "\n"
                + String.format("%.2f", Standard.getTruckHeight()) + "\n " + String.format("%.2f", Standard.getTruckWeight()) + "\n" + String.format("%.2f", Standard.getTruckCapacity()) + "\n";

    }

    public String showSetInfo() {

        return "Zestaw " + "\nTyl" + "\n" + String.format("%.2f", Set.getTruckLength()) + "\n" + String.format("%.2f", Set.getTruckWidth()) + "\n"
                + String.format("%.2f", Set.getTruckHeight()) + "\n" + String.format("%.2f", Set.getTruckWeight()) + "\n" + String.format("%.2f", Set.getTruckCapacity()) + "\n";

    }

    public String showTankInfo() {

        return "Cysterna " + "\nGora" + "\n" + String.format("%.2f", Tank.getTruckLength()) + "\n" + String.format("%.2f", Tank.getTruckWidth()) + "\n"
                + String.format("%.2f", Tank.getTruckHeight()) + "\n" + String.format("%.2f", Tank.getTruckWeight()) + "\n" + String.format("%.2f", Tank.getTruckCapacity()) + "\n";

    }

    public String showTipCartInfo() {

        return "Wywrotka " + "\nGora" + "\n" + String.format("%.2f", TipCart.getTruckLength()) + "\n" + String.format("%.2f", TipCart.getTruckWidth()) + "\n"
                + String.format("%.2f", TipCart.getTruckHeight()) + "\n" + String.format("%.2f", TipCart.getTruckWeight()) + "\n" + String.format("%.2f", TipCart.getTruckCapacity()) + "\n";

    }

    public String showRoutes(int rN) {


        return AvailableRoutes.get(rN).routeSource + " " + AvailableRoutes.get(rN).routeDestination;
    }


    public String ShowRoutesInfo(int rIN) {
        return "Length: " + AvailableRoutes.get(rIN).routeLength + "km     " +
                "Capacity: " + AvailableRoutes.get(rIN).routeCapacity + "l     " +
                "Height: " + AvailableRoutes.get(rIN).routeHeight + "m     " +
                "Width: " + AvailableRoutes.get(rIN).routeWidth + "m     " +
                "Weight: " + AvailableRoutes.get(rIN).routeWeight + "t     " +
                "Load Type: " + AvailableRoutes.get(rIN).routeLoadType;

    }


    public void Compare() {

        if (equipTruckTilt.get(0).getTruckLoadType() == AvailableRoutes.get(0).getRouteLoadType() && equipTruckTilt.size() > 0) {


        }
    }


    public truck PrepareTrucktoMainPanel(int NumOfTruck) {
        return equipTruckTilt.get(NumOfTruck);
    }

    public void SelectedTruckTilt(){

        activeTruckTilt.add(equipTruckTilt.get(0));
        equipTruckTilt.remove(0);



    }

    public double SelectedRoute(int numOfRoute)
    {

        return AvailableRoutes.get(numOfRoute).getRouteLength();
    }
        //working setActiveTruck


    public List<com.stp94.truckforwarder.route> GetAvailableRoutes()
    {
        return AvailableRoutes;
    }




    ;

}


// Compare trucks to routes






