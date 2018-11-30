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

    /**
     * Makes a Condo with no data - constructor is empty for firebase serialization/deserialization
     */
    public Condo() {
        this.events = new ArrayList<>();
        this.amenities = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.residents = new ArrayList<>();
    }

    /**
     * Returns the condo id
     * @return the condo id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the condo id
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the condo name
     * @return the condo name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the condo name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the condo address
     * @return the condo address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the condo address
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the condo owner's id
     * @return the condo owner's id
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the condo owner's id
     * @param ownerId the owner's id
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Returns the list of events happening in the condo
     * @return the list of events happening in the condo
     */
    @Exclude
    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    /**
     * Adds to the list of events for the condo
     * @param event the new event
     */
    public void addToEventsList(Event event){
        this.events.add(event);
    }

    /**
     * Removes an event from the condo's list of events
     * @param event the event to remove
     */
    public void removeFromEventsList(Event event){
        this.events.remove(event);
    }

    /**
     * Sets the list of events for the condo
     * @param events the list of events for the condo
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Returns the list of amenities in the condo
     * @return the list of amenities in the condo
     */
    @Exclude
    public List<Amenity> getAmenities() {
        return new ArrayList<>(amenities);
    }

    /**
     * Adds to the list of amenities for the condo
     * @param amenity the new amenity
     */
    public void addToAmenitiesList(Amenity amenity){
        this.amenities.add(amenity);
    }

    /**
     * Removes an amenity from the condo's list of amenities
     * @param amenity the amenity to remove
     */
    public void removeFromAmenitiesList(Amenity amenity){
        this.amenities.remove(amenity);
    }

    /**
     * Sets the list of amenities for the condo
     * @param amenities the list of amenities for the condo
     */
    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    /**
     * Returns the list of announcements made for this condo
     * @return the list of announcements made for this condo
     */
    @Exclude
    public List<Announcement> getAnnouncements() {
        return new ArrayList<>(announcements);
    }

    /**
     * Adds an announcement to the list of the condo's announcements
     * @param announcement the new announcement
     */
    public void addToAnnouncementsList(Announcement announcement){
        this.announcements.add(announcement);
    }

    /**
     * Removes an announcement from the list of the condo's announcements
     * @param announcement the announcement to remove
     */
    public void removeFromAnnouncementsList(Announcement announcement){
        this.announcements.remove(announcement);
    }

    /**
     * Sets the list of announcements for the condo
     * @param announcements the list of announcements
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * Returns a list of the manager accounts for this condo
     * @return a list of the manager accounts for this condo
     */
    @Exclude
    public List<CondoManager> getManagers() {
        return new ArrayList<>(managers);
    }

    /**
     * Adds a manager to this condo
     * @param manager the new manager
     */
    public void addToManagersList(CondoManager manager){
        this.managers.add(manager);
    }

    /**
     * Removes a manager from the condo
     * @param manager the manager to remove
     */
    public void removeFromManagersList(CondoManager manager){
        this.managers.remove(manager);
    }

    /**
     * Sets the list of managers for this condo
     * @param managers the list of managers
     */
    public void setManagers(List<CondoManager> managers) {
        this.managers = managers;
    }

    /**
     * Returns the list of residents for the condo
     * @return the list of residents for the condo
     */
    @Exclude
    public List<Resident> getResidents() {
        return new ArrayList<>(residents);
    }

    /**
     * Adds a new resident to the condo
     * @param resident the new resident
     */
    public void addToResidentsList(Resident resident){
        this.residents.add(resident);
    }

    /**
     * Removes a resident from the condo
     * @param resident the resident to remove
     */
    public void removeFromResidentsList(Resident resident){
        this.residents.remove(resident);
    }

    /**
     * Sets the list of residents in the condo
     * @param residents the list of residents
     */
    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    /**
     * Returns whether one condo is the same as another
     * @param obj the condo to be compared to
     * @return whether the condos are the same
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Condo && this.getId().equals(((Condo) obj).getId());
    }

    /**
     * Returns the condo represented as the "condo name @ condo address"
     * @return the condo represented as the "condo name @ condo address"
     */
    @Override
    public String toString() {
        return name + " @ " + address;
    }
}
