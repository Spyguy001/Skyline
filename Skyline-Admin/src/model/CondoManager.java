package model;

public class CondoManager extends User {
    private IDatabase database;

    public CondoManager() {
        this.setLevel(1);
    }

    public void setDatabase(IDatabase database) {
        this.database = database;
    }

    public void addResidentToCondo(Resident resident, Condo condo) {

    }

    public void removeResidentFromCondo(Resident resident, Condo condo) {

    }

    public void addEventToCondo(Event event, Condo condo) {
        condo.addToEventsList(event);
        this.database.addEventToCondo(event, condo.getId());
    }

    public void removeEventFromCondo(Event event, Condo condo) {
        condo.removeFromEventsList(event);
        this.database.removeEventFromCondo(event.getId(), condo.getId());
    }

    public void addAmenityToCondo(Amenity amenity, Condo condo) {
        condo.addToAmenitiesList(amenity);
        this.database.addAmenityToCondo(amenity, condo.getId());
    }

    public void removeAmenityFromCondo(Amenity amenity, Condo condo) {
        condo.removeFromAmenitiesList(amenity);
        this.database.removeAmenityFromCondo(amenity.getId(), condo.getId());
    }

    public void addAnnouncementToCondo(Announcement announcement, Condo condo) {
        condo.removeFromAnnouncementsList(announcement);
        this.database.addAnnouncementToCondo(announcement, condo.getId());
    }

    public void removeAnnouncementFromCondo(Announcement announcement, Condo condo) {
        condo.removeFromAnnouncementsList(announcement);
        this.database.removeAnnouncementFromCondo(announcement.getId(), condo.getId());
    }
}
