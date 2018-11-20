package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Resident;

public class ResidentsController {
    @FXML
    private TableView<Resident> residentsTable;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField lot;

    @FXML
    public void initialize(){}

    @FXML
    private void addResident(){
        if (name.getText().equals("") || lot.getText().equals("") || email.getText().equals("")){
            // TODO: Need to implement email validation.
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else if (password.getText().length() < 6){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords must be at least 6 characters!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
                // TODO: Add a resident to the database, the following code is for testing
                Resident resident = new Resident();
                resident.setId("1");
                //resident.setCondos();
                //resident.setLevel();
                resident.setName(name.getText());
                resident.setLot(lot.getText());
                residentsTable.getItems().add(resident);
        }
    }

    @FXML
    private void removeResident(){
        if (residentsTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a resident!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            // TODO: Remove an event from the database
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + residentsTable.getSelectionModel().getSelectedItem().getName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                residentsTable.getItems().remove(residentsTable.getSelectionModel().getSelectedItem());
            }
        }
    }
}
