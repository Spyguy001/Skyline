package com.share.info.skyline.Database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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
    private static final String USERS = "Users";

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

                                            List<Event> events = new ArrayList<>();

                                            if (!documentSnapshots.isEmpty()) {
                                                for (DocumentSnapshot eventSnapshot : documentSnapshots) {
                                                    events.add(eventSnapshot.toObject(Event.class));
                                                }
                                            }

                                            databaseCallback.eventsCallback(events);

                                            Log.d("SUCCESS", "Events Document retrieved successfully");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });

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

                                            List<Announcement> announcements = new ArrayList<>();

                                            if (!documentSnapshots.isEmpty()) {
                                                for (DocumentSnapshot announcementSnapshot : documentSnapshots) {
                                                    announcements.add(announcementSnapshot.toObject(Announcement.class));
                                                }
                                            }

                                            databaseCallback.announcementsCallback(announcements);

                                            Log.d("SUCCESS", "Announcements Document retrieved successfully");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });

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

                                            List<Amenity> amenities = new ArrayList<>();

                                            if (!documentSnapshots.isEmpty()) {
                                                for (DocumentSnapshot amenitySnapshot : documentSnapshots) {
                                                    amenities.add(amenitySnapshot.toObject(Amenity.class));
                                                }
                                            }

                                            databaseCallback.amenitiesCallback(amenities);

                                            Log.d("SUCCESS", "Amenities Document retrieved successfully");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });

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
    public void addEvent(Event event) {
        condoDocumentReference
                .collection(EVENTS)
                .document(event.getId())
                .set(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    @Override
    public void bookAmenity() {

    }

    @Override
    public void updateToken(String token) {

        FirebaseFirestore.getInstance().collection(USERS)
                .document(FirebaseAuth.getInstance().getUid())
                .update("token", token)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("SUCCESS", "updated user token successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("UPDATE_TOKEN", e.toString());
                    }
                });
    }
}
