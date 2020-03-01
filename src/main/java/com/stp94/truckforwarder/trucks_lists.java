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

    private int TiltID = 1;
    private int StandardID =1;
    private int SetID = 1;
    private int TankID =1;
    private int TipCartID = 1;

    private truck Tilt = new truck("Tilt", "bs", 7.40, 2.45, 3, 5, 12, 10000,0,false);
    private truck Standard = new truck("Standard", "b", 13.6, 2.48, 2.8, 24, 33, 35000,0,false);
    private truck Set = new truck("Set", "b", 15.4, 2.48, 3, 24, 38, 75000,0,false);
    private truck Tank = new truck("Tank", "u", 13.4, 2.55, 3.7, 34, 51, 45000,0,false);
    private truck TipCart = new truck("Tip-cart", "uw", 13.6, 2.45, 2.6, 25, 33, 45000,0,false);

    // /\ Truck objects with ID = 0 , Only to get Details, doesn't exist in Game.

    private poiXLSread RouteDatabase = new poiXLSread(); // Simple DataBase in XLS file

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
    List<truck> activeTruckTipCart = new ArrayList<>(); // Active Trucks






    private List<route> AvailableRoutes = new ArrayList<>(); // Routes


    void BuyTruckTilt() {

        truck Tilt = new truck("Tilt", "bs", 7.40, 2.45, 3, 5, 12, 10000,TiltID,false);
        equipTruckTilt.add(Tilt);
        TiltID++;
    }

    void BuyTruckStandard() {
        truck Standard =  new truck("Standard", "b", 13.6, 2.48, 3, 24, 33, 35000, StandardID,false);
        equipTruckStandard.add(Standard);
        StandardID++;
    }

    void BuyTruckSet() {
        truck Set = new truck("Set", "b", 15.4, 2.48, 3, 24, 38, 75000,SetID,false);
        equipTruckSet.add(Set);
        SetID++;

    }

    void BuyTruckTank() {
        truck Tank = new truck("Tank", "u", 13.4, 2.55, 3.7, 34, 51, 45000,TankID,false);
        equipTruckTank.add(Tank);
        TankID++;
    }

    void BuyTruckTipCart() {

        truck TipCart = new truck("Tip-cart", "uw", 13.6, 2.45, 2.6, 25, 33, 45000,TipCartID,false);
        equipTruckTipCart.add(TipCart);
        TipCartID++;

    }

    // /\ Simple methods which add trucks to equip

    void GenerateAvailableRoutes() {


        AvailableRoutes=RouteDatabase.GetRoutefromFile();



    } // Copy Content from XLS to Route List


    String GetSource(int pos) {
        return AvailableRoutes.get(pos).routeSource;
    }

    String GetDestination(int pos) {
        return AvailableRoutes.get(pos).routeDestination;
    }

    long GetLength(int pos) {
        return AvailableRoutes.get(pos).routeLength;
    }

    double GetReward(int pos) {
        return AvailableRoutes.get(pos).routeCashReward;
    }

    double GetWidth(int pos) {
        return AvailableRoutes.get(pos).routeWidth;
    }
    double GetHeight(int pos) {
        return AvailableRoutes.get(pos).routeHeight;
    }
    double GetWeight(int pos) {
        return AvailableRoutes.get(pos).routeWeight;
    }
    double GetCapacity(int pos) {
        return AvailableRoutes.get(pos).routeCapacity;
    }
    String GetLoadType(int pos) {
        return AvailableRoutes.get(pos).routeLoadType;
    }






    String showTiltInfo() {

        return "Plandeka " + "\n" + "\n" + "Tyl" + "\n" + "\n" + String.format("%.2f", Tilt.getTruckLength()) + "m" + "\n" + "\n" + String.format("%.2f", Tilt.getTruckWidth()) + "m" + "\n" + "\n" +
                 String.format("%.2f", Tilt.getTruckHeight()) + "m" + "\n" + "\n" + String.format("%.2f", Tilt.getTruckWeight()) + "t" + "\n" + "\n" + String.format("%.2f", Tilt.getTruckCapacity()) +"m3";

    }

    String showStandardInfo() {

        return "Standard " + "\n" + "\n" + "Tyl, Bok" + "\n"  + "\n" +  String.format("%.2f", Standard.getTruckLength()) + "m" + "\n" + "\n" + String.format("%.2f", Standard.getTruckWidth()) + "m" + "\n" + "\n" +
                 String.format("%.2f", Standard.getTruckHeight()) + "m" + "\n " + "\n" +  String.format("%.2f", Standard.getTruckWeight()) + "t" + "\n" + "\n" + String.format("%.2f", Standard.getTruckCapacity()) + "m3";

    }

    String showSetInfo() {

        return "Zestaw " + "\n" + "\n" + "Tyl" + "\n"  + "\n" +  String.format("%.2f", Set.getTruckLength()) + "m" + "\n" + "\n" + String.format("%.2f", Set.getTruckWidth()) + "m" + "\n" + "\n" +
                String.format("%.2f", Set.getTruckHeight()) + "m" + "\n " + "\n" +  String.format("%.2f", Set.getTruckWeight()) + "t" + "\n" + "\n" + String.format("%.2f", Set.getTruckCapacity()) + "m3";

    }

    String showTankInfo() {

        return "Cysterna " + "\n" + "\n" + "Gora" + "\n"  + "\n" +  String.format("%.2f", Tank.getTruckLength()) + "m" + "\n" + "\n" + String.format("%.2f", Tank.getTruckWidth()) + "m" + "\n" + "\n" +
                String.format("%.2f", Tank.getTruckHeight()) + "m" + "\n " + "\n" +  String.format("%.2f", Tank.getTruckWeight()) + "t" + "\n" + "\n" + String.format("%.2f", Tank.getTruckCapacity()) + "m3";

    }

    String showTipCartInfo() {

        return "Wywrotka " + "\n" + "\n" + "Gora" + "\n"  + "\n" +  String.format("%.2f", TipCart.getTruckLength()) + "m" + "\n" + "\n" + String.format("%.2f", TipCart.getTruckWidth()) + "m" + "\n" + "\n" +
                String.format("%.2f", TipCart.getTruckHeight()) + "m" + "\n " + "\n" +  String.format("%.2f", TipCart.getTruckWeight()) + "t" + "\n" + "\n" + String.format("%.2f", TipCart.getTruckCapacity()) + "m3";
    }




    public List<com.stp94.truckforwarder.route> GetAvailableRoutes()
    {
        return AvailableRoutes;
    }




    ;

}


// Compare trucks to routes






