package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Condo;
import model.CondoManager;
import model.Event;
import model.IDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EventsController {
    @FXML
    private TableView<Event> eventsTable;

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker date;

    @FXML
    private TextField time;

    @FXML
    private ChoiceBox<String> timeChoice;

    @FXML
    private TextField loc;

    private IDatabase database;
    private CondoManager manager;
    private Condo condo;

    /**
     * Sets the condo in which the events are happening
     * @param condo the condo in which the events are happening
     */
    public void setCondo(Condo condo) {
        this.condo = condo;
    }

    /**
     * Sets the manager managing the events currently
     * @param manager the manager currently managing the events
     */
    public void setManager(CondoManager manager) {
        this.manager = manager;
    }

    /**
     * Loads the events from the condo and sets them in the GUI
     */
    public void loadEventsForCondo(){
        for(Event event : this.condo.getEvents()){
            eventsTable.getItems().add(event);
        }
        createEventPopup();
    }

    /**
     * Initializes the GUI with a default date value
     */
    @FXML
    private void initialize(){
        date.setValue(LocalDate.now());
    }

    /**
     * Adds an event to the condo based on the values in the GUI fields
     */
    @FXML
    private void addEvent(){
        if (title.getText().equals("") || description.getText().equals("") || date.getValue() == null || loc.getText() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            //making the event and adding it to the condo's list of events
            Event event = new Event();
            event.setTitle(title.getText());
            String timeStr = time.getText() + " " + timeChoice.getValue();
            event.setLocation(loc.getText());
            LocalDateTime dateTime = date.getValue()
                    .atTime(LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("h:mm a")));
            Date dateA = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            event.setDate(dateA);
            event.setId(Long.toString(System.currentTimeMillis()));
            event.setDescription(description.getText());
            String[] hoursMinutes = time.getText().split(":");

            //checking for a valid time
            if (Integer.parseInt(hoursMinutes[0]) < 1 || Integer.parseInt(hoursMinutes[0]) > 12 || Integer.parseInt(hoursMinutes[0]) < 0 || Integer.parseInt(hoursMinutes[1]) > 59){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid time!", ButtonType.CANCEL);
                alert.setHeaderText(null);
                alert.showAndWait();
            }else {
                this.manager.addEventToCondo(event, this.condo);
                eventsTable.getItems().add(event);
                createEventPopup();
            }
        }
    }

    /**
     * Removes the selected event from the condo
     */
    @FXML
    private void removeEvent(){
        if (eventsTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an event!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + eventsTable.getSelectionModel().getSelectedItem().getTitle() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Event event = eventsTable.getSelectionModel().getSelectedItem();
                this.manager.removeEventFromCondo(event, this.condo);
                eventsTable.getItems().remove(event);
            }
        }
    }

    /**
     * Creates a popup showing the details for an event
     */
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

                    gridPane.setPadding(new Insets(10, 10, 10, 10));

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