package model;

public class CondoManager extends User {
    private IDatabase database;

    public CondoManager() {
        this.setLevel(1);
    }

    /**
     * @param database the dao
     */
    public void setDatabase(IDatabase database) {
        this.database = database;
    }

    /**
     * Link the resident to the condo and vice versa in the database.
     *
     * @param resident a new resident
     * @param condo the condo the resident joins
     */
    public void addResidentToCondo(Resident resident, Condo condo) {
        this.database.createUser(resident);
        condo.addToResidentsList(resident);
        this.database.addResidentToCondo(resident.getId(), condo.getId());
        this.database.addCondoToUser(resident.getId(), condo.getId());
    }

    /**
     * Unlink the resident from the condo and vice versa in the database.
     *
     * @param resident the resident to remove
     * @param condo the condo the resident is in
     */
    public void removeResidentFromCondo(Resident resident, Condo condo) {
        condo.removeFromResidentsList(resident);
        this.database.removeResidentFromCondo(resident.getId(), condo.getId());
        this.database.removeCondoFromUser(resident.getId(), condo.getId());
        this.database.deleteUser(resident.getId());
        new FirebaseAuthHandler().deleteUserAcc(resident.getId());
    }

    /**
     * Push the event to the database and update condo model
     *
     * @param event the new event to add to condo
     * @param condo the condo the event is being added to
     */
    public void addEventToCondo(Event event, Condo condo) {
        condo.addToEventsList(event);
        this.database.addEventToCondo(event, condo.getId());
    }

    /**
     * Removes the event from the condo model and database
     *
     * @param event the event to remove
     * @param condo the condo to remove event from
     */
    public void removeEventFromCondo(Event event, Condo condo) {
        condo.removeFromEventsList(event);
        this.database.removeEventFromCondo(event.getId(), condo.getId());
    }

    /**
     * Push the Amenity to the database and update condo model
     *
     * @param amenity the new amenity to add
     * @param condo the condo the amenity is being added to
     */
    public void addAmenityToCondo(Amenity amenity, Condo condo) {
        condo.addToAmenitiesList(amenity);
        this.database.addAmenityToCondo(amenity, condo.getId());
    }

    /**
     * Removes the amenity from the condo model and database
     *
     * @param amenity the amenity to remove
     * @param condo the condo to remove amenity from
     */
    public void removeAmenityFromCondo(Amenity amenity, Condo condo) {
        condo.removeFromAmenitiesList(amenity);
        this.database.removeAmenityFromCondo(amenity.getId(), condo.getId());
    }

    /**
     * Push the announcement to the database and update condo model
     *
     * @param announcement the new annoucement to add to condo
     * @param condo the condo the event is being added to
     */
    public void addAnnouncementToCondo(Announcement announcement, Condo condo) {
        condo.removeFromAnnouncementsList(announcement);
        this.database.addAnnouncementToCondo(announcement, condo.getId());
    }

    /**
     * Removes the amenity from the condo model and database
     *
     * @param announcement the announcement to remove
     * @param condo the condo to remove announcement from
     */
    public void removeAnnouncementFromCondo(Announcement announcement, Condo condo) {
        condo.removeFromAnnouncementsList(announcement);
        this.database.removeAnnouncementFromCondo(announcement.getId(), condo.getId());
    }

    /**
     * Asks dao to notify users in database of an annoucement
     * @param announcement the annoucement to send notifications for
     * @param condo the condo to send notifications to
     */
    public void sendNotificationToResidents(Announcement announcement, Condo condo) {
        this.database.sendNotificationToResidentsOfCondo(announcement, condo.getId());
    }
}
