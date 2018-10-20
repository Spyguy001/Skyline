package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/creator.fxml"));
        primaryStage.setTitle("Skyline");
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("view/stylesheet_new.css");
        primaryStage.show();

        // Testing out the lists for condos and condo managers
    }


    public static void main(String[] args) {
        launch(args);
    }
}
