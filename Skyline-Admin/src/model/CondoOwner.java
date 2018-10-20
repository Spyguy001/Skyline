package model;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import model.Condo;

public class CondoOwner {

  private List<model.Condo> condos;
  private Firestore db;
  private String owner;

  public CondoOwner(Firestore db, String name) throws InterruptedException, ExecutionException {
    this.db = db;
    this.condos = new ArrayList<>();
    this.owner = name;
    this.getCondos();
  }

  private void deleteManager(String name) throws InterruptedException, ExecutionException {
    // asynchronously delete a manager
    ApiFuture<WriteResult> writeResult = db.collection("managers").document(name).delete();
    // ...
    System.out.println("Update time : " + writeResult.get().getUpdateTime());
  }

  private void makeManager(String name, String condo) throws InterruptedException, ExecutionException{
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

  private void deleteCondo(String id) throws InterruptedException, ExecutionException {
    // asynchronously delete a document
    ApiFuture<WriteResult> writeResult = db.collection("condos").document(id).delete();
    // ...
    System.out.println("Update time : " + writeResult.get().getUpdateTime());
  }

  private void makeCondo(String name, String address) throws InterruptedException, ExecutionException {
    DocumentReference docRef = db.collection("condos").document(address);
    // Add document data with an additional field ("middle")
    Map<String, Object> data = new HashMap<>();
    data.put("name", name);
    data.put("owner", this.owner);
    data.put("address", address);

    ApiFuture<WriteResult> result = docRef.set(data);
    System.out.println("Update time : " + result.get().getUpdateTime());
  }

  private void getCondos() throws InterruptedException, ExecutionException {
    ApiFuture<QuerySnapshot> query = db.collection("condos").get();

    // ...
    // query.get() blocks on response
    QuerySnapshot querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
    for (QueryDocumentSnapshot document : documents) {
      if (document.get("owner").equals(this.owner)) {
        this.condos.add(new Condo(document.getString("name"), document.getString("address")));
        System.out.println("Condo: " + document.getId());
        System.out.println("Name: " + document.getString("name"));
        if (document.contains("owner")) {
          System.out.println("Owner: " + document.getString("owner"));
        }
        System.out.println("Address: " + document.getString("address"));
      }
    }
  }
}