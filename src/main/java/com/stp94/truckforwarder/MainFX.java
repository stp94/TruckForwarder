package com.stp94.truckforwarder;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MainFX extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;
    boolean gamestatus=true;
    int x=0;

    private static double xOffset = 0;
    private static double yOffset = 0;



    @Override
    public void start(Stage primaryStage) throws Exception {

        //Parent root = FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
        //Scene scene = new Scene(root);
        primaryStage.setTitle("Spedytor");
        primaryStage.setScene(new Scene(root, 1800, 800));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.getFullScreenExitHint();
        primaryStage.show();





    }



    public void init() throws  Exception{
        springContext = SpringApplication.run(MainFX.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }
    public void stop() throws Exception{
        springContext.close();
    }








    public static void main(String[] args) {



        Application.launch(args);
    }
}
