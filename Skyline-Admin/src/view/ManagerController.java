package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class ManagerController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private void initialize(){}

    @FXML
    private void loadEvents()throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Events.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadAmenities()throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Amenities.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadAnnouncements()throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Announcements.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
