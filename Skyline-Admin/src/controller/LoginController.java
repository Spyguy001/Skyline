package controller;

import Database.DatabaseHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.io.IOException;
import java.util.List;

public class LoginController {
    private FirebaseAuthHandler authHandler;
    private IDatabase database;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label loginFailed;

    @FXML
    private void initialize() throws IOException {
        this.authHandler = new FirebaseAuthHandler();
        this.database = new DatabaseHandler();

        //press enter to login
        rootPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if (key.getCode().equals(KeyCode.ENTER)){
                    try{
                        signIn();
                    }catch(IOException e){ }
                }
            }
        });
    }

    @FXML
    private void signIn() throws IOException {
        String uid;
        User user;

        try {
            uid = this.authHandler.verifyUserAuth(username.getText(), password.getText());
        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            loginFailed.setText(e.getMessage());
            return;
        }

        user = this.database.getUser(uid);
        int userLevel = user.getLevel();
        if(userLevel == 1) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Manager.fxml"));
            AnchorPane pane = fxmlLoader.load();
            ManagerController controller = fxmlLoader.<ManagerController>getController();
            CondoManager manager = (CondoManager) user;
            manager.setDatabase(this.database);
            manager.setCondos(this.database.getCondosForUser(manager.getId()));

            for(Condo condo : manager.getCondos()){
                condo.setEvents(this.database.getEventsForCondo(condo.getId()));
                condo.setAmenities(this.database.getAmenitiesForCondo(condo.getId()));
                condo.setAnnouncements(this.database.getAnnouncementsForCondo(condo.getId()));
                condo.setManagers(this.database.getManagersForCondo(condo.getId()));
                condo.setResidents(this.database.getResidentsForCondo(condo.getId()));
            }

            controller.setManager(manager);
            controller.setupPanes();

            rootPane.getChildren().setAll(pane);
        }
        else if(userLevel == 2) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Owner.fxml"));
            AnchorPane pane = fxmlLoader.load();
            OwnerController controller = fxmlLoader.<OwnerController>getController();

            CondoOwner owner = (CondoOwner)user;
            owner.setCondos(database.getCondosForUser(owner.getId()));
            owner.setDatabase(database);
            controller.setDatabase(database);
            controller.setCondoOwner(owner);

            rootPane.getChildren().setAll(pane);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Need to be a condo owner or manager to login!");
            alert.setHeaderText("Insufficient Access Level");
            alert.showAndWait();
        }
    }
}
