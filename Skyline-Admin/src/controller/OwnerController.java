package controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import javafx.beans.value.ChangeListener;
import java.util.ArrayList;
import java.util.HashMap;


public class OwnerController {

    private int condoID = 0; // For testing purposes
    HashMap<String, ArrayList<String>> condosToManagers = new HashMap<>();

    @FXML
    private ListView<String> listCondos;

    @FXML
    private ListView<String> listManagers;

    @FXML
    private Button buttonCreateCondo;

    @FXML
    private void createCondo(){
        listCondos.getItems().add("Condo" + condoID);
        condosToManagers.put("Condo" + condoID, new ArrayList<String>());
        condoID++;
    }

    @FXML
    private void addManager(){
        String selected = listCondos.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a Condo!", ButtonType.CANCEL);
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            if (condosToManagers.get(selected) == null) {
                ArrayList<String> list = new ArrayList<String>();
                list.add("Manager");
                condosToManagers.put(selected, list);
                listManagers.getItems().add("Manager");
            } else {
                condosToManagers.get(selected).add("Manager");
                listManagers.getItems().add("Manager");
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


