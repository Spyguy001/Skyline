package Database;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DatabaseHandler implements IDatabase {

    private Firestore db;

    private final String USERS = "Users";
    private final String MANAGERS = "Managers";
    private final String RESIDENTS = "Residents";
    private final String EVENTS = "Events";
    private final String AMENITIES = "Amenities";
    private final String ANNOUNCEMENTS = "Announcements";
    private final String CONDOS = "Condos";

    public DatabaseHandler() throws IOException {
        FileInputStream serviceFile = new FileInputStream("ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceFile))
            .build();

        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();
    }

    private DocumentReference getCondoDocRef(String condoId) {
        ApiFuture<DocumentSnapshot> future = this.db.collection(CONDOS)
            .document(condoId)
            .get();

        DocumentReference document;
        try {
            document = future.get().getReference();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        return document;
    }

    /***
     *
     * @param uid the id of the user whos condos to get
     * @return List of Condos belonging to User uid
     */
    @Override
    public List<Condo> getCondosForUser(String uid) {

        List<Condo> condoList = new ArrayList<>();

        // get a list of all the condo documents the user has
        ApiFuture<QuerySnapshot> query = db.collection(USERS)
            .document(uid)
            .collection(CONDOS)
            .get();

        // for each condo doc, add the associated condo Object
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                // get the document in which the condo info is stored
                DocumentReference documentReference = (DocumentReference) document.get("docRef");
                ApiFuture<DocumentSnapshot> future = this.db.document(documentReference.getPath()).get();
                DocumentSnapshot documentSnapshot = future.get();

                // convert the data to Condo and store in condoList
                condoList.add(documentSnapshot.toObject(Condo.class));

            }
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

        return condoList;
    }

    /**
     *
     * @param uid The id of the Resident to whom the condo will be added
     * @param cid The id of the Condo to be added to the User
     */
    @Override
    public void addCondoToUser(String uid, String cid) {
        HashMap<String, DocumentReference> condoObj = new HashMap<>();
        condoObj.put("docRef", getCondoDocRef(cid));

        ApiFuture<WriteResult> writeResult = this.db.collection(USERS)
            .document(uid)
            .collection(CONDOS)
            .document(cid)
            .set(condoObj, SetOptions.merge());

        writeResult.isDone();
    }

    /**
     *
     * @param uid  The id of the Resident from whom the condo will be deleted
     * @param cid  The id of the Condo to be deleted to the User
     */
    @Override
    public void removeCondoFromUser(String uid, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(USERS)
            .document(uid)
            .collection(CONDOS)
            .document(cid)
            .delete();

        writeResult.isDone();
    }

    private DocumentReference getUserDocRef(String uid) {
        ApiFuture<DocumentSnapshot> future = this.db.collection(USERS)
            .document(uid)
            .get();

        DocumentReference document;
        try {
            document = future.get().getReference();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        return document;
    }

    /**
     *
     * @param uid The id of the Manager to whom the condo will be added
     * @param cid The id of the Condo to be added to the User
     */
    @Override
    public void addManagerToCondo(String uid, String cid) {

        HashMap<String, DocumentReference> managerObj = new HashMap<>();
        managerObj.put("docRef", getUserDocRef(uid));

        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(MANAGERS)
            .document(uid)
            .set(managerObj, SetOptions.merge());

        writeResult.isDone();
    }

    /**
     *
     * @param uid The id of the Manager from whom the condo will be deleted
     * @param cid The id of the Condo to be deleted to the User
     */
    @Override
    public void removeManagerFromCondo(String uid, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(MANAGERS)
            .document(uid)
            .delete();

        writeResult.isDone();
    }

    /**
     *
     * @param cid The id of the Condo of which we want the managers
     * @return The List of Managers belonging to the Condo
     */
    @Override
    public List<CondoManager> getManagersForCondo(String cid) {
        List<CondoManager> managerList  = new ArrayList<>();

        // get a list of all the "manager" documents the condo has
        ApiFuture<QuerySnapshot> query = db.collection(CONDOS)
            .document(cid)
            .collection(MANAGERS)
            .get();

        // for each resident document, add to the list the asscoiated Resident Object
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                // get the document in which the condo info is stored
                DocumentReference documentReference = (DocumentReference) document.get("docRef");
                ApiFuture<DocumentSnapshot> future = this.db.document(documentReference.getPath()).get();
                DocumentSnapshot documentSnapshot = future.get();

                // convert the data to Condo and store in condoList
                managerList.add(documentSnapshot.toObject(CondoManager.class));

            }
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

        return managerList;
    }


    /**
     *
     * @param uid Id of resident to add to Condo
     * @param cid Id of Condo to which resident will be added
     */
    @Override
    public void addResidentToCondo(String uid, String cid) {

        HashMap<String, DocumentReference> residentObj = new HashMap<>();
        residentObj.put("docRef", getUserDocRef(uid));

        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(RESIDENTS)
            .document(uid)
            .set(residentObj, SetOptions.merge());

        writeResult.isDone();
    }

    /**
     *
     * @param uid Id of resident to remove from condo
     * @param cid Id of COndo from which resident is to be deleted
     */
    @Override
    public void removeResidentFromCondo(String uid, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(RESIDENTS)
            .document(uid)
            .delete();

        writeResult.isDone();
    }

    /**
     *
     * @param cid If od Condo of which we want list of residents
     * @return List of Residents belonging to the condo
     */
    @Override
    public List<Resident> getResidentsForCondo(String cid) {
        List<Resident> residentList = new ArrayList<>();

        // get a list of all the "resdient" documents the condo has
        ApiFuture<QuerySnapshot> query = db.collection(CONDOS)
            .document(cid)
            .collection(RESIDENTS)
            .get();

        // for each resident document, add to the list the asscoiated Resident Object
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                // get the document in which the resident info is stored
                DocumentReference documentReference = (DocumentReference) document.get("docRef");
                ApiFuture<DocumentSnapshot> future = this.db.document(documentReference.getPath()).get();
                DocumentSnapshot documentSnapshot = future.get();

                // convert the data to Resident and store inlist
                residentList.add(documentSnapshot.toObject(Resident.class));

            }
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

        return residentList;
    }

    /**
     *
     * @param event Event to be addded to the condo
     * @param cid Id of condo to which we want to add the event
     */
    @Override
    public void addEventToCondo(Event event, String cid) {

        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(EVENTS)
            .document(event.getTitle())
            .set(event, SetOptions.merge());

        writeResult.isDone();
    }

    /**
     *
     * @param eid Id of event to remove from Condo
     * @param cid Id of Condo from which the event is going to be removed
     */
    @Override
    public void removeEventFromCondo(String eid, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(EVENTS)
            .document(eid)
            .delete();

        writeResult.isDone();
    }

    /**
     *
     * @param cid Id of Condo of which we want Events
     * @return List of Events belonging to the Condo
     */
    @Override
    public List<Event> getEventsForCondo(String cid) {
        List<Event> eventList = new ArrayList<>();

        // get a list of all the "events" documents the condo has
        ApiFuture<QuerySnapshot> query = db.collection(CONDOS)
            .document(cid)
            .collection(EVENTS)
            .get();

        // for each event document, add to the list the asscoiated event Object
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                // convert the data to Event and store in list
                eventList.add(document.toObject(Event.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

        return eventList;
    }

    /**
     *
     * @param amenity Amenity to be added the condo
     * @param cid Id of the condo to add the amenity to
     */
    @Override
    public void addAmenityToCondo(Amenity amenity, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(AMENITIES)
            .document(amenity.getName())
            .set(amenity, SetOptions.merge());

        writeResult.isDone();
    }

    /**
     *
     * @param aid Id of the amenity to remove
     * @param cid Id of the Condo to add remove the Amenity from
     */
    @Override
    public void removeAmenityFromCondo(String aid, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(AMENITIES)
            .document(aid)
            .delete();

        writeResult.isDone();
    }

    /**
     *
     * @param cid Id of the condo to get events from
     * @return List of Events belonging to the Condo
     */
    @Override
    public List<Amenity> getAmenitiesForCondo(String cid) {
        List<Amenity> amenityList = new ArrayList<>();

        // get a list of all the "amenity" documents the condo has
        ApiFuture<QuerySnapshot> query = db.collection(CONDOS)
            .document(cid)
            .collection(AMENITIES)
            .get();

        // for each event document, add to the list the associated amenity Object
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                // convert the data to Amenity object and store in list
                amenityList.add(document.toObject(Amenity.class));

            }
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

        return amenityList;
    }

    /**
     *
     * @param announcement Announcement to add to Condo
     * @param cid Id of Condo to add the announcement to
     */
    @Override
    public void addAnnouncementToCondo(Announcement announcement, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(ANNOUNCEMENTS)
            .document(announcement.getTitle())
            .set(announcement, SetOptions.merge());

        writeResult.isDone();
    }

    /**
     *
     * @param aid Id of annoucement to remove
     * @param cid Id of Condo from which to remove annoucement
     */
    @Override
    public void removeAnnouncementFromCondo(String aid, String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .collection(ANNOUNCEMENTS)
            .document(aid)
            .delete();

        writeResult.isDone();
    }

    /**
     *
     * @param cid Id of Condo to get announcements from
     * @return List of annoucements belonging to the condo
     */
    @Override
    public List<Announcement> getAnnouncementsForCondo(String cid) {
        List<Announcement> announcementList = new ArrayList<>();

        // get a list of all the "announcement" documents the condo has
        ApiFuture<QuerySnapshot> query = db.collection(CONDOS)
            .document(cid)
            .collection(ANNOUNCEMENTS)
            .get();

        // for each event document, add to the list the associated Announcement object
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                // convert the data to Annoucement and store in list
                announcementList.add(document.toObject(Announcement.class));

            }
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

        return announcementList;
    }

    /**
     *
     * @param user User to add to the dabase
     */
    @Override
    public void createUser(User user) {
        ApiFuture<WriteResult> writeResult = this.db.collection(USERS)
            .document(user.getId())
            .set(user);

        writeResult.isDone();
    }

    /**
     *
     * @param uid Id of user to delete from database
     */
    @Override
    public void deleteUser(String uid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(USERS)
            .document(uid)
            .delete();

        writeResult.isDone();
    }

    /**
     *
     * @param uid Id of the User interesetd in
     * @return True if the User is present in the database. False otherwise
     */
    @Override
    public boolean hasUser(String uid) {
        ApiFuture<DocumentSnapshot> future = this.db.collection(USERS)
            .document(uid)
            .get();

        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return document.exists();
    }

    /**
     *
     * @param uid Id of the user to get from the databse
     * @return User with id uid from the database
     */
    @Override
    public User getUser(String uid) {
        ApiFuture<DocumentSnapshot> future = this.db.collection(USERS)
            .document(uid)
            .get();
        DocumentSnapshot document;
        try {
            document = future.get();
            return document.toObject(User.class);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param condo Condo to add to the databse
     */
    @Override
    public void createCondo(Condo condo) {

        Map<String, Object> condoObjToStore = new HashMap<>();
        condoObjToStore.put("id", condo.getId());
        condoObjToStore.put("name", condo.getName());
        condoObjToStore.put("address", condo.getAddress());
        condoObjToStore.put("ownerId", condo.getOwnerId());

        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(condo.getId())
            .set(condoObjToStore, SetOptions.merge());

        writeResult.isDone();
    }

    /**
     *
     * @param cid Id of Condo to remove from the databse
     */
    @Override
    public void deleteCondo(String cid) {
        ApiFuture<WriteResult> writeResult = this.db.collection(CONDOS)
            .document(cid)
            .delete();

        writeResult.isDone();
    }

    /**
     *
     * @param cid Id of Condo interested in
     * @return Return true if Condo is in databse, false otherwise
     */
    @Override
    public boolean hasCondo(String cid) {
        ApiFuture<DocumentSnapshot> future = this.db.collection(CONDOS)
            .document(cid)
            .get();

        DocumentSnapshot document;
        try {
            document = future.get();
            return document.exists();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     *
     * @param cid Id of Condo to get from database
     * @return Condo with id cid from database
     */
    @Override
    public Condo getCondo(String cid) {
        ApiFuture<DocumentSnapshot> future = this.db.collection(CONDOS)
            .document(cid)
            .get();
        DocumentSnapshot document;
        try {
            document = future.get();
            return document.toObject(Condo.class);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}