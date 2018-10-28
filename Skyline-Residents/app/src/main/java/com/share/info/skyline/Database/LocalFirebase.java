package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.ArrayList;
import java.util.List;

public class LocalFirebase implements DatabaseController {

    private List<Event> eventList;
    private List<Amenity> amenityList;
    private List<Announcement> announcementsList;

    public LocalFirebase() {
        this.eventList = new ArrayList<>();
        this.amenityList = new ArrayList<>();
        this.announcementsList = new ArrayList<>();
    }

    public LocalFirebase(List<Event> eventList, List<Amenity> amenityList, List<Announcement> announcementsList) {
        this.eventList = eventList;
        this.amenityList = amenityList;
        this.announcementsList = announcementsList;
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
    public void updateAnnouncements() {

    }

    @Override
    public void updateEvents() {

    }

    @Override
    public void updateAmenities() {

    }

    @Override
    public void addEvent(Event event) {
        this.eventList.add(event);
    }

    @Override
    public void addAmenity(Amenity amenity) {
        this.amenityList.add(amenity);
    }

    @Override
    public void addAnnouncement(Announcement announcement) {
        this.announcementsList.add(announcement);
    }
}
