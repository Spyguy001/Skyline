package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public interface RemoteDatabase {

    public void fetchEvents(DatabaseCallback databaseCallback);
    public void fetchAnnouncements(DatabaseCallback databaseCallback);
    public void fetchAmenities(DatabaseCallback databaseCallback);
}
