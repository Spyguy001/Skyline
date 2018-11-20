package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.util.ArrayList;

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

    private Condo condo;

    private CondoManager manager;

    @FXML
    public void initialize(){}

    public void setCondo(Condo condo) {
        this.condo = condo;
    }

    public void setManager(CondoManager manager) {
        this.manager = manager;
    }

    public void loadResidentsForCondo(){
        for(Resident resident : this.condo.getResidents()){
            residentsTable.getItems().add(resident);
        }
    }

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
                String uid;
                try {
                    uid = new FirebaseAuthHandler().createUserAcc(email.getText(), password.getText());
                }
                catch (IllegalArgumentException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    return;
                }
                resident.setId(uid);
                resident.setLevel(0);
                resident.setName(name.getText());
                ArrayList<Condo> condoList = new ArrayList<>();
                condoList.add(condo);
                resident.setCondos(condoList);
                resident.setLot(lot.getText());

                this.manager.addResidentToCondo(resident, condo);

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
                this.manager.removeResidentFromCondo(residentsTable.getSelectionModel().getSelectedItem(), condo);
                residentsTable.getItems().remove(residentsTable.getSelectionModel().getSelectedItem());
            }
        }
    }
}
