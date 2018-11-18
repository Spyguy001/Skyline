package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Amenity;
import model.Announcement;
import model.Condo;
import model.CondoManager;

import java.util.Date;

public class AnnouncementsController {
    @FXML
    private TableView<Announcement> announcementsTable;

    @FXML
    private TableColumn<Announcement, Boolean> impCol;

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private CheckBox important;

    private CondoManager manager;
    private Condo condo;

    public void setCondo(Condo condo) {
        this.condo = condo;
    }

    public void setManager(CondoManager manager) {
        this.manager = manager;
    }

    public void loadAnnouncementsForCondo(){
        for (Announcement announcement: this.condo.getAnnouncements()){
            announcementsTable.getItems().add(announcement);
        }
    }

    @FXML
    private void initialize() {
        impCol.setCellFactory(col -> new TableCell<Announcement, Boolean>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                setGraphic(imageView);
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                if (empty || item == null) {
                    imageView.setImage(null);
                } else {
                    imageView.setImage(item ? new Image("view/important-small.png") : null);
                }
            }
        });
    }

    @FXML
    private void addAnnouncement() {
        if (title.getText().equals("") || description.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Announcement an = new Announcement();
            an.setTitle(title.getText());
            an.setId(Long.toString(System.currentTimeMillis()));
            //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            an.setDate(date);
            an.setDescription(description.getText());
            an.setImportant(important.isSelected());
            this.manager.addAnnouncementToCondo(an, this.condo);
            announcementsTable.getItems().add(an);
            createAnnouncementPopup();
        }
    }

    @FXML
    private void removeAnnouncement() {
        if (announcementsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an announcement!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + announcementsTable.getSelectionModel()
                    .getSelectedItem().getTitle() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                this.manager.removeAnnouncementFromCondo(announcementsTable.getSelectionModel().getSelectedItem(), this.condo);
                announcementsTable.getItems().remove(announcementsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    private void createAnnouncementPopup() {
        announcementsTable.setRowFactory(tv -> {
            TableRow<Announcement> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty())) {
                    // Create new stage for the dialog window
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(announcementsTable.getScene().getWindow());
                    Announcement an = announcementsTable.getSelectionModel().getSelectedItem();
                    dialog.setTitle(an.getTitle());

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
                    gridPane.add(new Label(an.getDate().toString()), 1, 0);
                    gridPane.add(new Label("Description:"), 0, 1);
                    Label desc = new Label(an.getDescription());
                    desc.setWrapText(true);
                    gridPane.add(desc, 1, 1);

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
