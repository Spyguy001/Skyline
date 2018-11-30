package model;


import java.util.ArrayList;
import java.util.List;

public class CondoOwner extends User{

    private IDatabase database;

    /**
     * Make a new condo owner without any data - constructor is empty for firebase serialization/deserialization
     */
    public CondoOwner(){
        this.setCondos(new ArrayList<>());
    }

    /**
     * Remove a manager from a condo
     * @param manager the manager to be removed
     * @param condo the condo to be removed from
     */
    public void removeManager(CondoManager manager, Condo condo) {
        condo.removeFromManagersList(manager);
        this.database.removeManagerFromCondo(manager.getId(), condo.getId());
        this.database.removeCondoFromUser(manager.getId(), condo.getId());
        this.database.deleteUser(manager.getId());
        new FirebaseAuthHandler().deleteUserAcc(manager.getId());
    }

    /**
     * Add a manager to a condo
     * @param manager the new manager
     * @param condo the condo to be added to
     */
    public void addManager(CondoManager manager, Condo condo) {
        this.database.createUser(manager);
        condo.addToManagersList(manager);
        this.database.addManagerToCondo(manager.getId(), condo.getId());
        this.database.addCondoToUser(manager.getId(), condo.getId());
    }

    /**
     * Remove a condo from the owner's list of condos
     * @param condo the condo to remove
     */
    public void removeCondo(Condo condo) {
        this.removeFromCondoList(condo);
        this.database.removeCondoFromUser(this.getId(), condo.getId());
        this.database.deleteCondo(condo.getId());
    }

    /**
     * Add a condo to the owner's list of condos
     * @param condo the new condo
     */
    public void addCondo(Condo condo) {
        this.database.createCondo(condo);
        this.addToCondoList(condo);
        this.database.addCondoToUser(this.getId(), condo.getId());
    }

    /**
     * Set the data access object to be used by the owner
     * @param database the data access object
     */
    public void setDatabase(IDatabase database){
        this.database = database;
    }
}