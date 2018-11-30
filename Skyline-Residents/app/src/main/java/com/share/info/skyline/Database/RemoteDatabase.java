package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public interface RemoteDatabase {

    /**
     * Fetch the Events for the Condo under consideration from the Database
     * @param databaseCallback callback to be called when the Events have been fetched. The callback is called
     *      *                  passing in the list of Events fetched
     */
    public void fetchEvents(DatabaseCallback databaseCallback);

    /**
     * Fetch the Announcements for the Condo under consideration from the Database
     * @param databaseCallback callback to be called when the Announcements have been fetched. The callback is called
     *      *                   passing in the list of Announcements fetched
     */
    public void fetchAnnouncements(DatabaseCallback databaseCallback);

    /**
     * Fetch the Amenities for the Condo under consideration from the Database
     * @param databaseCallback Callback to be called when the amenAmenitiesities have been fetched. The callback is called
     *                         passing in the list of Amenities fetched
     */
    public void fetchAmenities(DatabaseCallback databaseCallback);

    /**
     * Add Event event to the Condo under consideraation in the Database
     * @param event Event to be added
     */
    public void addEvent(Event event);

    /**
     * Change Ddatabase to refelct amenity has been booked
     */
    public void bookAmenity();

    /**
     * Update the token for the current user in the databse
     * @param s the new token
     */
    void updateToken(String s);
}
