package controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import javafx.beans.value.ChangeListener;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OwnerController {

    private HashMap<Condo, List<CondoManager>> condosToManagers = new HashMap<>();
    private CondoOwner condoOwner;
    private IDatabase database;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ListView<Condo> listCondos;

    @FXML
    private ListView<CondoManager> listManagers;

    @FXML
    private TextField nameCondo;

    @FXML
    private TextField nameManager;

    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    /**
     * Sets the data access object
     * @param database the data access object
     */
    public void setDatabase(IDatabase database){
        this.database = database;
    }

    /**
     * Sets the condoOwner to be used
     * @param condoOwner the condoOwner to be used
     */
    public void setCondoOwner(CondoOwner condoOwner){
        this.condoOwner = condoOwner;
        this.updateCondosToManagersMap();
    }

    /**
     * Fills the GUI list of condos and managers with ones retrieved from the database
     */
    private void updateCondosToManagersMap(){
        this.condosToManagers = new HashMap<>();
        for(Condo c : this.condoOwner.getCondos()){
            this.condosToManagers.put(c, this.database.getManagersForCondo(c.getId()));
            listCondos.getItems().add(c);
        }
    }

    /**
     * Creates a new condo based on the values in the fields in the GUI
     */
    @FXML
    private void createCondo(){
        // Check if all required fields are filled in
        if (nameCondo.getText().equals("") || address.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in both the name and address fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            //making the condo based on the fields and adding it to the condo owner's list of condos
            Condo condo = new Condo();
            condo.setAddress(address.getText());
            condo.setName(nameCondo.getText());
            condo.setId(address.getText());
            condo.setOwnerId(this.condoOwner.getId());

            this.condoOwner.addCondo(condo);

            //adding the condo to the list of condos in the GUI
            listCondos.getItems().add(condo);
            condosToManagers.put(condo, new ArrayList<CondoManager>());
        }
    }

    /**
     * Adds a manager to the selected condo, based on the values of the fields in the GUI
     */
    @FXML
    private void addManager(){
        // Check if all the required fields are filled in
        if (nameManager.getText().equals("") || email.getText().equals("") || password.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in the name, email, password fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            Condo selected = listCondos.getSelectionModel().getSelectedItem();
            // Check if there is a condo selected
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a Condo!", ButtonType.CANCEL);
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                //making a manager object and adding it to the condo and the condo owner's lists
                CondoManager manager = new CondoManager();
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
                manager.setId(uid);
                manager.setName(nameManager.getText());
                manager.setLevel(1);
                ArrayList<Condo> condoList = new ArrayList<>();
                condoList.add(selected);
                manager.setCondos(condoList);

                this.condoOwner.addManager(manager, selected);

                //adding the manager to the list of managers for a selected condo in the GUI
                if (condosToManagers.get(selected) == null) {
                    ArrayList<CondoManager> list = new ArrayList<>();
                    list.add(manager);
                    condosToManagers.put(selected, list);
                    listManagers.getItems().add(manager);
                } else {
                    condosToManagers.get(selected).add(manager);
                    listManagers.getItems().add(manager);
                }
            }
        }
    }

    /**
     * Deletes a manager from the selected condo
     */
    @FXML
    private void deleteManager(){
        CondoManager selected = listManagers.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a manager to delete!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + listManagers.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                this.condoOwner.removeManager(selected, listCondos.getSelectionModel().getSelectedItem());
                listManagers.getItems().remove(selected);
                condosToManagers.get(listCondos.getSelectionModel().getSelectedItem()).remove(selected);
            }
        }
    }

    /**
     * Deletes the selected condo
     */
    @FXML
    private void deleteCondo(){
        Condo selected = listCondos.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a condo to delete!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + listCondos.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                this.condoOwner.removeCondo(selected);
                listCondos.getItems().remove(selected);
            }
        }
    }

    /**
     * Initializes the GUI, making empty lists for condos and managers
     */
    @FXML
    private void initialize(){
        listCondos.setCellFactory(param -> new ListCell<Condo>(){
            @Override
            protected void updateItem(Condo condo, boolean empty){
                super.updateItem(condo, empty);
                if (empty || condo == null || condo.getName() == null){
                    setText(null);
                }else{
                    setText(condo.getName());
                }
            }
        });

        listManagers.setCellFactory(param -> new ListCell<CondoManager>(){
            @Override
            protected void updateItem(CondoManager manager, boolean empty){
                super.updateItem(manager, empty);
                if (empty || manager == null || manager.getName() == null){
                    setText(null);
                }else{
                    setText(manager.getName());
                }
            }
        });

        listCondos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Condo>() {
            @Override
            public void changed(ObservableValue<? extends Condo> observable, Condo oldValue, Condo newValue) {
                listManagers.getItems().clear();
                listManagers.getItems().addAll(condosToManagers.get(newValue)); }
        });
    }

    /**
     * Signs out the user from this page and takes him to the login page
     * @throws IOException if the login page cannot be found and loaded
     */
    @FXML
    private void signOut() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
        AnchorPane pane = fxmlLoader.load();
        rootPane.getChildren().setAll(pane);
    }

    /**
     * Refreshes the page, getting the condos and owner attributes from the database and updating the GUI accordingly
     */
    @FXML
    private void refresh(){
        this.condoOwner = (CondoOwner)this.database.getUser(this.condoOwner.getId());
        this.condoOwner.setCondos(this.database.getCondosForUser(this.condoOwner.getId()));
        this.updateCondosToManagersMap();
    }
}


