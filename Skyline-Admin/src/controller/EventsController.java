package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private TextField time;

    @FXML
    private ChoiceBox<String> timeChoice;

    @FXML
    private void initialize(){
        //eventsTable.getColumns().forEach(this::addDescriptionTooltip);
        // TODO: Initialize eventsTable with data from the database.
    }

    private <T> void addDescriptionTooltip(TableColumn<Event,T> column) {
        Callback<TableColumn<Event, T>, TableCell<Event,T>> existingCellFactory
                = column.getCellFactory();

        column.setCellFactory(c -> {
            TableCell<Event, T> cell = existingCellFactory.call(c);

            Tooltip tooltip = new Tooltip();
            tooltip.textProperty().bind(cell.itemProperty().asString());

            cell.setTooltip(tooltip);
            return cell ;
        });
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
                String dateWithTime = date.getText() + " " + time.getText() + " " + timeChoice.getValue();
                Date dateA = new SimpleDateFormat("dd/MM/yyyy h:mm a").parse(dateWithTime);
                ev.setDate(dateA);
                ev.setDescription(description.getText());
                String[] hoursMinutes = time.getText().split(":");
                if (Integer.parseInt(hoursMinutes[0]) < 1 || Integer.parseInt(hoursMinutes[0]) > 12 || Integer.parseInt(hoursMinutes[0]) < 0 || Integer.parseInt(hoursMinutes[1]) > 59){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid time!", ButtonType.CANCEL);
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }else {
                    eventsTable.getItems().add(ev);
                    createEventPopup();
                }
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + eventsTable.getSelectionModel().getSelectedItem().getTitle() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                eventsTable.getItems().remove(eventsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    private void createEventPopup(){
        eventsTable.setRowFactory( tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (! row.isEmpty()) ) {
                    // Create new stage for the dialog window
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(eventsTable.getScene().getWindow());
                    Event event = eventsTable.getSelectionModel().getSelectedItem();
                    dialog.setTitle(event.getTitle());

                    // Create the grid pane containing all the info
                    GridPane gridPane = new GridPane();
                    gridPane.setId("dialog-window");

                    // Add constraints to the grid pane
                    ColumnConstraints c1 = new ColumnConstraints();
                    c1.setPercentWidth(25);
                    ColumnConstraints c2 = new ColumnConstraints();
                    c2.setPercentWidth(75);
                    gridPane.getColumnConstraints().addAll(c1, c2);
                    RowConstraints r1 = new RowConstraints();
                    r1.setPercentHeight(25);
                    RowConstraints r2 = new RowConstraints();
                    r2.setPercentHeight(75);
                    gridPane.getRowConstraints().addAll(r1, r2);

                    // Add the info to the grid pane
                    gridPane.add(new Label("Date:"), 0, 0);
                    gridPane.add(new Label(event.getDate().toString()), 1, 0);
                    gridPane.add(new Label("Description:"), 0, 1);
                    Label desc = new Label(event.getDescription());
                    desc.setWrapText(true);
                    gridPane.add(desc, 1, 1);

                    // Create the new scene
                    Scene scene = new Scene(gridPane, 300, 200);
                    scene.getStylesheets().add("view/StylesheetGlobal.css");
                    dialog.setScene(scene);
                    dialog.show();
                }
            });
            return row ;
        });
    }
}