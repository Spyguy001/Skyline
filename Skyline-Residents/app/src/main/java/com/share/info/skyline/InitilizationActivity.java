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
import com.share.info.skyline.Database.CondoController;
import com.share.info.skyline.Database.LocalFirebase;
import com.share.info.skyline.Model.Amenity;
import com.share.info.skyline.Model.Announcement;
import com.share.info.skyline.Model.Event;

public class InitilizationActivity extends AppCompatActivity {

    private static final String ANNOUCEMENTS = "announements";
    private static final String EVENTS = "events";
    private static final String AMENITIES = "amenities";
    private static final String USERS = "users";
    private FirebaseFirestore firebaseFirestore;
    private DocumentReference condoDocumentReference;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);

        retrieveAndInitializeCondo(getIntent().getExtras().getString("uid"));
    }

    private void retrieveAndInitializeCondo(String userId) {

        // start progress bar
        progressDialog.setMessage("Retrieving your Condo ...");
        progressDialog.show();

        firebaseFirestore.collection(USERS)
                .document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        // retrieve condo
                        condoDocumentReference = documentSnapshot.getDocumentReference("condo");

                        // end progress bar
                        progressDialog.dismiss();

                        initilizeCondoController();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    private void initilizeCondoController() {

        CondoController condoController = CondoController.getInstance();
        final LocalFirebase localFirebase = new LocalFirebase();

        // start progress bar
        progressDialog.setMessage("Initializing your Condo ...");
        progressDialog.show();

        // get Events get Announcements get Amenities

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

                                                for (DocumentSnapshot announcementSnapshot : documentSnapshots) {
                                                    localFirebase.addAnnouncement(announcementSnapshot.toObject(Announcement.class));
                                                }
                                            }
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
                                        }
                                    });

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
                                        }
                                    });

                            Log.d("SUCCESS", "Document retrieved successfully");

                        } else {


                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w("ERROR", e.toString());

                    }
                });

        // end progress bar
        progressDialog.dismiss();

        // initialize condo
        condoController.init(localFirebase, "XYZ");

        // switch to home page
        finish();
        startActivity( new Intent(this, HomePageActivity.class));

    }
}
