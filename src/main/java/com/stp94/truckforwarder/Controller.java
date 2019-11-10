package com.stp94.truckforwarder;


import com.stp94.truckforwarder.database.poiXLSread;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import java.util.*;


@Component
public class Controller implements Initializable {

    private player NewPlayer = new player("Piotr", 130000, 1000); // Create a default player

    private int TiltAmount, StandardAmount, SetAmount, TankAmount, TipCartAmount; // Amount of every truck type in currently game
    private boolean creditstatus, initFlag; // flags


    private long secondsPassed = 0;
    private long minutePassed = 0;
    private long hourPassed = 0;
    private long TimeLimit;

    private long secondsBar=0;
    private long minuteBar=0;
    private long hourBar=0;
    double progressPercent=0.0;

    private static double xOffset = 0;
    private static double yOffset = 0;

    //  /\  variables to Timer - working


    trucks_lists Library = new trucks_lists(); // trucks_lists Could be change name to more "global" class


    // \/ GuiElementsDefinitions

    public BorderPane MainBP = new BorderPane();


    public Label TiltLabelAmount = new Label();
    public Label StandardLabelAmount = new Label();
    public Label SetLabelAmount = new Label();
    public Label TankLabelAmount = new Label();
    public Label TipCartLabelAmount = new Label();
    public Label AmountLabel = new Label();

    //public Label ShowTruckLabels = new Label();

    public Label Route1=new Label();
    public Label Route2=new Label();
    public Label Route3=new Label();
    public Label Route4=new Label();
    public Label Route5=new Label();
    public Label RouteName1=new Label();
    public Label RouteName2=new Label();
    public Label RouteName3=new Label();
    public Label RouteName4=new Label();

    public Label ActiveSourceLabel1=new Label();


    public Label InfoRoute1=new Label();
    public Label InfoRoute2=new Label();
    public Label InfoRoute3=new Label();



    public Button BuyTilt = new Button();
    public Button BuyStandard = new Button();
    public Button BuySet = new Button();
    public Button BuyTank = new Button();
    public Button BuyTipCart = new Button();


    public Button Get30kCredit = new Button();

    public ImageView img_tilt_bought = new javafx.scene.image.ImageView();
    public ImageView img_standard_bought = new javafx.scene.image.ImageView();
    public ImageView img_set_bought = new javafx.scene.image.ImageView();
    public ImageView img_tank_bought = new javafx.scene.image.ImageView();
    public ImageView img_tipcart_bought = new javafx.scene.image.ImageView();


    public Label playerName = new Label();
    public Label playerCash = new Label();
    public Label playerRespect = new Label();
    public Label InfoMessage = new Label();
    public Label MainTimeLabel = new Label();

    public TextArea ShowTruckDetailsInfo = new TextArea();
    public TextArea ShowTruckDetailsInfoBought = new TextArea();
    public TextArea ShowTruckLabels = new TextArea();
    public TextArea ShowTruckLabelsBought = new TextArea();
    public TextArea RouteInfoLabel = new TextArea();


    public Label NumberOfTruckLabel1 = new Label();  public Label SourceLabel1 = new Label();  public Label LengthLabel1 = new Label();  public Label CashRewardLabel1 = new Label();
    public Label NumberOfTruckLabel2 = new Label();  public Label SourceLabel2 = new Label();  public Label LengthLabel2 = new Label();  public Label CashRewardLabel2 = new Label();
    public Label NumberOfTruckLabel3 = new Label();  public Label SourceLabel3 = new Label();  public Label LengthLabel3 = new Label();  public Label CashRewardLabel3 = new Label();
    public Label NumberOfTruckLabel4 = new Label();  public Label SourceLabel4 = new Label();  public Label LengthLabel4 = new Label();  public Label CashRewardLabel4 = new Label();
    public Label NumberOfTruckLabel5 = new Label();  public Label SourceLabel5 = new Label();  public Label LengthLabel5 = new Label();  public Label CashRewardLabel5 = new Label();

    public TableView<route> RoutesTable = new TableView<>();
    public TableColumn<route, String> SourceColumn = new TableColumn<>();
    public TableColumn<route, String> DestinationColumn = new TableColumn<>();
    public TableColumn<route, String> LoadTypeColumn = new TableColumn<>();
    public TableColumn<route, String> LengthColumn = new TableColumn<>();
    public TableColumn<route, String> WidthColumn = new TableColumn<>();
    public TableColumn<route, String> HeightColumn = new TableColumn<>();
    public TableColumn<route, String> WeightColumn = new TableColumn<>();
    public TableColumn<route, String> CapacityColumn = new TableColumn<>();
    public TableColumn<route, String> CashRewardColumn = new TableColumn<>();
    public TableColumn<route, String> CategoryColumn = new TableColumn<>();



    public List<Label> SourcesLabel = new ArrayList<>(250);


    public ProgressBar ProgressBarTruck1 = new ProgressBar();
    public ProgressBar ProgressBarTruck2 = new ProgressBar();
    public ProgressBar ProgressBarTruck3 = new ProgressBar();
    public ProgressBar ProgressBarTruck4 = new ProgressBar();
    public ProgressBar ProgressBarTruck5 = new ProgressBar();

    private List<ProgressBar> Bars = new ArrayList<>();






    public Label DestinationLabel1 = new Label(); public Label DestinationLabel2 = new Label(); public Label DestinationLabel3 = new Label(); public Label DestinationLabel4 = new Label();
    public VBox RouteSlideVBox1 = new VBox(); public VBox RouteSlideVBox2 = new VBox(); public VBox RouteSlideVBox3 = new VBox(); public VBox RouteSlideVBox4 = new VBox(); public VBox RouteSlideVBox5 = new VBox();
    public TextArea Route1Area = new TextArea();




    // /\ GuiElementsDefinitions



    //GameTimer MainTime = new GameTimer(10); // Constructor for Timer - test



    @Override
    public void initialize(java.net.URL location,
                           java.util.ResourceBundle resource) // Currently status of Game Area
    {


        playerName.setText(NewPlayer.getPlayer_Name());
        playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash()));
        playerRespect.setText(String.format("%.2f", NewPlayer.getPlayer_Respect()));

        Library.GenerateAvailableRoutes();
        PrepareBars();
        ShowRoutes();
        CheckStatusOfTrucks();
        ShowTruckLabelsBought.setVisible(false);
        ShowTruckLabels.setVisible(false);
        SetTrucksMainPanel();
        MainGameTimer.run();
        FillRoutesTable();


    }



    // /\  Declaration of currently player status, generate environment

        private Runnable MainGameTimer = new Runnable() {
        @Override
        public void run() {


            Timer myTimer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {


                    secondsPassed++;

                    if (secondsPassed == 60) {
                        minutePassed++;
                        secondsPassed = 0;
                    }

                    if (minutePassed == 60) {
                        hourPassed++;
                        minutePassed = 0;
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            MainTimeLabel.setText(String.format("%d", minutePassed)+ ":"+ String.format("%d", secondsPassed));


                        }
                    });
                }
            };                     myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }}; // Main Clock in FXML
        private Runnable StartRoute = new Runnable() {
                @Override
                public void run() {
                    Timer routeTimer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            secondsBar++;
                            progressPercent++;

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    int i;

                                    for (i=0;i<Bars.size();i++) {
                                        Bars.get(i).setProgress(progressPercent * 0.01 * Library.SelectedRoute(0) / 50);

                                        }

                                }
                            });

                        }
                    };
                    routeTimer.scheduleAtFixedRate(task, 1000, 1000);

                }
            }; // Timer of selected Truck




        //\ Timers


        private void CheckStatusOfTrucks() {
        if (TiltAmount <= 0) {
            img_tilt_bought.setVisible(false);
        } else
            img_tilt_bought.setVisible(true);

        if (StandardAmount <= 0) {
            img_standard_bought.setVisible(false);
        } else
            img_standard_bought.setVisible(true);

        if (SetAmount <= 0) {
            img_set_bought.setVisible(false);
        } else
            img_set_bought.setVisible(true);

        if (TankAmount <= 0) {
            img_tank_bought.setVisible(false);
        } else
            img_tank_bought.setVisible(true);

        if (TipCartAmount <= 0) {
            img_tipcart_bought.setVisible(false);
        } else
            img_tipcart_bought.setVisible(true);

        if (creditstatus = false)
            Get30kCredit.setVisible(true);

        playerCash.setText(String.format("%.2f",NewPlayer.getPlayer_Cash()));


    } // // Function to check status of equip

    // \/ FXML working with users action








    @FXML
    private void ShowTiltPrice(MouseEvent event) {
        ShowTruckDetailsInfo.setText(Library.showTiltInfo());
        ShowTruckDetailsInfoBought.setText(Library.showTiltInfo());
        ShowTruckLabels.setVisible(true);
        ShowTruckLabelsBought.setVisible(true);

        AmountLabel.setText(String.format("%d",TiltAmount));


    }

    @FXML
    private void HideTiltPrice(MouseEvent event) {
        ShowTruckDetailsInfo.setText("");
        ShowTruckDetailsInfoBought.setText("");
        ShowTruckLabels.setVisible(false);
        ShowTruckLabelsBought.setVisible(false);
    }

    @FXML
    private void ShowStandardPrice(MouseEvent event) {
        ShowTruckDetailsInfo.setText(Library.showStandardInfo());
        ShowTruckDetailsInfoBought.setText(Library.showStandardInfo());
        ShowTruckLabels.setVisible(true);
        ShowTruckLabelsBought.setVisible(true);
    }

    @FXML
    private void ShowSetPrice(MouseEvent event) {
        ShowTruckDetailsInfo.setText(Library.showSetInfo());
        ShowTruckDetailsInfoBought.setText(Library.showSetInfo());
        ShowTruckLabels.setVisible(true);
        ShowTruckLabelsBought.setVisible(true);

    }

    @FXML
    private void ShowTankPrice(MouseEvent event) {
        ShowTruckDetailsInfo.setText(Library.showTankInfo());
        ShowTruckDetailsInfoBought.setText(Library.showTankInfo());
        ShowTruckLabels.setVisible(true);
        ShowTruckLabelsBought.setVisible(true);

    }

    @FXML
    private void ShowTipCartPrice(MouseEvent event) {
        ShowTruckDetailsInfo.setText(Library.showTipCartInfo());
        ShowTruckDetailsInfoBought.setText(Library.showTipCartInfo());
        ShowTruckLabels.setVisible(true);
        ShowTruckLabelsBought.setVisible(true);

    }


    // /\ ShowDetailsInMarket
    // \/ BuyTrucksFunctions

    @FXML
    private void BuyTiltTruck(MouseEvent event) {
        if (NewPlayer.getPlayer_Cash() >= 10000) {
            InfoMessage.setText("Bought: ");
            Library.BuyTruckTilt();
            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()-10000);
            TiltAmount++;
            TiltLabelAmount.setText(String.format("%d",TiltAmount));
            CheckStatusOfTrucks();
        } else
            InfoMessage.setText("No funds");
    }

    @FXML
    private void BuyStandardTruck(MouseEvent event) {
        if (NewPlayer.getPlayer_Cash() >= 35000) {
            InfoMessage.setText("Bought: ");
            Library.BuyTruckStandard();
            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash() - 35000);
            StandardAmount++;
            StandardLabelAmount.setText(String.format("%d", StandardAmount));
            CheckStatusOfTrucks();
        } else
            InfoMessage.setText("No funds");
    }

    @FXML
    private void BuySetTruck(MouseEvent event) {
        if (NewPlayer.getPlayer_Cash() >= 75000) {
            InfoMessage.setText("Bought: ");
            Library.BuyTruckSet();
            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash() - 75000);
            SetAmount++;
            SetLabelAmount.setText(String.format("%d", SetAmount));
            CheckStatusOfTrucks();
        } else
            InfoMessage.setText("No funds");
    }

    @FXML
    private void BuyTankTruck(MouseEvent event) {
        if (NewPlayer.getPlayer_Cash() >= 45000) {
            InfoMessage.setText("Bought: ");
            Library.BuyTruckTank();
            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash() - 45000);
            TankAmount++;
            TankLabelAmount.setText(String.format("%x", TankAmount));
            CheckStatusOfTrucks();
        } else
            InfoMessage.setText("No funds");
    }

    @FXML
    private void BuyTipCartTruck(MouseEvent event) {
        if (NewPlayer.getPlayer_Cash() >= 45000) {
            InfoMessage.setText("Bought: ");
            Library.BuyTruckTipCart();
            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash() - 45000);
            TipCartAmount++;
            TipCartLabelAmount.setText(String.format("%d", TipCartAmount));
            CheckStatusOfTrucks();
        } else
            InfoMessage.setText("No funds");
    }

    // /\BuyTrucksFunctions

    @FXML
    private boolean GetCredit(MouseEvent event) {
        if (creditstatus = true) {
            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash() + 15000);
            CheckStatusOfTrucks();
            InfoMessage.setText("Otrzymales kredyt");
            creditstatus = false;

        } else
            InfoMessage.setText("Niestety, nie mozesz otrzymac kredytu");


        return creditstatus = false;

    }

    @FXML
    private void TiltBoxInit(MouseEvent event) {




    }

    @FXML
    private void RoutesToTextArea1 (MouseEvent event)
    {
        RouteInfoLabel.setText(Library.ShowRoutesInfo(0));

    }

    @FXML
    private void RoutesToTextArea2 (MouseEvent event)
    {
        RouteInfoLabel.setText(Library.ShowRoutesInfo(1));

    }

    @FXML
    private void RoutesToTextArea3 (MouseEvent event)
    {
        RouteInfoLabel.setText(Library.ShowRoutesInfo(2));

    }

    @FXML
    private void RoutesToTextArea4 (MouseEvent event)
    {
        RouteInfoLabel.setText(Library.ShowRoutesInfo(3));

    }

    @FXML
    private void Compare()
    {

        Library.Compare();
    }

    private void PrepareBars()
    {
        Bars.add(ProgressBarTruck1);
        Bars.add(ProgressBarTruck2);
        Bars.add(ProgressBarTruck3);
        Bars.add(ProgressBarTruck4);
        Bars.add(ProgressBarTruck5);
    }

    private void ShowRoutes()
    {




        Route1.setText(Library.ShowRoutesInfo(0));
        Route2.setText(Library.ShowRoutesInfo(1));
        Route3.setText(Library.ShowRoutesInfo(2));

        InfoRoute1.setText(Library.ShowRoutesInfo(0));
        InfoRoute2.setText(Library.ShowRoutesInfo(1));
        InfoRoute3.setText(Library.ShowRoutesInfo(2));

        RouteName1.setText(Library.GetSource(0)+ "-" + Library.GetDestination(0));
        RouteName2.setText(Library.GetSource(1)+ "-" + Library.GetDestination(1));
        RouteName3.setText(Library.GetSource(2)+ "-" + Library.GetDestination(2));
        //RouteName4.setText(Library.GetSource(3)+ "-" + Library.GetDestination(3));






    }


    private void FillRoutesTable()
    {










        SourceColumn.setCellValueFactory(new PropertyValueFactory<>("routeSource"));
        DestinationColumn.setCellValueFactory(new PropertyValueFactory<>("routeDestination"));
        LengthColumn.setCellValueFactory(new PropertyValueFactory<>("routeLength"));
        CashRewardColumn.setCellValueFactory(new PropertyValueFactory<>("routeCashReward"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("routeCategory"));

        ObservableList<route> RoutesObservableList = FXCollections.observableArrayList(Library.GetAvailableRoutes());



        RoutesTable.setItems(RoutesObservableList);
        RoutesTable.getColumns().addAll(SourceColumn,DestinationColumn,LengthColumn,CashRewardColumn,CategoryColumn);


















    }

    private void SetTrucksMainPanel()
    {
        NumberOfTruckLabel1.setText("1");
        SourceLabel1.setText(Library.GetSource(0));
        DestinationLabel1.setText(Library.GetDestination(0));
        LengthLabel1.setText(String.format("%d",Library.GetLength(0)) + "km");
        CashRewardLabel1.setText("$" + String.format("%s",Library.GetReward(0)));

        NumberOfTruckLabel2.setText("2");
        SourceLabel2.setText(Library.GetSource(1));
        DestinationLabel2.setText(Library.GetDestination(1));
        LengthLabel2.setText(String.format("%d",Library.GetLength(1)) + "km");
        CashRewardLabel2.setText("$" +  String.format("%s",Library.GetReward(1)));

        NumberOfTruckLabel3.setText("3");
        SourceLabel3.setText(Library.GetSource(2));
        DestinationLabel3.setText(Library.GetDestination(2));
        LengthLabel3.setText(String.format("%d",Library.GetLength(2)) + "km");
        CashRewardLabel3.setText("$" + String.format("%s",Library.GetReward(2)));




    }

            @FXML private void RouteVBoxAnimationSlider1()
            {
                RouteSlideVBox1.setPrefHeight(200);
            }

            @FXML private void RouteVBoxAnimationSliderHide1() { RouteSlideVBox1.setPrefHeight(30);}

            @FXML private void RouteVBoxAnimationSlider2()
            {
                RouteSlideVBox2.setPrefHeight(200);
            }

            @FXML private void RouteVBoxAnimationSliderHide2()
            {
                RouteSlideVBox2.setPrefHeight(30);
            }

            @FXML private void RouteVBoxAnimationSlider3()
            {
                RouteSlideVBox3.setPrefHeight(200);
            }

            @FXML private void RouteVBoxAnimationSliderHide3()
            {
                RouteSlideVBox3.setPrefHeight(30);
            }

            @FXML private void RouteVBoxAnimationSlider4()
            {
                RouteSlideVBox4.setPrefHeight(200);
            }

            @FXML private void RouteVBoxAnimationSliderHide4()
            {
                RouteSlideVBox4.setPrefHeight(30);
            }



                                @FXML private void SetActiveTruck()
                                {
                                    Library.SelectedTruckTilt();
                                    Library.ShowRoutesInfo(0);
                                    StartRoute.run();



                                }

                                @FXML private void ProgressBarTruck()
                                {

                                    ProgressBarTruck1.setProgress(Library.GetLength(0));

                                }

                                    @FXML
                                    private void Exit()
                                    {
                                        Platform.exit();
                                        System.exit(0);
                                    }







}









