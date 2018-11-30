package model;

import java.util.Date;

public class Event {
    /** id of the event */
    private String id;
    /** date the event takes place on */
    private Date date;
    /** title of the event */
    private String title;
    /** location the event takes place in */
    private String location;
    /** more details about the event */
    private String description;

    //need empty constructor for firebase
    public Event(){}

    /**
     * @return the id of the event
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the ide of the event
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date of the event
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date of the event
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the title of the event
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title of the event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the location of the event
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location where the event takes place
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return more details about the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description more details about the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param obj
     * @return true if obj is an Event and has the same id as this
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Event && this.getId().equals(((Event) obj).getId());
    }

    /**
     * @return the title of the event
     */
    @Override
    public String toString() {
        return this.title;
    }
}
