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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.share.info.skyline.Database.DataFetchCallback;
import com.share.info.skyline.Database.LocalDatabase;
import com.share.info.skyline.Database.LocalFirebase;
import com.share.info.skyline.Database.RemoteFirebase;
import com.share.info.skyline.Model.CondoController;
import com.share.info.skyline.Model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InitilizationActivity extends AppCompatActivity implements DataFetchCallback {

    private static final String USERS = "Users";
    private static final String CONDOS = "Condos";
    private FirebaseFirestore firebaseFirestore;
    private List<DocumentReference> condoDocumentReferenceList;

    private ProgressDialog retrieveProgressDialog;
    private ProgressDialog initializeProgressDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();

        condoDocumentReferenceList = new ArrayList<>();

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
                .collection(CONDOS)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()) {

                            for (DocumentSnapshot condoRefSnapshot : queryDocumentSnapshots) {
                                condoDocumentReferenceList.add((DocumentReference) condoRefSnapshot.get("docRef"));
                            }
                            // end progress bar
                            retrieveProgressDialog.dismiss();
                            // start progress bar
                            initializeProgressDialogue.setMessage("Initializing your Condo ...");
                            initializeProgressDialogue.show();
                            initilizeCondoController();
                        }
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

        LocalDatabase localFirebase = new LocalFirebase();

        RemoteFirebase remoteDatabase = RemoteFirebase.getInstance();
        // TODO: allow for multiple condos to be viewed in the future
        remoteDatabase.init(condoDocumentReferenceList.get(0));

        condoController.init(remoteDatabase, localFirebase);
        condoController.fetch(this);
    }

    @Override
    public void onDataFetch() {
        // end progress bar
        initializeProgressDialogue.dismiss();
        // switch to home page
        finish();
        startActivity( new Intent(this, HomePageActivity.class));
    }

}
