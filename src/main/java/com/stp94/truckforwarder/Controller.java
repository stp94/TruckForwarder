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
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Component
public class Controller implements Initializable {

    private player NewPlayer = new player("Player", 50000, 1000); // Create a default player

    private int TiltAmount, StandardAmount, SetAmount, TankAmount, TipCartAmount; // Amount of every truck type, in currently game
    private boolean creditstatus, initFlag; // flags


    private long secondsPassed = 0;
    private long minutePassed = 0;
    private long hourPassed = 0;


    boolean RunStatus0=true;
    boolean RunStatus1=true;
    boolean RunStatus2=true;
    boolean RunStatus3=true;
    boolean RunStatus4=true;
    boolean RunStatus5=true;
    boolean RunStatus6=true;
    boolean RunStatus7=true;
    boolean RunStatus8=true;
    boolean RunStatus9=true;

    boolean Credit15k=false;
    boolean Credit30k=false;
    boolean Credit50k=false;

    private int CreditLimit=0;
    private int CreditPart=0;
    private boolean CreditStatus=false;




    private int mainElementOfTask=0;

    String DestinationRoute0, DestinationRoute1, DestinationRoute2, DestinationRoute3, DestinationRoute4, DestinationRoute5, DestinationRoute6, DestinationRoute7,
            DestinationRoute8,DestinationRoute9;

    String TruckType;

   boolean[] TableOfTasks = new boolean[10]; // flags for information that Currently ProgresBar Task is busy or free. True=Busy , False=Free;



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
    public Label NumberOfTruckLabel10= new Label(); public Label SourceLabel10 = new Label(); public Label LengthLabel10 = new Label(); public Label CashRewardLabel10 = new Label();

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
    private List<Label> IdentityLabels = new ArrayList<>();

    private List<Runnable> StartRouteList = new ArrayList<>();

    public Label DestinationLabel1 = new Label(); public Label DestinationLabel2 = new Label(); public Label DestinationLabel3 = new Label(); public Label DestinationLabel4 = new Label();
    public Label DestinationLabel5 = new Label(); public Label DestinationLabel6 = new Label(); public Label DestinationLabel7 = new Label(); public Label DestinationLabel8 = new Label();
    public Label DestinationLabel9 = new Label(); public Label DestinationLabel10 = new Label();

    public Label IdentityLabel1 = new Label(); public Label IdentityLabel2 = new Label(); public Label IdentityLabel3 = new Label();
    public Label IdentityLabel4 = new Label(); public Label IdentityLabel5 = new Label(); public Label IdentityLabel6 = new Label();
    public Label IdentityLabel7 = new Label(); public Label IdentityLabel8 = new Label(); public Label IdentityLabel9 = new Label();
    public Label IdentityLabel10 = new Label();







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
        PrepareTableOfTasks();
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
                    CheckRespectStatus();

                    Platform.runLater(() -> playerRespect.setText(String.format("%s",NewPlayer.getPlayer_Respect())));
                    Platform.runLater(() -> playerCash.setText(String.format("%s",NewPlayer.getPlayer_Cash())));

                }
            };      myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }; // Main Clock of Game


                                    private  Runnable StartRoute0 = new Runnable()
                                    {
                                        double progressPercent=0.0;
                                        int FocusedIndexInTask;
                                        String TruckInTask;

                                        @Override
                                        public void run()
                                        {
                                            {             RunStatus0=true;
                                                          Bars.get(0).setProgress(0);
                                                          progressPercent=0;
                                                          FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                          DestinationRoute0=Library.GetDestination(FocusedIndexInTask);
                                                          TruckInTask=TruckType;

                                                          Timer routeTimer = new Timer();
                                                          TimerTask routeTask = new TimerTask()
                                                          {
                                                              @Override
                                                                  public void run()
                                                                  {
                                                                      progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                                              Platform.runLater(() ->
                                                                                     Bars.get(0).setProgress(progressPercent));
                                                                          if (Bars.get(0).getProgress() > 1)
                                                                          {
                                                                              NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                                              Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                                              routeTimer.cancel();
                                                                              CheckTypeofTruckInTasks(TruckInTask);
                                                                              TableOfTasks[0]=false;
                                                                              RunStatus0=false;



                                                                              NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {
                                            {   RunStatus1=true;
                                                Bars.get(1).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute1=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                       progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(1).setProgress(progressPercent));
                                                        if (Bars.get(1).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);
                                                            TableOfTasks[1]=false;
                                                            RunStatus1=false;


                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {
                                            {
                                                RunStatus2=true;
                                                Bars.get(2).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute2=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(2).setProgress(progressPercent));
                                                        if (Bars.get(2).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[2]=false;
                                                            RunStatus2=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {
                                            {
                                                RunStatus3=true;
                                                Bars.get(3).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute3=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(3).setProgress(progressPercent));
                                                        if (Bars.get(3).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[3]=false;
                                                            RunStatus3=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {
                                            {
                                                RunStatus4=true;
                                                Bars.get(4).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute4=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(4).setProgress(progressPercent));
                                                        if (Bars.get(4).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[4]=false;
                                                            RunStatus4=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {
                                            {
                                                RunStatus5=true;
                                                Bars.get(5).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute5=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(5).setProgress(progressPercent));
                                                        if (Bars.get(5).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[5]=false;
                                                            RunStatus5=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {
                                            {
                                                RunStatus6=true;
                                                Bars.get(6).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute6=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;


                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {

                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(6).setProgress(progressPercent));

                                                        if (Bars.get(6).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[6]=false;
                                                            RunStatus6=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {
                                            {
                                                RunStatus7=true;
                                                Bars.get(7).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute7=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {
                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);

                                                        Platform.runLater(() ->
                                                                Bars.get(7).setProgress(progressPercent));

                                                        if (Bars.get(7).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[7]=false;
                                                            RunStatus7=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                RunStatus8=true;
                                                Bars.get(8).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute8=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {


                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(8).setProgress(progressPercent));

                                                        if (Bars.get(8).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[8]=false;
                                                            RunStatus8=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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
                                        String TruckInTask;
                                        @Override
                                        public void run()
                                        {


                                            {
                                                RunStatus9=true;
                                                Bars.get(9).setProgress(0);
                                                progressPercent=0;
                                                FocusedIndexInTask=RoutesTable.getSelectionModel().getFocusedIndex();
                                                DestinationRoute9=Library.GetDestination(FocusedIndexInTask);
                                                TruckInTask=TruckType;

                                                Timer routeTimer = new Timer();
                                                TimerTask routeTask = new TimerTask()
                                                {
                                                    @Override
                                                    public void run()
                                                    {

                                                        progressPercent=progressPercent+0.01/ (Library.GetLength(FocusedIndexInTask) * 0.005);
                                                        Platform.runLater(() ->
                                                                Bars.get(9).setProgress(progressPercent));

                                                        if (Bars.get(9).getProgress() > 1)
                                                        {
                                                            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+Library.GetReward(FocusedIndexInTask));
                                                            Platform.runLater(() -> playerCash.setText(String.format("%.2f", NewPlayer.getPlayer_Cash())));
                                                            routeTimer.cancel();
                                                            CheckTypeofTruckInTasks(TruckInTask);

                                                            TableOfTasks[9]=false;
                                                            RunStatus9=false;
                                                            NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()+100);
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



        private void ReleaseActiveTruck()
        {




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



    private void CheckAmountsOfTrucks()
    {


        TiltLabelAmount.setText(String.format("%d", Library.equipTruckTilt.size()) );
        StandardLabelAmount.setText(String.format("%d", Library.equipTruckStandard.size()) );
        SetLabelAmount.setText(String.format("%d", Library.equipTruckSet.size()) );
        TankLabelAmount.setText(String.format("%d", Library.equipTruckTank.size()) );
        TipCartLabelAmount.setText(String.format("%d", Library.equipTruckTipCart.size()) );



        CheckStatusOfTrucks();
    }



    private void FindFreeTask()
    {
        for(int i=0; i<TableOfTasks.length;i++)
        {
            if (TableOfTasks[i]==false)
            {
                mainElementOfTask=i;
                TableOfTasks[i]=true;
                break;
            }



        }

    }



    private void CheckRespectStatus()
    {
        if(NewPlayer.getPlayer_Respect()<1)
        {
            Exit();
        }

        if (!RunStatus0 || !RunStatus1 || !RunStatus2 || !RunStatus3 || !RunStatus4 || !RunStatus5 || !RunStatus6 || !RunStatus7 || !RunStatus8 || !RunStatus9 )
        {NewPlayer.setPlayer_Respect(NewPlayer.getPlayer_Respect()-1);}
    }


    private void CheckTypeofTruckInTasks(String CurrentlyType)
    {
        if (CurrentlyType == "Tilt") {
            Library.equipTruckTilt.add(Library.activeTruckTilt.get(0));
            Library.activeTruckTilt.remove(0);
        }

        else if (CurrentlyType == "Standard") {
            Library.equipTruckStandard.add(Library.activeTruckStandard.get(0));
            Library.activeTruckStandard.remove(0);
        }
        else if (CurrentlyType == "Set") {
            Library.equipTruckSet.add(Library.activeTruckSet.get(0));
            Library.activeTruckSet.remove(0);

        }
        else if (CurrentlyType == "Tank") {
            Library.equipTruckTank.add(Library.activeTruckTank.get(0));
            Library.activeTruckTank.remove(0);

        }
        else if (CurrentlyType == "TipCart") {
            Library.equipTruckTipCart.add(Library.activeTruckTipCart.get(0));
            Library.activeTruckTipCart.remove(0);

        }

        TruckType="empty";
    }



        private void PrepareTableOfTasks()
        {
            for (int i=0;i<TableOfTasks.length;i++)
            TableOfTasks[i]=false;

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
            InfoMessage.setText("Bought");
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
            InfoMessage.setText("Bought");
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
            InfoMessage.setText("Bought");
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
            InfoMessage.setText("Bought");
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
            InfoMessage.setText("Bought");
            Library.BuyTruckTipCart();
            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash() - 45000);
            TipCartAmount++;
            TipCartLabelAmount.setText(String.format("%d", TipCartAmount));
            CheckStatusOfTrucks();
        } else
            InfoMessage.setText("No funds");
    }

    // /\BuyTrucksFunctions

    private void CheckCreditStatus()
    {
        if (secondsPassed==60){

                if(CreditStatus==true) {

                while (CreditPart < CreditLimit) {
                    NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash() - 1000);

                }
            }
            else if (CreditPart==CreditLimit){
                CreditStatus=false;
            }

        }
    }

    @FXML
    private void GetCredit15k(MouseEvent event) {

            NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+15000);
            CreditStatus=true;
            CreditLimit=20;

    }

    @FXML
    private void GetCredit30k(MouseEvent event){
        NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+30000);
        CreditStatus=true;
        CreditLimit=40;

    }

    @FXML
    private void GetCredit50k(MouseEvent event){

        NewPlayer.setPlayer_Cash(NewPlayer.getPlayer_Cash()+50000);
        CreditStatus=true;
        CreditLimit=65;
    }


    @FXML
    private boolean CompareTilt() {

        if (Library.equipTruckTilt.get(0).getTruckWidth() >= Library.GetWidth(RoutesTable.getSelectionModel().getFocusedIndex()) &&
                Library.equipTruckTilt.get(0).getTruckHeight() >= Library.GetHeight(RoutesTable.getSelectionModel().getFocusedIndex()) &&
                Library.equipTruckTilt.get(0).getTruckWeight() >= Library.GetWeight(RoutesTable.getSelectionModel().getFocusedIndex()) &&
                Library.equipTruckTilt.get(0).getTruckCapacity() >= Library.GetCapacity(RoutesTable.getSelectionModel().getFocusedIndex())
            //Library.equipTruckTilt.get(0).getTruckLoadType()==Library.GetLoadType(RoutesTable.getSelectionModel().getFocusedIndex())
        ) {
            return true;

        } else
            InfoMessage.setText("Check the size of cargo");
        return false;
        }


        @FXML
        private boolean CompareStandard()
        {

            if (    Library.equipTruckStandard.get(0).getTruckWidth() >= Library.GetWidth(RoutesTable.getSelectionModel().getFocusedIndex()) &&
                    Library.equipTruckStandard.get(0).getTruckHeight() >= Library.GetHeight(RoutesTable.getSelectionModel().getFocusedIndex()) &&
                    Library.equipTruckStandard.get(0).getTruckWeight() >= Library.GetWeight(RoutesTable.getSelectionModel().getFocusedIndex()) &&
                    Library.equipTruckStandard.get(0).getTruckCapacity() >= Library.GetCapacity(RoutesTable.getSelectionModel().getFocusedIndex())
                //Library.equipTruckTilt.get(0).getTruckLoadType()==Library.GetLoadType(RoutesTable.getSelectionModel().getFocusedIndex())
            ) {
                return true;

            } else
                InfoMessage.setText("Check the size of cargo");
            return false;
        }



    @FXML
    private boolean CompareSet()
    {

        if (        Library.equipTruckSet.get(0).getTruckWidth()>=Library.GetWidth(RoutesTable.getSelectionModel().getFocusedIndex())         &&
                Library.equipTruckSet.get(0).getTruckHeight()>=Library.GetHeight(RoutesTable.getSelectionModel().getFocusedIndex())       &&
                Library.equipTruckSet.get(0).getTruckWeight()>=Library.GetWeight(RoutesTable.getSelectionModel().getFocusedIndex())       &&
                Library.equipTruckSet.get(0).getTruckCapacity()>=Library.GetCapacity(RoutesTable.getSelectionModel().getFocusedIndex())
            //Library.equipTruckTilt.get(0).getTruckLoadType()==Library.GetLoadType(RoutesTable.getSelectionModel().getFocusedIndex())
        )

        { return true;

        }
        else
            InfoMessage.setText("Check the size of cargo");
        return false;

    }

    @FXML
    private boolean CompareTank()
    {

        if (        Library.equipTruckTank.get(0).getTruckWidth()>=Library.GetWidth(RoutesTable.getSelectionModel().getFocusedIndex())         &&
                Library.equipTruckTank.get(0).getTruckHeight()>=Library.GetHeight(RoutesTable.getSelectionModel().getFocusedIndex())       &&
                Library.equipTruckTank.get(0).getTruckWeight()>=Library.GetWeight(RoutesTable.getSelectionModel().getFocusedIndex())       &&
                Library.equipTruckTank.get(0).getTruckCapacity()>=Library.GetCapacity(RoutesTable.getSelectionModel().getFocusedIndex())
            //Library.equipTruckTilt.get(0).getTruckLoadType()==Library.GetLoadType(RoutesTable.getSelectionModel().getFocusedIndex())
        )

        { return true;

        }
        else
            InfoMessage.setText("Check the size of cargo");
        return false;

    }

    @FXML
    private boolean CompareTipCart()
    {

        if (        Library.equipTruckTipCart.get(0).getTruckWidth()>=Library.GetWidth(RoutesTable.getSelectionModel().getFocusedIndex())         &&
                Library.equipTruckTipCart.get(0).getTruckHeight()>=Library.GetHeight(RoutesTable.getSelectionModel().getFocusedIndex())       &&
                Library.equipTruckTipCart.get(0).getTruckWeight()>=Library.GetWeight(RoutesTable.getSelectionModel().getFocusedIndex())       &&
                Library.equipTruckTipCart.get(0).getTruckCapacity()>=Library.GetCapacity(RoutesTable.getSelectionModel().getFocusedIndex())
            //Library.equipTruckTilt.get(0).getTruckLoadType()==Library.GetLoadType(RoutesTable.getSelectionModel().getFocusedIndex())
        )

        { return true;

        }
        else
            InfoMessage.setText("Check the size of cargo");
        return false;

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

        IdentityLabels.add(IdentityLabel1);
        IdentityLabels.add(IdentityLabel2);
        IdentityLabels.add(IdentityLabel3);
        IdentityLabels.add(IdentityLabel4);
        IdentityLabels.add(IdentityLabel5);
        IdentityLabels.add(IdentityLabel6);
        IdentityLabels.add(IdentityLabel7);
        IdentityLabels.add(IdentityLabel8);
        IdentityLabels.add(IdentityLabel9);
        IdentityLabels.add(IdentityLabel10);



    }

        private void GetStartRouteWithParameter(int elementOfRunnable)
        {
            StartRouteList.get(elementOfRunnable).run();
        }



        private void PrepareActiveTaskInMainPanel(int elementOfBar)
        {
            SourceLabels.get(elementOfBar).setText(Library.GetSource(RoutesTable.getSelectionModel().getFocusedIndex()));
            DestinationLabels.get(elementOfBar).setText(Library.GetDestination(RoutesTable.getSelectionModel().getFocusedIndex()));
            LengthLabels.get(elementOfBar).setText(String.format("%d",Library.GetLength(RoutesTable.getSelectionModel().getFocusedIndex())  ) ) ;
            CashRewardLabels.get(elementOfBar).setText(String.format("%.2f",Library.GetReward(RoutesTable.getSelectionModel().getFocusedIndex())));
            //IdentityLabels.get(elementOfBar).setText(String.format("d",Library.Ge));



        }


        private void FillRoutesTable()
        {
            SourceColumn.setCellValueFactory(new PropertyValueFactory<>("routeSource"));
            DestinationColumn.setCellValueFactory(new PropertyValueFactory<>("routeDestination"));
            LengthColumn.setCellValueFactory(new PropertyValueFactory<>("routeLength"));
            CashRewardColumn.setCellValueFactory(new PropertyValueFactory<>("routeCashReward"));
            CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("routeCategory"));
            WidthColumn.setCellValueFactory(new PropertyValueFactory<>("routeWidth"));
            HeightColumn.setCellValueFactory(new PropertyValueFactory<>("routeHeight"));
            WeightColumn.setCellValueFactory(new PropertyValueFactory<>("routeWeight"));
            LoadTypeColumn.setCellValueFactory(new PropertyValueFactory<>("routeLoadType"));
            CapacityColumn.setCellValueFactory(new PropertyValueFactory<>("routeCapacity"));

            ObservableList<route> RoutesObservableList = FXCollections.observableArrayList(Library.GetAvailableRoutes());
            RoutesTable.setItems(RoutesObservableList);



        }


                @FXML
                private void SelectRouteFromTableToTilt()
                {

                    if(Library.equipTruckTilt.size()>0)
                    { CheckRespectStatus();
                    CompareTilt(); }


                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckTilt.size() > 0 && CompareTilt()) {
                        TruckType="Tilt";
                        Library.activeTruckTilt.add(Library.equipTruckTilt.get(0));
                        String tiltID = String.format("%d", Library.equipTruckTilt.get(0).getTruckID());
                        Library.equipTruckTilt.remove(0);
                        FindFreeTask();
                        PrepareActiveTaskInMainPanel(mainElementOfTask);
                        IdentityLabels.get(mainElementOfTask).setText("Tilt");

                        CheckAmountsOfTrucks();
                        System.out.println(RoutesTable.getSelectionModel().getFocusedIndex());
                        InfoMessage.setText("Truck started a route");
                        GetStartRouteWithParameter(mainElementOfTask);
                        mainElementOfTask++;
                    }
                    else
                        InfoMessage.setText("Bad Route or Truck");
                }

                @FXML
                private void SelectRouteFromTableToStandard()
                {


                    if(Library.equipTruckStandard.size()>0)
                    { CheckRespectStatus();
                        CompareStandard(); }

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckStandard.size() > 0 && CompareStandard())
                        {
                            TruckType="Standard";
                            Library.activeTruckStandard.add(Library.equipTruckStandard.get(Library.equipTruckStandard.size()-1));
                            Library.equipTruckStandard.remove(0);
                            FindFreeTask();
                            IdentityLabels.get(mainElementOfTask).setText("Standard");
                            PrepareActiveTaskInMainPanel(mainElementOfTask);
                            CheckAmountsOfTrucks();
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
                    if(Library.equipTruckSet.size()>0)
                    { CheckRespectStatus();
                        CompareSet(); }

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckSet.size() > 0 && CompareSet())
                    {
                        TruckType="Set";
                        Library.activeTruckSet.add(Library.equipTruckSet.get(Library.equipTruckSet.size()-1));
                        Library.equipTruckSet.remove(0);
                        FindFreeTask();
                        IdentityLabels.get(mainElementOfTask).setText("Set");
                        PrepareActiveTaskInMainPanel(mainElementOfTask);
                        CheckAmountsOfTrucks();
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

                    if(Library.equipTruckTank.size()>0)
                    { CheckRespectStatus();
                        CompareTank(); }

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckTank.size() > 0 && CompareTank())
                    {
                        TruckType="Tank";
                        Library.activeTruckTank.add(Library.equipTruckTank.get(Library.equipTruckTank.size()-1));
                        Library.equipTruckTank.remove(0);

                        FindFreeTask();
                        IdentityLabels.get(mainElementOfTask).setText("Tank");

                        PrepareActiveTaskInMainPanel(mainElementOfTask);
                        CheckAmountsOfTrucks();
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
                    if(Library.equipTruckTipCart.size()>0)
                    { CheckRespectStatus();
                        CompareTipCart(); }

                    if (RoutesTable.getSelectionModel().getFocusedIndex() != 0 && Library.equipTruckTipCart.size() > 0 && CompareTipCart())
                    {

                        TruckType="TipCart";
                        Library.activeTruckTipCart.add(Library.equipTruckTipCart.get(Library.equipTruckTipCart.size()-1));
                        Library.equipTruckTipCart.remove(0);

                        FindFreeTask();
                        IdentityLabels.get(mainElementOfTask).setText("TipCart");

                        PrepareActiveTaskInMainPanel(mainElementOfTask);
                        CheckAmountsOfTrucks();
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









