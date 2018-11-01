package com.share.info.skyline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.share.info.skyline.Database.LocalFirebase;
import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.CondoController;
import com.share.info.skyline.Model.Event;

import java.util.concurrent.CountDownLatch;

public class InitilizationActivity extends AppCompatActivity {

    private static final String ANNOUCEMENTS = "Announcements";
    private static final String EVENTS = "Events";
    private static final String AMENITIES = "Amenities";
    private static final String USERS = "Users";
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference condoDocumentReference;

    private ProgressDialog retrieveProgressDialog;
    private ProgressDialog initializeProgressDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();

        retrieveProgressDialog = new ProgressDialog(this);
        initializeProgressDialogue = new ProgressDialog(this);

        retrieveAndInitializeCondo(getIntent().getExtras().getString("uid"));
    }

    private void retrieveAndInitializeCondo(String userId) {

        // start progress bar
        retrieveProgressDialog.setMessage("Retrieving your Condo ...");
        retrieveProgressDialog.show();

        firebaseFirestore.collection(USERS)
                .document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        // retrieve condo
                        condoDocumentReference = documentSnapshot.getDocumentReference("condo");

                        // end progress bar
                        retrieveProgressDialog.dismiss();
                        // start progress bar
                        initializeProgressDialogue.setMessage("Initializing your Condo ...");
                        initializeProgressDialogue.show();
                        initilizeCondoController();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w("ERROR", e.toString());
                    }
                });

    }

    private void initilizeCondoController() {

        CondoController condoController = CondoController.getInstance();
        final LocalFirebase localFirebase = new LocalFirebase();
        condoController.init(localFirebase, "XYZ");

        // semaphore to ensure all data is fetched before switching to home
        final CountDownLatch done = new CountDownLatch(3);

        // get Events get Announcements get Amenities

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

                                                for (DocumentSnapshot amenitySnapshot : documentSnapshots) {
                                                    localFirebase.addAmenity(amenitySnapshot.toObject(Amenity.class));
                                                }
                                            }
                                            dataFetched(done);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });

                            condoDocumentReference.collection(ANNOUCEMENTS)
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot documentSnapshots) {

                                            if(!documentSnapshots.isEmpty()) {

                                                for (DocumentSnapshot announcementSnapshot : documentSnapshots) {
                                                    localFirebase.addAnnouncement(announcementSnapshot.toObject(Announcement.class));
                                                }
                                            }
                                            dataFetched(done);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("ERROR", e.toString());
                                        }
                                    });

                            condoDocumentReference.collection(EVENTS)
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot documentSnapshots) {

                                            if(!documentSnapshots.isEmpty()) {

                                                for (DocumentSnapshot eventSnapshot : documentSnapshots) {
                                                    localFirebase.addEvent(eventSnapshot.toObject(Event.class));
                                                }
                                            }
                                            dataFetched(done);
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

    private void dataFetched(CountDownLatch partial) {
        partial.countDown();
        if (partial.getCount() == 0) {
            // end progress bar
            initializeProgressDialogue.dismiss();

            // switch to home page
            finish();
            startActivity( new Intent(this, HomePageActivity.class));
        }
    }
}
