package model;

import java.util.List;

public interface IDatabase {
    public void createUser(User user);
    public void deleteUser(String uid);
    public boolean hasUser(String uid);
    public User getUser(String uid);

    public void createCondo(Condo condo);
    public void deleteCondo(String cid);
    public boolean hasCondo(String cid);
    public Condo getCondo(String cid);

    public List<Condo> getCondosForUser(String uid);
    public void addCondoToUser(String uid, String cid);
    public void removeCondoFromUser(String uid, String cid);

    public void addManagerToCondo(String uid, String cid);
    public void removeManagerFromCondo(String uid, String cid);
    public List<CondoManager> getManagersForCondo(String cid);

    public void addResidentToCondo(String uid, String cid);
    public void removeResidentFromCondo(String uid, String cid);
    public List<Resident> getResidentsForCondo(String cid);

    public void addEventToCondo(Event event, String cid);
    public void removeEventFromCondo(String eid, String cid);
    public List<Event> getEventsForCondo(String cid);

    public void addAmenityToCondo(Amenity amenity, String cid);
    public void removeAmenityFromCondo(String aid, String cid);
    public List<Amenity> getAmenitiesForCondo(String cid);

    public void addAnnouncementToCondo(Announcement announcement, String cid);
    public void removeAnnouncementFromCondo(String aid, String cid);
    public List<Announcement> getAnnouncementsForCondo(String cid);

    public void sendNotificationToResidentsOfCondo(Announcement announcement, String cid);
}
