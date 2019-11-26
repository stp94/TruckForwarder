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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Component
public class Controller implements Initializable {

    private player NewPlayer = new player("Piotr", 130000, 1000); // Create a default player

    private int TiltAmount, StandardAmount, SetAmount, TankAmount, TipCartAmount; // Amount of every truck type, in currently game
    private boolean creditstatus, initFlag; // flags


    private long secondsPassed = 0;
    private long minutePassed = 0;
    private long hourPassed = 0;
    private long TimeLimit;

    private long secondsBar=0;
    private long minuteBar=0;
    private long hourBar=0;


    private int mainElementOfTask=0;



    //  /\  variables to Timer - working


    private trucks_lists Library = new trucks_lists(); // Class with methods of Trucks and Routes for Controller


    // \/ GuiElementsDefinitions

    public Label TiltLabelAmount = new Label();
    public Label StandardLabelAmount = new Label();
    public Label SetLabelAmount = new Label();
    public Label TankLabelAmount = new Label();
    public Label TipCartLabelAmount = new Label();
    public Label AmountLabel = new Label();


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



    public Label NumberOfTruckLabel1 = new Label();  public Label SourceLabel1 = new Label();  public Label LengthLabel1 = new Label();  public Label CashRewardLabel1 = new Label();
    public Label NumberOfTruckLabel2 = new Label();  public Label SourceLabel2 = new Label();  public Label LengthLabel2 = new Label();  public Label CashRewardLabel2 = new Label();
    public Label NumberOfTruckLabel3 = new Label();  public Label SourceLabel3 = new Label();  public Label LengthLabel3 = new Label();  public Label CashRewardLabel3 = new Label();
    public Label NumberOfTruckLabel4 = new Label();  public Label SourceLabel4 = new Label();  public Label LengthLabel4 = new Label();  public Label CashRewardLabel4 = new Label();
    public Label NumberOfTruckLabel5 = new Label();  public Label SourceLabel5 = new Label();  public Label LengthLabel5 = new Label();  public Label CashRewardLabel5 = new Label();
    public Label NumberOfTruckLabel6 = new Label();  public Label SourceLabel6 = new Label();  public Label LengthLabel6 = new Label();  public Label CashRewardLabel6 = new Label();
    public Label NumberOfTruckLabel7 = new Label();  public Label SourceLabel7 = new Label();  public Label LengthLabel7 = new Label();  public Label CashRewardLabel7 = new Label();
    public Label NumberOfTruckLabel8 = new Label();  public Label SourceLabel8 = new Label();  public Label LengthLabel8 = new Label();  public Label CashRewardLabel8 = new Label();
    public Label NumberOfTruckLabel9 = new Label();  public Label SourceLabel9 = new Label();  public Label LengthLabel9 = new Label();  public Label CashRewardLabel9 = new Label();
    public Label NumberOfTruckLabel10 = new Label();  public Label SourceLabel10 = new Label();  public Label LengthLabel10 = new Label();  public Label CashRewardLabel10 = new Label();

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


    public ProgressBar ProgressBarTruck1 = new ProgressBar();
    public ProgressBar ProgressBarTruck2 = new ProgressBar();
    public ProgressBar ProgressBarTruck3 = new ProgressBar();
    public ProgressBar ProgressBarTruck4 = new ProgressBar();
    public ProgressBar ProgressBarTruck5 = new ProgressBar();
    public ProgressBar ProgressBarTruck6 = new ProgressBar();
    public ProgressBar ProgressBarTruck7 = new ProgressBar();
    public ProgressBar ProgressBarTruck8 = new ProgressBar();
    public ProgressBar ProgressBarTruck9 = new ProgressBar();
    public ProgressBar ProgressBarTruck10 = new ProgressBar();

    private List<ProgressBar> Bars = new ArrayList<>();


    private List<Label> SourceLabels = new ArrayList<>();
    private List<Label> DestinationLabels = new ArrayList<>();
    private List<Label> LengthLabels = new ArrayList<>();
    private List<Label> CashRewardLabels = new ArrayList<>();

    private List<Runnable> StartRouteList = new ArrayList<>();

    public Label DestinationLabel1 = new Label(); public Label DestinationLabel2 = new Label(); public Label DestinationLabel3 = new Label(); public Label DestinationLabel4 = new Label();
    public Label DestinationLabel5 = new Label(); public Label DestinationLabel6 = new Label(); public Label DestinationLabel7 = new Label(); public Label DestinationLabel8 = new Label();
    public Label DestinationLabel9 = new Label(); public Label DestinationLabel10 = new Label();







    // /\ Gui Elements Definitions



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
        PrepareTasks();
        CheckStatusOfTrucks();
        ShowTruckLabelsBought.setVisible(false);
        ShowTruckLabels.setVisible(false);
        MainGameTimer.run();
        FillRoutesTable();
    }



    // /\  Declaration of currently player status, generate environment

        private Runnable MainGameTimer = () ->
        {


            Timer myTimer = new Timer();
            TimerTask task = new TimerTask()
            {

                @Override
                public void run()
                {


                    secondsPassed++;

                        if (secondsPassed == 60)
                        {
                            minutePassed++;
                            secondsPassed = 0;
                        }

                        if (minutePassed == 60)
                        {
                            hourPassed++;
                            minutePassed = 0;
                        }

                    Platform.runLater(() -> MainTimeLabel.setText(String.format("%d", minutePassed)+ ":"+ String.format("%d", secondsPassed)));
                }
            };      myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }; // Main Clock of Game


                                    private  Runnable StartRoute0 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                          Bars.get(0).setProgress(0);
                                                          progressPercent=0;
                                                          FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                          Timer routeTimer = new Timer();
                                                          TimerTask routeTask = new TimerTask()
                                                          {
                                                              @Override
                                                                  public void run()
                                                                  {


                                                                      progressPercent++;


                                                                              Platform.runLater(() ->
                                                                                     Bars.get(0).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                                          if (Bars.get(0).getProgress() > 1)
                                                                          {
                                                                              NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                                              Platform.runLater(() ->
                                                                                      playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                                              routeTimer.cancel();
                                                                              Thread.currentThread().interrupt();
                                                                              Thread.currentThread().stop();
                                                                          }
                                                                  }
                                                          };
                                                          routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute1 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(1).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(1).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(1).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute2 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(2).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(2).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(2).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute3 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(3).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(3).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(3).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute4 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(4).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(4).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(4).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute5 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(5).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(5).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(5).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute6 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(6).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(6).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(6).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute7 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(7).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(7).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(7).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute8 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(8).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(8).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(8).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable

                                    private  Runnable StartRoute9 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                Bars.get(9).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent++;


                                                        Platform.runLater(() ->
                                                                Bars.get(9).setProgress(progressPercent * 0.01 * Library.GetLength(FocusedIndexInTask) / 50));





                                                        if (Bars.get(9).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));

                                                            Platform.runLater(() ->
                                                                    playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            Thread.currentThread().interrupt();
                                                            Thread.currentThread().stop();
                                                        }
                                                    }
                                                };
                                                routeTimer.scheduleAtFixedRate(routeTask, 1000, 1000);
                                            }

                                        }
                                    }; // end of StartRoute Runnable
    // /\ Runnable Tasks for Routes in Main Panel




        private void PrepareTasks(){

            StartRouteList.add(StartRoute0);
            StartRouteList.add(StartRoute1);
            StartRouteList.add(StartRoute2);
            StartRouteList.add(StartRoute3);
            StartRouteList.add(StartRoute4);
            StartRouteList.add(StartRoute5);
            StartRouteList.add(StartRoute6);
            StartRouteList.add(StartRoute7);
            StartRouteList.add(StartRoute8);
            StartRouteList.add(StartRoute9);

        }



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



    private void CheckAmountsofTrucks()
    {


        TiltLabelAmount.setText(String.format("%d", TiltAmount));


        CheckStatusOfTrucks();
    }




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
        Bars.add(ProgressBarTruck6);
        Bars.add(ProgressBarTruck7);
        Bars.add(ProgressBarTruck8);
        Bars.add(ProgressBarTruck9);
        Bars.add(ProgressBarTruck10);

        SourceLabels.add(SourceLabel1);
        SourceLabels.add(SourceLabel2);
        SourceLabels.add(SourceLabel3);
        SourceLabels.add(SourceLabel4);
        SourceLabels.add(SourceLabel5);
        SourceLabels.add(SourceLabel6);
        SourceLabels.add(SourceLabel7);
        SourceLabels.add(SourceLabel8);
        SourceLabels.add(SourceLabel9);
        SourceLabels.add(SourceLabel10);

        DestinationLabels.add(DestinationLabel1);
        DestinationLabels.add(DestinationLabel2);
        DestinationLabels.add(DestinationLabel3);
        DestinationLabels.add(DestinationLabel4);
        DestinationLabels.add(DestinationLabel5);
        DestinationLabels.add(DestinationLabel6);
        DestinationLabels.add(DestinationLabel7);
        DestinationLabels.add(DestinationLabel8);
        DestinationLabels.add(DestinationLabel9);
        DestinationLabels.add(DestinationLabel10);

        LengthLabels.add(LengthLabel1);
        LengthLabels.add(LengthLabel2);
        LengthLabels.add(LengthLabel3);
        LengthLabels.add(LengthLabel4);
        LengthLabels.add(LengthLabel5);
        LengthLabels.add(LengthLabel6);
        LengthLabels.add(LengthLabel7);
        LengthLabels.add(LengthLabel8);
        LengthLabels.add(LengthLabel9);
        LengthLabels.add(LengthLabel10);

        CashRewardLabels.add(CashRewardLabel1);
        CashRewardLabels.add(CashRewardLabel2);
        CashRewardLabels.add(CashRewardLabel3);
        CashRewardLabels.add(CashRewardLabel4);
        CashRewardLabels.add(CashRewardLabel5);
        CashRewardLabels.add(CashRewardLabel6);
        CashRewardLabels.add(CashRewardLabel7);
        CashRewardLabels.add(CashRewardLabel8);
        CashRewardLabels.add(CashRewardLabel9);
        CashRewardLabels.add(CashRewardLabel10);



    }

        private void GetStartRouteWithParameter(int elementOfRunnable)
        {

            StartRouteList.get(elementOfRunnable).run();
        }



        private void PrepareActiveTaskInMainPanel(int elementOfBar)
        {
            SourceLabels.get(elementOfBar).setText(Library.GetSource(RoutesTable.getSelectionModel().getFocusedIndex()));
            DestinationLabels.get(elementOfBar).setText(Library.GetDestination(RoutesTable.getSelectionModel().getFocusedIndex()));
            LengthLabels.get(elementOfBar).setText(String.format("%x",Library.GetLength(RoutesTable.getSelectionModel().getFocusedIndex())  ) ) ;
            CashRewardLabels.get(elementOfBar).setText(String.format("%.2f",Library.GetReward(RoutesTable.getSelectionModel().getFocusedIndex())));


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
            //RoutesTable.getColumns().addAll(SourceColumn,DestinationColumn,LengthColumn,CashRewardColumn,CategoryColumn);


        }


                @FXML
                private void SelectRouteFromTableToTilt()
                {

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckTilt.size() > 0)
                    {

                        Library.activeTruckTilt.add(Library.equipTruckTilt.get(Library.equipTruckTilt.size()-1));
                        Library.equipTruckTilt.remove(0);
                        PrepareActiveTaskInMainPanel(mainElementOfTask);



                        CheckAmountsofTrucks();


                        InfoMessage.setText("Truck started a route");
                        GetStartRouteWithParameter(mainElementOfTask);

                        mainElementOfTask++;


                    }
                    else
                        InfoMessage.setText("First select a route or check tilt truck availability");
                }




                @FXML
                private void SelectRouteFromTableToStandard()
                {

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckStandard.size() > 0)


                        {

                            Library.activeTruckStandard.add(Library.equipTruckStandard.get(Library.equipTruckStandard.size()-1));
                            Library.equipTruckStandard.remove(0);
                            PrepareActiveTaskInMainPanel(mainElementOfTask);



                            CheckAmountsofTrucks();


                            InfoMessage.setText("Truck started a route");
                            GetStartRouteWithParameter(mainElementOfTask);

                            mainElementOfTask++;


                        }
                        else
                            InfoMessage.setText("First select a route or check standard truck availability");



                }

                @FXML
                private void SelectRouteFromTableToSet()
                {

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckSet.size() > 0)


                    {

                        Library.activeTruckSet.add(Library.equipTruckSet.get(Library.equipTruckSet.size()-1));
                        Library.equipTruckSet.remove(0);
                        PrepareActiveTaskInMainPanel(mainElementOfTask);



                        CheckAmountsofTrucks();


                        InfoMessage.setText("Truck started a route");
                        GetStartRouteWithParameter(mainElementOfTask);

                        mainElementOfTask++;


                    }
                    else
                        InfoMessage.setText("First select a route or check set truck availability");



                }



                @FXML
                private void SelectRouteFromTableToTank()
                {

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckTank.size() > 0)


                    {

                        Library.activeTruckTank.add(Library.equipTruckTank.get(Library.equipTruckTank.size()-1));
                        Library.equipTruckTank.remove(0);
                        PrepareActiveTaskInMainPanel(mainElementOfTask);



                        CheckAmountsofTrucks();


                        InfoMessage.setText("Truck started a route");
                        GetStartRouteWithParameter(mainElementOfTask);

                        mainElementOfTask++;


                    }
                    else
                        InfoMessage.setText("First select a route or check tank truck availability");



                }


                @FXML
                private void SelectRouteFromTableToTipCart()
                {

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckTipCart.size() > 0)


                    {

                        Library.activeTruckTipCart.add(Library.equipTruckTipCart.get(Library.equipTruckTipCart.size()-1));
                        Library.equipTruckTipCart.remove(0);
                        PrepareActiveTaskInMainPanel(mainElementOfTask);



                        CheckAmountsofTrucks();


                        InfoMessage.setText("Truck started a route");
                        GetStartRouteWithParameter(mainElementOfTask);

                        mainElementOfTask++;


                    }
                    else
                        InfoMessage.setText("First select a route or check tank truck availability");



                }











                                    @FXML
                                    private void Exit()
                                    {
                                        Platform.exit();
                                        System.exit(0);
                                    }







}









