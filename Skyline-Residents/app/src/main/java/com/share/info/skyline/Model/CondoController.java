package com.share.info.skyline.Model;

import com.share.info.skyline.Database.DataFetchCallback;
import com.share.info.skyline.Database.DatabaseCallback;
import com.share.info.skyline.Database.LocalDatabase;
import com.share.info.skyline.Database.RemoteDatabase;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Singleton class to allow for controlling all aspects of the residents side of the Condo
 */
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

    /**
     * Initialize the condo object by injecting a remoteDatabse and localDatabase
     * @param remoteDatabase
     * @param localDatabase
     */
    public void init(RemoteDatabase remoteDatabase, LocalDatabase localDatabase) {
        this.localDatabase = localDatabase;
        this.remoteDatabse = remoteDatabase;
    }

    /**
     * Fetch the Amenities, Announcements and Events from remote Database
     * @param dataFetchCallback callback to be called when data has been fetched
     */
    public void fetch(DataFetchCallback dataFetchCallback) {
        dataLeftToFetch = new AtomicInteger(3);
        currentDataCallBack = dataFetchCallback;

        fetchAmenities();
        fetchAnnoucements();
        fetchEvents();
    }

    private void fetchEvents() {
        this.remoteDatabse.fetchEvents(this);
    }

    private void fetchAnnoucements() {
        this.remoteDatabse.fetchAnnouncements(this);
    }

    private void fetchAmenities() {
        this.remoteDatabse.fetchAmenities(this);
    }

    /**
     * Return a list of Amenities for this COndo
     * @return List of Amenities
     */
    public List<Amenity> getCondoAmenities() {
        return this.localDatabase.fetchAmenities();
    }

    /**
     * Return a list of Announcement for this COndo
     * @return List of Announcement
     */
    public List<Announcement> getCondoAnnouncements() {
        return this.localDatabase.fetchAnnouncements();
    }

    /**
     * Return a list of Event for this COndo
     * @return List of Event
     */
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

    /**
     * Add an event to the condo in the Database
     * @param event Event to be added
     */
    public void addEvent(Event event) {
        this.remoteDatabse.addEvent(event);
    }
}
