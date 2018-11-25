package model;

public class Amenity {
    private String id;
    private String name;
    private String details;
    private boolean isBookable;
    private int intervalAllowed;

    public Amenity(){
        //need empty constructor for firebase
    }

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isBookable() {
        return isBookable;
    }

    public void setBookable(boolean bookable) {
        isBookable = bookable;
    }

    public int getIntervalAllowed() {
        return intervalAllowed;
    }

    public void setIntervalAllowed(int intervalAllowed) {
        this.intervalAllowed = intervalAllowed;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Amenity && this.getId().equals(((Amenity) obj).getId());
    }

    @Override
    public String toString() {
        return this.name;
    }

}
