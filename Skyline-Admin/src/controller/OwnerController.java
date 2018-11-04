package controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.beans.value.ChangeListener;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;


public class OwnerController {

    private HashMap<Condo, ArrayList<CondoManager>> condosToManagers = new HashMap<>();
    private CondoOwner condoOwner;

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
    
    private void setCondoOwner(CondoOwner condoOwner){
        this.condoOwner = condoOwner;
    }

    @FXML
    private void createCondo(){
        // Check if all required fields are filled in
        if (nameCondo.getText().equals("") || address.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in both the name and address fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            Condo condo = new Condo();
            condo.setAddress(address.getText());
            condo.setName(nameCondo.getText());
            condo.setId(address.getText());

            this.condoOwner.addCondo(condo);

            listCondos.getItems().add(condo);
            condosToManagers.put(condo, new ArrayList<CondoManager>());
        }
    }

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
                CondoManager manager = new CondoManager();
                String uid = new FirebaseAuthHandler().createUserAcc(email.getText(), password.getText());
                manager.setId(uid);
                manager.setName(nameManager.getText());
                manager.setLevel(1);
                ArrayList<Condo> condoList = new ArrayList<>();
                condoList.add(selected);
                manager.setCondos(condoList);

                this.condoOwner.addManager(manager, selected);

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
}


