package model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

/** An abstract class used by resident, owner, manager models */
public abstract class User {
    /** the id of the user */
    private String id;
    /** the name of the user */
    private String name;
    /**
     * The privilege level of the user.
     * Subclasses must set this to the appropriate number
     */
    private Integer level;
    /** list of condos the owner belongs to */
    private List<Condo> condos;

    /**
     * @return the id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the privilege level of the user
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the privilege level of the user
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return the list of condos the user belongs to
     */
    @Exclude
    public List<Condo> getCondos() {
        return new ArrayList<>(condos);
    }

    /**
     * @param condo the condo to add to user
     */
    public void addToCondoList(Condo condo){
        this.condos.add(condo);
    }

    /**
     * @param condo the condo to remove from user
     */
    public void removeFromCondoList(Condo condo){
        this.condos.remove(condo);
    }

    /**
     * @param condos the list of condos to set to user
     */
    public void setCondos(List<Condo> condos) {
        this.condos = condos;
    }

    /**
     * @param obj
     * @return true if obj is another user with the same id as this
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.getId().equals(((User) obj).getId());
    }

    /**
     * @return the name of the user
     */
    @Override
    public String toString() {
        return this.name;
    }
}
