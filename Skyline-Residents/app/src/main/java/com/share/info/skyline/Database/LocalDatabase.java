package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public interface LocalDatabase {

    /**
     * Return the list of Announcements stored in memory
     * @return List of the Announcements stored in memory
     */
    public List<Announcement> fetchAnnouncements();

    /**
     * Return the list of Events stored in memory
     * @return List of the Events stored in memory
     */
    public List<Event> fetchEvents();

    /**
     * Return the list of Amenities stored in memory
     * @return List of the Amenities stored in memory
     */
    public List<Amenity> fetchAmenities();

    /**
     * Update the list of Events stored in memory
     * @param eventList new list of events to store in memory
     */
    public void updateEvents(List<Event> eventList);

    /**
     * Update the list of Amenities stored in memory
     * @param amenityList  new list of Amenities to store in memory
     */
    public void updateAmenities(List<Amenity> amenityList);

    /**
     * the list of Announcements stored in memory
     * @param announcementList  new list of Announcements to store in memory
     */
    public void updateAnnouncements(List<Announcement> announcementList);
}
