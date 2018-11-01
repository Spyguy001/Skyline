package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class AmenitiesController {
    @FXML
    private ListView<String> amenityList;

    @FXML
    private void initialize(){
    }

    @FXML
    private void addAmenity(){
        // TODO: Implement a dialog box with more fields
        TextInputDialog dialog = new TextInputDialog("New Amenity");

        dialog.setTitle("Add New Amenity");
        dialog.setHeaderText(null);
        dialog.setContentText("Amenity Title:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(title -> {
            amenityList.getItems().add(title);
        });
    }

    @FXML
    private void removeAmenity(){
        if (amenityList.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an amenity!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + amenityList.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                amenityList.getItems().remove(amenityList.getSelectionModel().getSelectedIndex());
            }
        }
    }
}
