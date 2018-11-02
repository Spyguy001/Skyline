package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.beans.value.ChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Controller {

    private int condoID = 0; // For testing purposes
    HashMap<Number, ArrayList<String>> condosToManagers = new HashMap<Number, ArrayList<String>>();

    @FXML
    private ListView<String> listCondos;

    @FXML
    private ListView<String> listManagers;

    @FXML
    private Button buttonCreateCondo;

    @FXML
    private void createCondo(){
        listCondos.getItems().add(String.valueOf(condoID));
        condosToManagers.put(condoID, new ArrayList<String>());
        condoID++;
    }

    @FXML
    private void addManager(){
        int selectedID = Integer.parseInt(listCondos.getSelectionModel().getSelectedItem());

        if (condosToManagers.get(selectedID) == null) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("Manager");
            condosToManagers.put(selectedID, list);
            listManagers.getItems().add("Manager");
        }else{
            condosToManagers.get(selectedID).add("Manager");
            listManagers.getItems().add("Manager");
        }
    }
    @FXML
    private void initialize(){
        listCondos.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                listManagers.getItems().clear();
                listManagers.getItems().addAll(condosToManagers.get(newValue)); }
        });
    }
}


