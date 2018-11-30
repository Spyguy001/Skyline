package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public interface DatabaseCallback {
    /**
     * callback to be called when the Events have been fetched. The callback is called
     * passing in the list of Events fetched
     * @param eventList List of Events fetched
     */
    public void eventsCallback(List<Event> eventList);

    /**
     * callback to be called when the Announcements have been fetched. The callback is called
     * passing in the list of Announcements fetched
     * @param announcementList List of Announcements fetched
     */
    public void announcementsCallback(List<Announcement> announcementList);

    /**
     * callback to be called when the Amenities have been fetched. The callback is called
     * passing in the list of Amenities fetched
     * @param amenityList List of Amenities fetched
     */
    public void amenitiesCallback(List<Amenity> amenityList);
}
