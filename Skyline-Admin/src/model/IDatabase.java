package model;

import java.util.List;

public interface IDatabase {

    public List<Condo> getCondosForUser(String uid);

    public void addCondoToOwner(String uid, Condo condo);
    public void removeCondoFromOwner(String uid, Condo condo);

    public boolean hasUser(String uid);

    public void addManagerToCondo(CondoManager manager, Condo condo);
    public void removeManagerFromCondo(CondoManager manager, Condo condo);
    public void addCondoToManager(CondoManager manager, Condo condo);
    public void removeCondoFromManager(CondoManager manager, Condo condo);

    public void addResidentToCondo(Resident resident, Condo condo);
    public void removeResidentFromCondo(Resident resident, Condo condo);
    public void addCondoToResident(Resident resident, Condo condo);
    public void removeCondoFromResident(Resident resident, Condo condo);

    public void addEventToCondo(Event event, Condo condo);
    public void removeEventFromCondo(Event event, Condo condo);
    public List<Event> getEventsForCondo(Condo condo);

    public void addAmenityToCondo(Amenity amenity, Condo condo);
    public void removeAmenityFromCondo(Amenity amenity, Condo condo);
    public List<Amenity> getAmenitiesForCondo(Condo condo);

    public void addAnnouncementToCondo(Announcement announcement, Condo condo);
    public void removeAnnouncementFromCondo(Announcement announcement, Condo condo);
    public List<Announcement> getAnnouncementsForCondo(Condo condo);
}
