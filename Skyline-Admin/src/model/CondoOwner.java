package model;


import java.util.ArrayList;
import java.util.List;

public class CondoOwner extends User{

    private IDatabase database;

    public CondoOwner(){
        //empty constructor for firebase
    }

    public CondoOwner(IDatabase database){
        this.database = database;

    }

    public void removeManager(CondoManager manager, Condo condo) {
        condo.getManagerIDs().remove(manager.getId());
        this.database.removeManagerFromCondo(manager.getId(), condo.getId());
        this.database.removeCondoFromUser(manager.getId(), condo.getId());
    }

    public void addManager(CondoManager manager, Condo condo) {
        this.database.createUser(manager);
        condo.getManagerIDs().add(manager.getId());
        this.database.addManagerToCondo(manager.getId(), condo.getId());
        this.database.addCondoToUser(manager.getId(), condo.getId());
    }

    public void removeCondo(Condo condo) {
        this.getCondos().remove(condo);
        this.database.removeCondoFromUser(this.getId(), condo.getId());
        this.database.deleteCondo(condo.getId());
    }

    public void addCondo(Condo condo) {
        this.database.createCondo(condo);
        this.getCondos().add(condo);
        this.database.addCondoToUser(this.getId(), condo.getId());
    }

    public void setDatabase(IDatabase database){
        this.database = database;
    }
}