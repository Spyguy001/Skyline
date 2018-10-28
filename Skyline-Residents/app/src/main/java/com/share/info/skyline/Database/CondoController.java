package com.share.info.skyline.Database;

import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.List;

public class CondoController {

    private DatabaseController databaseController;
    private String condoName;
    private List<Amenity> condoAmenities;
    private List<Announcement> condoAnnouncements;
    private List<Event> condoEvents;

    private static CondoController condoController = null;

    public static CondoController getInstance() {

        if (condoController == null) {
            condoController = new CondoController();
        }

        return condoController;

    }

    public void init(DatabaseController databaseController, String condoName) {
        this.databaseController = databaseController;
        this.condoName = condoName;
        fetch();
    }

    private void fetch() {
        fetchAmenities();
        fetchAnnoucement();
        fetchEvents();
    }

    private void fetchEvents() {
        this.condoEvents = this.databaseController.fetchEvents();
    }

    private void fetchAnnoucement() {
        this.condoAnnouncements = this.databaseController.fetchAnnouncements();
    }

    private void fetchAmenities() {
        this.condoAmenities = this.databaseController.fetchAmenities();
    }

    public List<Amenity> getCondoAmenities() {
        return this.condoAmenities;
    }

    public List<Announcement> getCondoAnnouncements() {
        return this.condoAnnouncements;
    }

    public List<Event> getCondoEvent() {
        return this.condoEvents;
    }


}
