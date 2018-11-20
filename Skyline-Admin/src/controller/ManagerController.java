package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.Condo;
import model.CondoManager;
import model.IDatabase;

import java.io.IOException;

public class ManagerController {

    private int DEFAULT = 0;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonEvents;
    @FXML
    private AnchorPane selectedButtonPane;

    private String activePane;

    private IDatabase database;
    private CondoManager manager;
    private Condo condo;

    public void setDatabase(IDatabase database) {
        this.database = database;
    }

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
        activePane = "events";
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
        activePane = "amenities";
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
        activePane = "announcements";
    }

    @FXML
    private void loadResidents()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Residents.fxml"));
        AnchorPane pane = fxmlLoader.load();
        ResidentsController controller = fxmlLoader.<ResidentsController>getController();
        //TODO: Set database, manager, condo using singleton
        //controller.setManager(this.manager);
        //controller.setCondo(this.condo);
        //controller.loadResidentsForCondo();
        selectedButtonPane.getChildren().setAll(pane);
        activePane = "residents";
    }
    @FXML
    private void signOut() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        AnchorPane pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void refresh() throws IOException{
        //get all the condo attributes again
        this.condo = this.database.getCondo(this.condo.getId());
        this.condo.setEvents(this.database.getEventsForCondo(condo.getId()));
        this.condo.setAmenities(this.database.getAmenitiesForCondo(condo.getId()));
        this.condo.setAnnouncements(this.database.getAnnouncementsForCondo(condo.getId()));
        this.condo.setManagers(this.database.getManagersForCondo(condo.getId()));
        this.condo.setResidents(this.database.getResidentsForCondo(condo.getId()));

        //refresh the right scene
        switch(activePane){
            case("events"):
                loadEvents();
                break;
            case("amenities"):
                loadAmenities();
                break;
            case("announcements"):
                loadAnnouncements();
                break;
            case("residents"):
                loadResidents();;
                break;
        }
    }
}
