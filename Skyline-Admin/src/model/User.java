package model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String id;
    private String name;
    private Integer level;
    private List<Condo> condos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Exclude
    public List<Condo> getCondos() {
        return new ArrayList<>(condos);
    }

    public void addToCondoList(Condo condo){
        this.condos.add(condo);
    }

    public void removeFromCondoList(Condo condo){
        this.condos.remove(condo);
    }

    public void setCondos(List<Condo> condos) {
        this.condos = condos;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.getId().equals(((User) obj).getId());
    }
}
