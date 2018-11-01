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
import com.share.info.skyline.Database.DataFetchCallback;
import com.share.info.skyline.Database.LocalDatabase;
import com.share.info.skyline.Database.LocalFirebase;
import com.share.info.skyline.Database.RemoteFirebase;
import com.share.info.skyline.Model.CondoController;

import java.util.concurrent.atomic.AtomicInteger;

public class InitilizationActivity extends AppCompatActivity implements DataFetchCallback {

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

        LocalDatabase localFirebase = new LocalFirebase();

        RemoteFirebase remoteDatabase = RemoteFirebase.getInstance();
        remoteDatabase.init(condoDocumentReference);

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
