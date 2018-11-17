package com.share.info.skyline.Model;

import com.share.info.skyline.Database.DataFetchCallback;
import com.share.info.skyline.Database.DatabaseCallback;
import com.share.info.skyline.Database.LocalDatabase;
import com.share.info.skyline.Database.RemoteDatabase;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CondoController implements DatabaseCallback {

    private LocalDatabase localDatabase;
    private RemoteDatabase remoteDatabse;

    private DataFetchCallback currentDataCallBack = null;
    private AtomicInteger dataLeftToFetch = null;

    private static CondoController condoController = null;

    public static CondoController getInstance() {

        if (condoController == null) {
            condoController = new CondoController();
        }

        return condoController;

    }

    public void init(RemoteDatabase remoteDatabase, LocalDatabase localDatabase) {
        this.localDatabase = localDatabase;
        this.remoteDatabse = remoteDatabase;
    }

    public void fetch(DataFetchCallback dataFetchCallback) {
        dataLeftToFetch = new AtomicInteger(3);
        currentDataCallBack = dataFetchCallback;

        fetchAmenities();
        fetchAnnoucements();
        fetchEvents();
    }

    private void fetchEvents() {
//        this.condoEvents = this.localDatabase.fetchEvents();
        this.remoteDatabse.fetchEvents(this);
    }

    private void fetchAnnoucements() {
//        this.condoAnnouncements = this.localDatabase.fetchAnnouncements();
        this.remoteDatabse.fetchAnnouncements(this);
    }

    private void fetchAmenities() {
//        this.condoAmenities = this.localDatabase.fetchAmenities();
        this.remoteDatabse.fetchAmenities(this);
    }

    public List<Amenity> getCondoAmenities() {
        return this.localDatabase.fetchAmenities();
    }

    public List<Announcement> getCondoAnnouncements() {
        return this.localDatabase.fetchAnnouncements();
    }

    public List<Event> getCondoEvent() {
        return this.localDatabase.fetchEvents();
    }


    @Override
    public void eventsCallback(List<Event> eventList) {
        this.localDatabase.updateEvents(eventList);
        dataFetched();
    }

    @Override
    public void announcementsCallback(List<Announcement> announcementList) {
        this.localDatabase.updateAnnouncements(announcementList);
        dataFetched();
    }

    @Override
    public void amenitiesCallback(List<Amenity> amenityList) {
        this.localDatabase.updateAmenities(amenityList);
        dataFetched();
    }

    private void dataFetched() {
        if (currentDataCallBack != null && dataLeftToFetch.decrementAndGet() == 0) {
            currentDataCallBack.onDataFetch();
        }
    }

    public void addEvent(Event event) {
        this.remoteDatabse.addEvent(event);
    }
}
