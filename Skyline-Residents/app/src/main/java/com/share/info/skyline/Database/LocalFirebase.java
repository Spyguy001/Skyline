package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.ArrayList;
import java.util.List;

public class LocalFirebase implements LocalDatabase {

    private List<Event> eventList;
    private List<Amenity> amenityList;
    private List<Announcement> announcementsList;

    public LocalFirebase() {
        this.eventList = new ArrayList<>();
        this.amenityList = new ArrayList<>();
        this.announcementsList = new ArrayList<>();
    }

    @Override
    public List<Announcement> fetchAnnouncements() {
        return this.announcementsList;
    }

    @Override
    public List<Event> fetchEvents() {
        return this.eventList;
    }

    @Override
    public List<Amenity> fetchAmenities() {
        return this.amenityList;
    }

    @Override
    public void updateEvents(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public void updateAmenities(List<Amenity> amenityList) {
        this.amenityList = amenityList;
    }

    @Override
    public void updateAnnouncements(List<Announcement> announcementList) {
        this.announcementsList = announcementList;
    }
}
