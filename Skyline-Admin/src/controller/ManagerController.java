package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.Condo;
import model.CondoManager;
import model.Event;
import model.IDatabase;

import java.io.IOException;

public class ManagerController {

    private int DEFAULT = 0;

    @FXML
    private Button buttonEvents;
    @FXML
    private AnchorPane selectedButtonPane;

    private IDatabase database;
    private CondoManager manager;
    private Condo condo;

    public void setManager(CondoManager manager) {
        this.manager = manager;
        this.switchCondo(DEFAULT);
    }

    public void switchCondo(int index){
        this.condo = manager.getCondos().get(index);
    }

    @FXML
    private void initialize(){
        buttonEvents.requestFocus();
    }

    public void setupPanes(){
        try{
            loadEvents();
        }
        catch (IOException e){
            return;
        }
    }

    @FXML
    private void loadEvents()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Events.fxml"));
        AnchorPane pane = fxmlLoader.load();
        EventsController controller = fxmlLoader.<EventsController>getController();
        //TODO: Set database, manager, condo using singleton
        controller.setManager(this.manager);
        controller.setCondo(this.condo);
        controller.loadEventsForCondo();
        selectedButtonPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadAmenities()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Amenities.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AmenitiesController controller = fxmlLoader.<AmenitiesController>getController();
        //TODO: Set database, manager, condo using singleton
        controller.setManager(this.manager);
        controller.setCondo(this.condo);
        controller.loadAmenitiesForCondo();
        selectedButtonPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadAnnouncements()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Announcements.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AnnouncementsController controller = fxmlLoader.<AnnouncementsController>getController();
        //TODO: Set database, manager, condo using singleton
        controller.setManager(this.manager);
        controller.setCondo(this.condo);
        controller.loadAnnouncementsForCondo();
        selectedButtonPane.getChildren().setAll(pane);
    }
}
