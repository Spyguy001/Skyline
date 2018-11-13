package controller;

import Database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Index.fxml"));
            AnchorPane pane = fxmlLoader.load();
            //ManagerController controller = fxmlLoader.<ManagerController>getController();
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
