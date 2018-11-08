package model;

public class CondoManager extends User {
    private IDatabase database;

    public CondoManager(IDatabase database) {
        this.database = database;
        this.setLevel(1);
    }

    public void addResidentToCondo(Resident resident, Condo condo) {}

    public void removeResidentFromCondo(Resident resident, Condo condo) {}

    public void addEventToCondo(Event event, Condo condo) {}

    public void removeEventFromCondo(Event event, Condo condo) {}

    public void addAmenityToCondo(Amenity amenity, Condo condo) {}

    public void removeAmenityFromCondo(Amenity amenity, Condo condo) {}

    public void addAnnouncementToCondo(Announcement announcement, Condo condo) {}

    public void removeAnnouncementFromCondo(Announcement announcement, Condo condo) {}
}
