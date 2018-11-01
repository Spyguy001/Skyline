package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DatabaseInteractor;
import model.FirebaseAuthHandler;

import java.io.IOException;
import java.util.Map;

public class LoginController {
    private FirebaseAuthHandler authHandler;
    private DatabaseInteractor dbInteractor;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label loginFailed;

    @FXML
    private void initialize() throws IOException {
        this.authHandler = new FirebaseAuthHandler();
        this.dbInteractor = new DatabaseInteractor();
    }

    @FXML
    private void signIn() throws IOException {
        String uid;
        Map<String, String> user;

        try {
            uid = this.authHandler.verifyUserAuth(username.getText(), password.getText());
            user = this.dbInteractor.getUser(uid);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            loginFailed.setText(e.getMessage());
            return;
        }

        int userLevel = Integer.parseInt(user.get("level"));
        if(userLevel == 1) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Index.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        else if(userLevel == 2) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Owner.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Need to be a condo owner or manager to login!");
            alert.setHeaderText("Insufficient Access Level");
            alert.showAndWait();
        }

        // TODO: Implement User Authentication
        /*if (username.getText().equals("owner")){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Owner.fxml"));
            rootPane.getChildren().setAll(pane);
        }else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Index.fxml"));
            rootPane.getChildren().setAll(pane);
        }*/
    }
}
