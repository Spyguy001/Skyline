package controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.beans.value.ChangeListener;
import java.util.ArrayList;
import java.util.HashMap;


public class OwnerController {

    HashMap<String, ArrayList<String>> condosToManagers = new HashMap<>();

    @FXML
    private ListView<String> listCondos;

    @FXML
    private ListView<String> listManagers;

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

    @FXML
    private void createCondo(){
        // Check if all required fields are filled in
        if (nameCondo.getText().equals("") || address.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in both the name and address fields!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            listCondos.getItems().add(nameCondo.getText());
            condosToManagers.put(nameCondo.getText(), new ArrayList<String>());
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
            String selected = listCondos.getSelectionModel().getSelectedItem();
            // Check if there is a condo selected
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a Condo!", ButtonType.CANCEL);
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {
                if (condosToManagers.get(selected) == null) {
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(nameManager.getText());
                    condosToManagers.put(selected, list);
                    listManagers.getItems().add(nameManager.getText());
                } else {
                    condosToManagers.get(selected).add(nameManager.getText());
                    listManagers.getItems().add(nameManager.getText());
                }
            }
        }
    }

    @FXML
    private void deleteManager(){
        String selected = listManagers.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a manager to delete!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + listManagers.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                listManagers.getItems().remove(selected);
                condosToManagers.get(listCondos.getSelectionModel().getSelectedItem()).remove(selected);
            }
        }
    }

    @FXML
    private void deleteCondo(){
        String selected = listCondos.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a condo to delete!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + listCondos.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                listCondos.getItems().remove(selected);
            }
        }
    }

    @FXML
    private void initialize(){

        listCondos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                listManagers.getItems().clear();
                listManagers.getItems().addAll(condosToManagers.get(newValue)); }
        });
    }
}


