package model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

public class Condo {
    private String id;
    private String name;
    private String address;
    private String ownerId;

    private List<Event> events;
    private List<Amenity> amenities;
    private List<Announcement> announcements;
    private List<String> managerIDs;
    private List<String> residentIDs;

    /* no args constructor required for serialize/deserialize */
    public Condo() {
        this.events = new ArrayList<>();
        this.amenities = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.managerIDs = new ArrayList<>();
        this.residentIDs = new ArrayList<>();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Exclude
    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public void addToEventsList(Event event){
        this.events.add(event);
    }

    public void removeFromEventsList(Event event){
        this.events.remove(event);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Exclude
    public List<Amenity> getAmenities() {
        return new ArrayList<>(amenities);
    }

    public void addToAmenitiesList(Amenity amenity){
        this.amenities.add(amenity);
    }

    public void removeFromAmenitiesList(Amenity amenity){
        this.amenities.remove(amenity);
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    @Exclude
    public List<Announcement> getAnnouncements() {
        return new ArrayList<>(announcements);
    }

    public void addToAnnouncementsList(Announcement announcement){
        this.announcements.add(announcement);
    }

    public void removeFromAnnouncementsList(Announcement announcement){
        this.announcements.remove(announcement);
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Exclude
    public List<String> getManagerIDs() {
        return new ArrayList<>(managerIDs);
    }

    public void addToManagerIDsList(String id){
        this.managerIDs.add(id);
    }

    public void removeFromManagerIDsList(String id){
        this.managerIDs.remove(id);
    }
    public void setManagerIDs(List<String> managerIDs) {
        this.managerIDs = managerIDs;
    }

    @Exclude
    public List<String> getResidentIDs() {
        return new ArrayList<>(residentIDs);
    }

    public void addToResidentIDsList(String id){
        this.residentIDs.add(id);
    }

    public void removeFromResidentIDsList(String id){
        this.residentIDs.remove(id);
    }

    public void setResidentIDs(List<String> residentIDs) {
        this.residentIDs = residentIDs;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Condo && this.getId().equals(((Condo) obj).getId());
    }
}
