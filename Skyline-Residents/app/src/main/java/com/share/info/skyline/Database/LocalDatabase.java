package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public interface LocalDatabase {

    public List<Announcement> fetchAnnouncements();
    public List<Event> fetchEvents();
    public List<Amenity> fetchAmenities();

    public void clearEvents();
    public void clearAnnouncements();
    public void clearAmenities();

    public void addEvent(Event event);
    public void addAmenity(Amenity amenity);
    public void addAnnouncement(Announcement announcement);

    public void updateEvents(List<Event> eventList);
    public void updateAmenities(List<Amenity> amenityList);
    public void updateAnnouncements(List<Announcement> announcementList);
}
