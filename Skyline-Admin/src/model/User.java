package model;

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

    public List<Condo> getCondos() {
        return condos;
    }

    public void setCondos(List<Condo> condos) {
        this.condos = condos;
    }
}
