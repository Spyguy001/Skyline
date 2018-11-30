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

    /**
     * Sets the data access object to get the data from and put data into
     * @param database the data access object
     */
    public void setDatabase(IDatabase database) {
        this.database = database;
    }

    /**
     * Sets the manager to be used for the condos
     * @param manager the manager to be set
     */
    public void setManager(CondoManager manager) {
        this.manager = manager;
        this.switchCondo(DEFAULT);
    }

    /**
     * Selects a specific condo the manager is going to manage
     * @param index the index for the condo to be selected from the manager's list of condos
     */
    public void switchCondo(int index){
        this.condo = manager.getCondos().get(index);
    }

    /**
     * Makes the manager GUI by loading in the events
     */
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

    /**
     * Loads the events GUI by setting all the attributes for its contructor and the related objects
     * @throws IOException the exception to be thrown if the events GUI file cannot be found
     */
    @FXML
    private void loadEvents()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Events.fxml"));
        AnchorPane pane = fxmlLoader.load();
        EventsController controller = fxmlLoader.<EventsController>getController();
        controller.setManager(this.manager);
        controller.setCondo(this.condo);
        controller.loadEventsForCondo();
        selectedButtonPane.getChildren().setAll(pane);
        activePane = "events";
    }

    /**
     * Loads the amenities GUI by setting all the attributes for its contructor and the related objects
     * @throws IOException the exception to be thrown if the amenities GUI file cannot be found
     */
    @FXML
    private void loadAmenities()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Amenities.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AmenitiesController controller = fxmlLoader.<AmenitiesController>getController();
        controller.setManager(this.manager);
        controller.setCondo(this.condo);
        controller.loadAmenitiesForCondo();
        selectedButtonPane.getChildren().setAll(pane);
        activePane = "amenities";
    }

    /**
     * Loads the announcements GUI by setting all the attributes for its contructor and the related objects
     * @throws IOException the exception to be thrown if the announcements GUI file cannot be found
     */
    @FXML
    private void loadAnnouncements()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Announcements.fxml"));
        AnchorPane pane = fxmlLoader.load();
        AnnouncementsController controller = fxmlLoader.<AnnouncementsController>getController();
        controller.setManager(this.manager);
        controller.setCondo(this.condo);
        controller.loadAnnouncementsForCondo();
        selectedButtonPane.getChildren().setAll(pane);
        activePane = "announcements";
    }

    /**
     * Loads the residents GUI by setting all the attributes for its contructor and the related objects
     * @throws IOException the exception to be thrown if the residents GUI file cannot be found
     */
    @FXML
    private void loadResidents()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Residents.fxml"));
        AnchorPane pane = fxmlLoader.load();
        ResidentsController controller = fxmlLoader.<ResidentsController>getController();
        //TODO: Set database, manager, condo using singleton
        controller.setManager(this.manager);
        controller.setCondo(this.condo);
        controller.loadResidentsForCondo();
        selectedButtonPane.getChildren().setAll(pane);
        activePane = "residents";
    }

    /**
     * Signs the user out from this view and takes them to the login page
     * @throws IOException the exception to be thrown if the login page GUI file cannot be found
     */
    @FXML
    private void signOut() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        AnchorPane pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Refreshes the condo and retrieves its latest changes from the database, updating the proper GUI view
     * @throws IOException the exception to be thrown if the respective GUI files cannot be found
     */
    @FXML
    private void refresh() throws IOException{
        //if the condo gets deleted as it is being worked on, sign the manager out
        if(!this.database.hasCondo(this.condo.getId())){
            this.signOut();
        }

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
