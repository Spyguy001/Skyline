package model;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DatabaseInteractor {
    private Firestore db;

    public DatabaseInteractor() throws IOException{
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(credentials)
            .setProjectId("skyline-admin-219803")
            .build();
        FirebaseApp.initializeApp(options);

        this.db = FirestoreClient.getFirestore();
    }

    public Event getEvent(){return null;}

    public Amenity getAmenity(){return null;}

    public Announcement getAnnouncement(){return null;}

    public void makeResident(String name, String username, String room) throws InterruptedException, ExecutionException{
        DocumentReference docRef = db.collection("residents").document(name);
        // Add document data with an additional field ("middle")
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("username", username);
        data.put("room", room);

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void deleteResident(String name) throws InterruptedException, ExecutionException {
        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection("residents").document(name).delete();
        // ...
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }

    public List<Condo> getCondos(String owner) throws InterruptedException, ExecutionException{
        List<Condo> condos = new ArrayList<>();
        ApiFuture<QuerySnapshot> query = db.collection("condos").get();

        // ...
        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.get("owner").equals(owner)) {
                condos.add(new Condo(document.getString("name"), document.getString("address")));
                System.out.println("Condo: " + document.getId());
                System.out.println("Name: " + document.getString("name"));
                if (document.contains("owner")) {
                    System.out.println("Owner: " + document.getString("owner"));
                }
                System.out.println("Address: " + document.getString("address"));
            }
        }

        return condos;
    }

    public void makeCondo(String name, String address, String owner) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("condos").document(address);
        // Add document data with an additional field ("middle")
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("owner", owner);
        data.put("address", address);

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void deleteCondo(String id) throws InterruptedException, ExecutionException {
        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection("condos").document(id).delete();
        // ...
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }

    public void makeManager(String name, String condo) throws InterruptedException, ExecutionException{
        DocumentReference docRef = db.collection("condos").document(condo);
        // Add document data with an additional field ("middle")
        Map<String, Object> data = new HashMap<>();
        data.put("manager", name);
        ApiFuture<WriteResult> result = docRef.set(data);

        docRef = db.collection("managers").document(name);
        // Add document data with an additional field ("middle")
        data = new HashMap<>();
        data.put("name", name);
        result = docRef.set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void deleteManager(String name) throws InterruptedException, ExecutionException {
        // asynchronously delete a manager
        ApiFuture<WriteResult> writeResult = db.collection("managers").document(name).delete();
        // ...
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }
}
