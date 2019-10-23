package com.stp94.truckforwarder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFX extends Application {

    boolean gamestatus=true;
    int x=0;



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
        //Scene scene = new Scene(root);
        primaryStage.setTitle("Spedytor");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getFullScreenExitHint();
        primaryStage.show();






    }








    public static void main(String[] args) {



        launch(args);
    }
}
