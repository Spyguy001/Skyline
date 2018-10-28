package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public interface DatabaseController {

    public List<Announcement> fetchAnnouncements();
    public List<Event> fetchEvents();
    public List<Amenity> fetchAmenities();

    public void updateAnnouncements();
    public void updateEvents();
    public void updateAmenities();

    public void addEvent(Event event);
    public void addAmenity(Amenity amenity);
    public void addAnnouncement(Announcement announcement);
}
