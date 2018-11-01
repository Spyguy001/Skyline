package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class AnnouncementsController {
    @FXML
    private ListView<String> announcementList;

    @FXML
    private void initialize(){
    }

    @FXML
    private void addAnnouncement(){
        // TODO: Implement a dialog box with more fields
        TextInputDialog dialog = new TextInputDialog("New Announcement");

        dialog.setTitle("Add New Announcement");
        dialog.setHeaderText(null);
        dialog.setContentText("Announcement Title:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(title -> {
            announcementList.getItems().add(title);
        });
    }

    @FXML
    private void removeAnnouncement(){
        if (announcementList.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an announcement!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + announcementList.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                announcementList.getItems().remove(announcementList.getSelectionModel().getSelectedIndex());
            }
        }
    }
}
