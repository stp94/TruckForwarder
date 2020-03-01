package com.stp94.truckforwarder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MainFX extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;




    @Override
    public void start(Stage primaryStage) throws Exception {


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
