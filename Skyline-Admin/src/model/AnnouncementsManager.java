
package model;


import java.util.concurrent.ExecutionException;


public class AnnouncementsManager {
	
	  private DatabaseInteractor databaseinteractor;
	  private String condo;

	  public AnnouncementsManager(DatabaseInteractor databaseInteractor,String condo){
		  this.condo = condo;
		  this.databaseinteractor = databaseInteractor;
		        
	  }
	  
	  public void createAnnouncement(String title, String description, String time ) 
			  throws InterruptedException, ExecutionException{
		  this.databaseinteractor.createAnnouncement(condo,title, description, time);
	  }
		  
	  public void deleteAnnouncement(String title) throws InterruptedException, ExecutionException{
		  this.databaseinteractor.deleteAnnouncement(condo,title);
	  }
	 
	  public void getAnnouncements() throws InterruptedException, ExecutionException {
		  this.databaseinteractor.getAnnouncements(condo);
		  
	  }
}

