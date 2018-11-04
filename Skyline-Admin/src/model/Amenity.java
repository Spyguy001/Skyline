package model;

public class Amenity {
    private String name;
    private String details;

    public Amenity(){
        //need empty constructor for firebase
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
