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
    private List<CondoManager> managers;
    private List<Resident> residents;

    /* no args constructor required for serialize/deserialize */
    public Condo() {
        this.events = new ArrayList<>();
        this.amenities = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.residents = new ArrayList<>();
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
    public List<CondoManager> getManagers() {
        return new ArrayList<>(managers);
    }

    public void addToManagersList(CondoManager manager){
        this.managers.add(manager);
    }

    public void removeFromManagersList(CondoManager manager){
        this.managers.remove(manager);
    }

    public void setManagers(List<CondoManager> managers) {
        this.managers = managers;
    }

    @Exclude
    public List<Resident> getResidents() {
        return new ArrayList<>(residents);
    }

    public void addToResidentsList(Resident resident){
        this.residents.add(resident);
    }

    public void removeFromResidentsList(Resident resident){
        this.residents.remove(resident);
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Condo && this.getId().equals(((Condo) obj).getId());
    }

    @Override
    public String toString() {
        return name + " @ " + address;
    }
}
