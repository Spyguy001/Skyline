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
import model.Amenity;
import model.Condo;
import model.CondoManager;

public class AmenitiesController {
    @FXML
    private TableView<Amenity> amenitiesTable;

    @FXML
    private TextField name;

    @FXML
    private TextArea details;

    @FXML
    private CheckBox bookable;

    @FXML
    private TextField interval;

    private CondoManager manager;
    private Condo condo;

    /**
     * Attaches the condo to operate on to this controller.
     * Needs to be called before UI switches to amenity scene.
     *
     * @param condo the condo whose amenities we display/manipulate.
     */
    public void setCondo(Condo condo) {
        this.condo = condo;
    }

    /**
     * Attaches the current manager to this controller
     * Needs to be called before UI switches to amenity scene.
     *
     * @param manager the manager who is performing actions.
     */
    public void setManager(CondoManager manager) {
        this.manager = manager;
    }

    /**
     * Fetches the amenities from the condo model and populates the view.
     */
    public void loadAmenitiesForCondo() {
        for (Amenity amenity : this.condo.getAmenities()) {
            amenitiesTable.getItems().add(amenity);
        }
        createAmenityPopup();
    }

    /**
     * Fired when user clicks add amenity button.
     * Updates the model(by extension, the database) and view to reflect the new amenity.
     */
    @FXML
    private void addAmenity() {
        if (name.getText().equals("") || details.getText().equals("") || (bookable.isSelected() && interval.getText().equals(""))) {
            // missing required fields
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            int inter = Integer.parseInt(interval.getText());
            if (bookable.isSelected() && (inter > 24 || inter < 0)) {
                // invalid time interval entered
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid time interval!", ButtonType.CANCEL);
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                Amenity am = new Amenity();
                am.setName(name.getText());
                am.setDetails(details.getText());
                am.setBookable(bookable.isSelected());
                am.setIntervalAllowed(Integer.parseInt(interval.getText()));
                am.setId(Long.toString(System.currentTimeMillis()));
                this.manager.addAmenityToCondo(am, this.condo);
                amenitiesTable.getItems().add(am);
                createAmenityPopup();
            }
        }
    }

    /**
     * Fired when user clicks remove amenity.
     * Removes the amenity from the view and makes the manager model remove the amenity from the database.
     */
    @FXML
    private void removeAmenity() {
        if (amenitiesTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an amenity!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            // confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " +
                    amenitiesTable.getSelectionModel().getSelectedItem().getName() + " ?", ButtonType.YES,
                    ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Amenity toRemove = amenitiesTable.getSelectionModel().getSelectedItem();
                this.manager.removeAmenityFromCondo(toRemove, this.condo);
                amenitiesTable.getItems().remove(toRemove);
            }
        }
    }

    /**
     * Updates the interval textfield based on bookable state
     */
    @FXML
    private void isBookable() {
        if (bookable.isSelected()) {
            interval.setDisable(false);
        } else {
            interval.setDisable(true);
        }
    }

    /**
     * Called when user double clicks an amenity entry
     * Displays the selected amenity info in a pop up window
     */
    private void createAmenityPopup() {
        amenitiesTable.setRowFactory(tv -> {
            TableRow<Amenity> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty())) {
                    // Create new stage for the dialog window
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(amenitiesTable.getScene().getWindow());
                    Amenity am = amenitiesTable.getSelectionModel().getSelectedItem();
                    dialog.setTitle(am.getName());

                    // Create the grid pane containing all the info
                    GridPane gridPane = new GridPane();
                    gridPane.setId("dialog-window");

                    // Add constraints to the grid pane
                    ColumnConstraints c1 = new ColumnConstraints();
                    c1.setPercentWidth(100);
                    gridPane.getColumnConstraints().addAll(c1);
                    RowConstraints r1 = new RowConstraints();
                    r1.setPercentHeight(25);
                    RowConstraints r2 = new RowConstraints();
                    r2.setPercentHeight(75);
                    gridPane.getRowConstraints().addAll(r1, r2);

                    gridPane.setPadding(new Insets(10, 10, 10, 10));

                    // Add the info to the grid pane
                    gridPane.add(new Label("Details:"), 0, 0);
                    Label details = new Label(am.getDetails());
                    details.setWrapText(true);
                    gridPane.add(details, 0, 1);

                    // Create the new scene
                    Scene scene = new Scene(gridPane, 300, 200);
                    scene.getStylesheets().add("view/StylesheetGlobal.css");
                    dialog.setScene(scene);
                    dialog.show();
                }
            });
            return row;
        });
    }
}
