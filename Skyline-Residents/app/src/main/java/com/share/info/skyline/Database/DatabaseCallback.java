package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public interface DatabaseCallback {
    public void eventsCallback(List<Event> eventList);
    public void announcementsCallback(List<Announcement> announcementList);
    public void amenitiesCallback(List<Amenity> amenityList);
}
