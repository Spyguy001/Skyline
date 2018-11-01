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
import java.util.Date;
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

    public List<Event> getEvents(Condo condo){return null;}

    public List<Amenity> getAmenities(Condo condo){return null;}

    public List<Announcement> getAnnouncements(Condo condo){return null;}

    public void makeResident(String uid, String name, String email, String room) throws InterruptedException,
    	ExecutionException{
        DocumentReference docRef = db.collection("Users").document(uid);
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        data.put("room", room);
        data.put("level", "0");

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public Map<String, String> getUser(String uid) throws InterruptedException, ExecutionException{
			// asynchronously retrieve all users
			ApiFuture<QuerySnapshot> query = db.collection("Users").get();

			// query.get() blocks on response
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				if(document.getId().equals(uid)){
					HashMap<String, String> map = new HashMap<>();
					map.put("name", document.getString("name"));
					map.put("email", document.getString("email"));
					map.put("level", document.getString("level"));
					return map;
				}
			}
			return null;
		}
    

    public void deleteResident(String uid) throws InterruptedException, ExecutionException {
        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection("Users").document(uid).delete();

        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }

    public List<Condo> getCondos(String owner) throws InterruptedException, ExecutionException{
        List<Condo> condos = new ArrayList<>();
        ApiFuture<QuerySnapshot> query = db.collection("Condos").get();

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

    public void makeCondo(String name, String address, String owner) throws InterruptedException,
    	ExecutionException {
        DocumentReference docRef = db.collection("Condos").document(address);

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("owner", owner);
        data.put("address", address);

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void deleteCondo(String id) throws InterruptedException, ExecutionException {
        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection("Condos").document(id).delete();

        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }

    public void makeManager(String uid, String name, String condo) throws InterruptedException, ExecutionException{
        DocumentReference docRef = db.collection("Users").document(uid);

        Map<String, Object> data = new HashMap<>();
        /* data.put("manager", name); */
				data.put("name", name);
				data.put("condo", condo);
				data.put("level", "1");
				ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void deleteManager(String uid) throws InterruptedException, ExecutionException {
        // asynchronously delete a manager
        ApiFuture<WriteResult> writeResult = db.collection("Users").document(uid).delete();

        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }
    
    public void createAmenity(String name, String starttime, String endtime, boolean avalability)
			  throws InterruptedException, ExecutionException{
	        DocumentReference docRef = db.collection("Amenity").document(name);
	        
	        Map<String, Object> data = new HashMap<>();
	        data.put("name", name);
	        data.put("starttime", starttime);
	        data.put("endtime", endtime);
	        data.put("avalability", avalability);
	        
	         ApiFuture<WriteResult> result = docRef.set(data);
	        System.out.println("Update time : " + result.get().getUpdateTime());
	  }
    
    public void deleteAmenity(String name) throws InterruptedException, ExecutionException{
		ApiFuture<WriteResult> writeResult = db.collection("Amenity").document("name").delete();
		
		System.out.println("Update time : " + writeResult.get().getUpdateTime());
	  }
    
    public void updateStarttime(String name, String starttime
			  ) throws InterruptedException, ExecutionException{
		  DocumentReference docRef = db.collection("Amenity").document(name);
		  ApiFuture<WriteResult> resulttime = docRef.update("starttime", starttime);
		  
		  WriteResult result = resulttime.get();
		  System.out.println("Write result: " + result);
			
	  }
    
	  public void updateEndttime(String name, String Endtime
			  ) throws InterruptedException, ExecutionException{
		  DocumentReference docRef = db.collection("Amenity").document(name);
		  ApiFuture<WriteResult> resulttime = docRef.update("Endtime", Endtime );
		  
		  WriteResult result = resulttime.get();
		  System.out.println("Write result: " + result);
			
	  }
	  
	  public void updateAvalability(String name, boolean status
			  ) throws InterruptedException, ExecutionException{
		  DocumentReference docRef = db.collection("Amenity").document(name);
		  ApiFuture<WriteResult> resultstatus = docRef.update("Avalability", status);
		  
		  WriteResult result = resultstatus.get();
		  System.out.println("Write result: " + result);
			
	  }
	  
	  public void getAmenities() throws InterruptedException, ExecutionException {
		  ApiFuture<QuerySnapshot> query = db.collection("Amenity").get();
		  QuerySnapshot querySnapshot = query.get();
		  List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		  for (QueryDocumentSnapshot document : documents) {
			  System.out.println("Amenity: " + document.getString("name"));
			  System.out.println("Starttime: " + document.getString("starttime"));
			  System.out.println("Endtime: " + document.getString("endtime"));
			  System.out.println("Avalability: " + document.getBoolean("avalability"));
		}
	  }
	  
	  public void createAnnouncement(String title, String description, Date date ) 
			  throws InterruptedException, ExecutionException{
		  	DocumentReference docRef = db.collection("Announcement").document(title);
	
	        Map<String, Object> data = new HashMap<>();
	        data.put("title", title);
	        data.put("description", description);
	        data.put("Date", date);
	        
	         ApiFuture<WriteResult> result = docRef.set(data);
	        System.out.println("Update time : " + result.get().getUpdateTime());
	  }
	  
	  public void deleteAnnouncement(String title) throws InterruptedException, ExecutionException{
		  ApiFuture<WriteResult> writeResult = db.collection("Amenity").document(title).delete();
		  
		  System.out.println("Update time : " + writeResult.get().getUpdateTime());
	  }
	  
	  public void getAnnouncements() throws InterruptedException, ExecutionException {
		  ApiFuture<QuerySnapshot> query = db.collection("Announcement").get();
		  QuerySnapshot querySnapshot = query.get();
		  List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		  for (QueryDocumentSnapshot document : documents) {
			  System.out.println("Title: " + document.getString("title"));
			  System.out.println("Description: " + document.getString("Description"));
			  System.out.println("Date: " + document.getDate("Date"));
		}
		  
	  }
	  
	  public void createEvent(String title, String description, Date date) throws InterruptedException, 
	  ExecutionException{
		  	DocumentReference docRef = db.collection("Announcement").document(title);
			
	        Map<String, Object> data = new HashMap<>();
	        data.put("title", title);
	        data.put("description", description);
	        data.put("Date", date);
	        
	        ApiFuture<WriteResult> result = docRef.set(data);
	        System.out.println("Update time : " + result.get().getUpdateTime());
		 
	  }
	  
	  public void deleteEvent(String title) throws InterruptedException, ExecutionException{
		   ApiFuture<WriteResult> writeResult = db.collection("Amenity").document(title).delete();
		  
		   System.out.println("Update time : " + writeResult.get().getUpdateTime());
	  }
	  
	  public void getEvents() throws InterruptedException, ExecutionException {
		  ApiFuture<QuerySnapshot> query = db.collection("Announcement").get();
		  QuerySnapshot querySnapshot = query.get();
		  List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		  for (QueryDocumentSnapshot document : documents) {
			  System.out.println("Title: " + document.getString("title"));
			  System.out.println("Description: " + document.getString("Description"));
			  System.out.println("Date: " + document.getDate("Date"));
		  }
	  }
}
