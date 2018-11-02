
package model;


import java.util.concurrent.ExecutionException;


public class EventManager {
	  private DatabaseInteractor databaseinteractor;
	  private String condo;

	  public void EventsManager(DatabaseInteractor databaseInteractor,String condo)  {
		  this.condo = condo;
		  this.databaseinteractor = databaseInteractor;
	  }
	  
	  public void createEvent(String title, String description, String time) throws InterruptedException, 
	  ExecutionException{
		  	this.databaseinteractor.createEvent(condo, title, description, time);
		  
	  }
	  
	  public void deleteEvent(String title) throws InterruptedException, ExecutionException{
		   this.databaseinteractor.deleteEvent(condo,title);
	  }
	
	  
	  public void getEvents() throws InterruptedException, ExecutionException {
		  this.databaseinteractor.getEvents(condo);
	  }
}

