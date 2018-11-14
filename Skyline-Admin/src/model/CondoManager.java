package model;

public class CondoManager extends User {
    private IDatabase database;

    public CondoManager() {
        this.setLevel(1);
    }

    public void setDatabase(IDatabase database) {
        this.database = database;
    }

    public void addResidentToCondo(Resident resident, Condo condo) {}

    public void removeResidentFromCondo(Resident resident, Condo condo) {}

    public void addEventToCondo(Event event, Condo condo) {
        condo.addToEventsList(event);
        this.database.addEventToCondo(event, condo.getId());
        System.out.println(event.getId());
        System.out.println(condo.getId());
        System.out.println(this.database.getEventsForCondo(condo.getId()).size());
    }

    public void removeEventFromCondo(Event event, Condo condo) {}

    public void addAmenityToCondo(Amenity amenity, Condo condo) {}

    public void removeAmenityFromCondo(Amenity amenity, Condo condo) {}

    public void addAnnouncementToCondo(Announcement announcement, Condo condo) {}

    public void removeAnnouncementFromCondo(Announcement announcement, Condo condo) {}
}
