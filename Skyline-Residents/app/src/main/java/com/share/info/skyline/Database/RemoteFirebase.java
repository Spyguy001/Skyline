package com.share.info.skyline.Database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RemoteFirebase implements RemoteDatabase {

    private static final String ANNOUCEMENTS = "Announcements";
    private static final String EVENTS = "Events";
    private static final String AMENITIES = "Amenities";

    private static RemoteFirebase remoteFirebase = null;
    private DocumentReference condoDocumentReference;

    public static RemoteFirebase getInstance() {

        if (remoteFirebase == null) {
            remoteFirebase = new RemoteFirebase();
        }

        return remoteFirebase;
    }

    public void init(DocumentReference condoDocumentReference) {
        this.condoDocumentReference = condoDocumentReference;
    }


    @Override
    public void fetchEvents(final DatabaseCallback databaseCallback) {

        condoDocumentReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            condoDocumentReference.collection(EVENTS)
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot documentSnapshots) {

                                            if(!documentSnapshots.isEmpty()) {

                                                List<Event> events = new ArrayList<>();
                                                for (DocumentSnapshot eventSnapshot : documentSnapshots) {
                                                    events.add(eventSnapshot.toObject(Event.class));
                                                }
                                                databaseCallback.eventsCallback(events);
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });

                            Log.d("SUCCESS", "Document retrieved successfully");

                        } else {
                            Log.d("ERROR", "could not find document");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ERROR", e.toString());
                    }
                });
    }


    @Override
    public void fetchAnnouncements(final DatabaseCallback databaseCallback) {

        condoDocumentReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            condoDocumentReference.collection(ANNOUCEMENTS)
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot documentSnapshots) {

                                            if(!documentSnapshots.isEmpty()) {

                                                List<Announcement> announcements = new ArrayList<>();
                                                for (DocumentSnapshot announcementSnapshot : documentSnapshots) {
                                                    announcements.add(announcementSnapshot.toObject(Announcement.class));
                                                }
                                                databaseCallback.announcementsCallback(announcements);
                                            }

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });

                            Log.d("SUCCESS", "Document retrieved successfully");

                        } else {
                            Log.d("ERROR", "could not find document");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ERROR", e.toString());
                    }
                });
    }

    @Override
    public void fetchAmenities(final DatabaseCallback databaseCallback) {

        condoDocumentReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            condoDocumentReference.collection(AMENITIES)
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot documentSnapshots) {

                                            if(!documentSnapshots.isEmpty()) {

                                                List<Amenity> amenities = new ArrayList<>();
                                                for (DocumentSnapshot amenitySnapshot : documentSnapshots) {
                                                    amenities.add(amenitySnapshot.toObject(Amenity.class));
                                                }
                                                databaseCallback.amenitiesCallback(amenities);
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });


                            Log.d("SUCCESS", "Document retrieved successfully");

                        } else {
                            Log.d("ERROR", "could not find document");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ERROR", e.toString());
                    }
                });
    }
}
