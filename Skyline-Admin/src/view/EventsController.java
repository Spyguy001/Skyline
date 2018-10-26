package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class EventsController {
    @FXML
    private ListView<String> eventsList;

    @FXML
    private void initialize(){}

    @FXML
    private void addEvent(){
        // TODO: Implement a dialog box with more fields
        TextInputDialog dialog = new TextInputDialog("New Event");

        dialog.setTitle("Add New Event");
        dialog.setHeaderText(null);
        dialog.setContentText("Event Title:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(title -> {
            eventsList.getItems().add(title);
        });
    }

    @FXML
    private void removeEvent(){
        if (eventsList.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an event!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + eventsList.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                eventsList.getItems().remove(eventsList.getSelectionModel().getSelectedIndex());
            }
        }
    }
}