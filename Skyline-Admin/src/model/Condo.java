package model;

import java.util.List;

public class Condo {
    private String id;
    private String name;
    private String address;

    private List<Event> events;
    private List<Amenity> amenities;
    private List<Announcement> announcements;
    private List<String> managerIDs;

    /* no args constructor required for serialize/deserialize */
    public Condo() {}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<String> getManagerIDs() {
        return managerIDs;
    }

    public void setManagerIDs(List<String> managerIDs) {
        this.managerIDs = managerIDs;
    }
}
