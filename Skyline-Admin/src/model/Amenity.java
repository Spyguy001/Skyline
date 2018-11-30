package model;

/** The amenity model */
public class Amenity {
    private String id;
    private String name;
    private String details;
    private boolean isBookable;
    private int intervalAllowed;

    // need empty constructor for firebase
    public Amenity(){}

    /**
     * @return the id of the amenity
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name of the amenity
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name of the amenity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return more information about the amenity
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set for amenity
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return whether the amenity is bookable or not
     */
    public boolean isBookable() {
        return isBookable;
    }

    /**
     * @param bookable boolean indicating whether amenity is bookable
     */
    public void setBookable(boolean bookable) {
        isBookable = bookable;
    }

    /**
     * @return the time interval for each amenity booking in hours
     */
    public int getIntervalAllowed() {
        return intervalAllowed;
    }

    /**
     * @param intervalAllowed the interval in hours an amenity is allowed to be booked for
     */
    public void setIntervalAllowed(int intervalAllowed) {
        this.intervalAllowed = intervalAllowed;
    }

    /**
     * @param obj
     * @return true if obj is an amenity with the same id as this object
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Amenity && this.getId().equals(((Amenity) obj).getId());
    }

    /**
     * @return the name of the amenity
     */
    @Override
    public String toString() {
        return this.name;
    }

}
