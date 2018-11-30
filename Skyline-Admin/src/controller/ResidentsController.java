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

    /** Needed for JavaFx */
    @FXML
    public void initialize(){}

    /**
     * Attaches the condo to operate on to this controller.
     * Needs to be called before UI switches to residents scene.
     *
     * @param condo the condo whose residents we display/manipulate.
     */
    public void setCondo(Condo condo) {
        this.condo = condo;
    }

    /**
     * Attaches the current manager to this controller
     * Needs to be called before UI switches to residents scene.
     *
     * @param manager the manager who is performing actions.
     */
    public void setManager(CondoManager manager) {
        this.manager = manager;
    }

    /**
     * Fetches the amenities from the condo model and populates the view.
     */
    public void loadResidentsForCondo(){
        for(Resident resident : this.condo.getResidents()){
            residentsTable.getItems().add(resident);
        }
    }

    /**
     * Fired when user clicks add resident.
     * Creates a new user account for the resident and associates them to the condo.
     */
    @FXML
    private void addResident(){
        if (name.getText().equals("") || lot.getText().equals("") || email.getText().equals("")){
            // TODO: Need to implement email validation.
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the required fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else if (password.getText().length() < 6){
            // invalid password length
            Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords must be at least 6 characters!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
                Resident resident = new Resident();
                String uid;
                try {
                    // create a new account in firebase auth
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

    /**
     * Fired when user clicks removeResident
     * Disassociates the user from the condo model (by extension the database) and updates the view.
     */
    @FXML
    private void removeResident(){
        if (residentsTable.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a resident!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            // confirmation dialog
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
