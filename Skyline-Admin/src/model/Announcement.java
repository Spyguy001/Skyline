package model;

import java.util.Date;

public class Announcement {
    private String id;
    private Date date;
    private String title;
    private String description;
    private boolean important;

    public Announcement(){
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Announcement && this.getId().equals(((Announcement) obj).getId());
    }

    @Override
    public String toString() {
        return this.title;
    }
}
