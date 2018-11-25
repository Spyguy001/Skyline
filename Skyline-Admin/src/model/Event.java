package model;

import java.util.Date;

public class Event {
    private String id;
    private Date date;
    private String title;
    private String location;
    private String description;

    public Event(){
        //need empty constructor for firebase
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Event && this.getId().equals(((Event) obj).getId());
    }

    @Override
    public String toString() {
        return this.title;
    }
}
