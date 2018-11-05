package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import model.Event;
import model.IDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventsController {
    @FXML
    private TableView<Event> eventsTable;

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private TextField date;

    @FXML
    private void initialize(){
    }

    @FXML
    private void addEvent(){
        if (title.getText().equals("") || description.getText().equals("") || date.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            try {
                // TODO: Add an event to the database, the following code is for testing
                Event ev = new Event();
                ev.setTitle(title.getText());
                Date dateA = new SimpleDateFormat("dd/MM/yyyy").parse(date.getText());
                ev.setDate(dateA);
                ev.setDescription(description.getText());
                eventsTable.getItems().add(ev);
            }catch(ParseException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid date!", ButtonType.CANCEL);
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void removeEvent(){
        if (eventsTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an event!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            // TODO: Remove an event from the database
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + eventsTable.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                eventsTable.getItems().remove(eventsTable.getSelectionModel().getSelectedItem());
            }
        }
    }
}