package model;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
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

    public void makeManager(String uid, String name, String condo) 
        throws InterruptedException, ExecutionException{
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
    
    public void createAmenity(String condo ,
    	String name, String starttime, String endtime, boolean avalability)
		throws InterruptedException, ExecutionException{
	    DocumentReference doccondo = db.collection("Condos").document(condo);
	    CollectionReference amenities = doccondo.collection("Amnenites");
	    DocumentReference amenity = amenities.document(name);
	        
	    Map<String, Object> data = new HashMap<>();
	    data.put("name", name);
	    data.put("starttime", starttime);
	    data.put("endtime", endtime);
	    data.put("avalability", avalability);
	        
	    ApiFuture<WriteResult> result = amenity.set(data);
	    System.out.println("Update time : " + result.get().getUpdateTime());
	  }
    
    public void deleteAmenity(String condo,String name) throws InterruptedException, 
    	ExecutionException{
		ApiFuture<WriteResult> writeResult = db.collection("Condos").document(condo)
		.collection("Amenities").document(name).delete();

		
		System.out.println("Update time : " + writeResult.get().getUpdateTime());
	  }
    
    public void updateStarttime(String condo,String name, String starttime
			  ) throws InterruptedException, ExecutionException{
    	DocumentReference doccondo = db.collection("Condos").document(condo);
	    CollectionReference amenities = doccondo.collection("Amnenites");
		ApiFuture<WriteResult> resulttime = amenities.document("name").update("starttime", starttime);
		  
		WriteResult result = resulttime.get();
		System.out.println("Write result: " + result);
			
	  }
    
	  public void updateEndttime(String condo,String name, String endtime
	      ) throws InterruptedException, ExecutionException{
	    	DocumentReference doccondo = db.collection("Condos").document(condo);
		    CollectionReference amenities = doccondo.collection("Amnenites");
			ApiFuture<WriteResult> resulttime = amenities.document("name").update("endtime", endtime);
		  
		  WriteResult result = resulttime.get();
		  System.out.println("Write result: " + result);
			
	  }
	  
	  public void updateAvalability(String condo,String name, boolean status
		  ) throws InterruptedException, ExecutionException{
		  DocumentReference doccondo = db.collection("Condos").document(condo);
		    CollectionReference amenities = doccondo.collection("Amnenites");
			ApiFuture<WriteResult> resultstat = amenities.document("name").update("avalability", status);
		  
		  WriteResult result = resultstat.get();
		  System.out.println("Write result: " + result);
			
	  }
	  
	  public void getAmenities(String condo) throws InterruptedException, ExecutionException {
		  ApiFuture<QuerySnapshot> query  = db.collection("Condos").document(condo)
		  .collection("Amenities").get();
		  QuerySnapshot querySnapshot = query.get();
		  List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		  for (QueryDocumentSnapshot document : documents) {
			  System.out.println("Amenity: " + document.getString("name"));
			  System.out.println("Starttime: " + document.getString("starttime"));
			  System.out.println("Endtime: " + document.getString("endtime"));
			  System.out.println("Avalability: " + document.getBoolean("avalability"));
		}
	  }
	  
	  public void createAnnouncement(String condo, String title, String description, String time ) 
		  throws InterruptedException, ExecutionException{
		  DocumentReference doccondo = db.collection("Condos").document(condo);
		  CollectionReference announcements = doccondo.collection("Announcements");
		  DocumentReference announcement = announcements.document(title);
	
	      Map<String, Object> data = new HashMap<>();
	          data.put("title", title);
	          data.put("description", description);
	          data.put("Time", time);
	        
	      ApiFuture<WriteResult> result = announcement.set(data);
	      System.out.println("Update time : " + result.get().getUpdateTime());
	  }
	  
	  public void deleteAnnouncement(String condo,String title) 
		  throws InterruptedException, ExecutionException{
		  ApiFuture<WriteResult> writeResult = db.collection("Condos").document(condo)
		  .collection("Announcements").document(title).delete();
		  
		  System.out.println("Update time : " + writeResult.get().getUpdateTime());
	  }
	  
	  public void getAnnouncements(String condo) throws InterruptedException, ExecutionException {
		  ApiFuture<QuerySnapshot> query  = db.collection("Condos").document(condo)
		  .collection("Announcements").get();
		  QuerySnapshot querySnapshot = query.get();
		  List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		  for (QueryDocumentSnapshot document : documents) {
			  System.out.println("Title: " + document.getString("title"));
			  System.out.println("Description: " + document.getString("Description"));
			  System.out.println("Tate: " + document.getDate("time"));
		}
		  
	  }
	  
	  public void createEvent(String condo,String title, String description, String time) 
	      throws InterruptedException, ExecutionException{
		  DocumentReference doccondo = db.collection("Condos").document(condo);
		  CollectionReference events = doccondo.collection("Events");
		  DocumentReference event = events.document(title);
			
	      Map<String, Object> data = new HashMap<>();
	      data.put("title", title);
	      data.put("description", description);
	      data.put("time", time);
	        
	      ApiFuture<WriteResult> result = event.set(data);
	      System.out.println("Update time : " + result.get().getUpdateTime());
		 
	  }
	  
	  public void deleteEvent(String condo,String title) 
		  throws InterruptedException, ExecutionException{
		  ApiFuture<WriteResult> writeResult = db.collection("Condos").document(condo)
		  .collection("Events").document(title).delete();
		  
		   System.out.println("Update time : " + writeResult.get().getUpdateTime());
	  }
	  
	  public void getEvents(String condo) throws InterruptedException, ExecutionException {
		  ApiFuture<QuerySnapshot> query  = db.collection("Condos").document(condo)
		  .collection("Events").get();
		  QuerySnapshot querySnapshot = query.get();
		  List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		  for (QueryDocumentSnapshot document : documents) {
			  System.out.println("Title: " + document.getString("title"));
			  System.out.println("Description: " + document.getString("Description"));
			  System.out.println("Time: " + document.getDate("time"));
		  }
	  }
}
