package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.io.IOException;

public class LoginController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private void initialize(){}

    @FXML
    private void signIn() throws IOException {
        // TODO: Implement User Authentication
        if (username.getText().equals("owner")){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Owner.fxml"));
            rootPane.getChildren().setAll(pane);
        }else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Index.fxml"));
            rootPane.getChildren().setAll(pane);
        }
    }
}
