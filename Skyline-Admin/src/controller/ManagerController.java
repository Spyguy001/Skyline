package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ManagerController {

    @FXML
    private Button buttonEvents;
    @FXML
    private AnchorPane selectedButtonPane;
    @FXML
    private void initialize(){
        buttonEvents.requestFocus();
    }

    @FXML
    private void loadEvents()throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Events.fxml"));
        selectedButtonPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadAmenities()throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Amenities.fxml"));
        selectedButtonPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadAnnouncements()throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Announcements.fxml"));
        selectedButtonPane.getChildren().setAll(pane);
    }
}
