
package model;


import java.util.concurrent.ExecutionException;


public class AmenitiesManager {
	
	private DatabaseInteractor databaseinteractor;
	private String condo;
	
	  public void AmenityManager(DatabaseInteractor databaseInteractor,String condo)  {
		  this.condo = condo;
		  this.databaseinteractor = databaseInteractor;
	  }
	  
	  public void createAmenity(String name,String starttime, String endtime, boolean avalability)
			  throws InterruptedException, ExecutionException{
		  this.databaseinteractor.createAmenity(condo, name, starttime, endtime, avalability);
	  }
	  
	  public void deleteAmenity( String name) throws InterruptedException, ExecutionException{
		  this.databaseinteractor.deleteAmenity(condo,name);
	  }
	  
	  public void updateStarttime(String name, String starttime
			  ) throws InterruptedException, ExecutionException{
		  this.databaseinteractor.updateStarttime(condo,name, starttime);
			
	  }
	  
	  public void updateEndttime(String name, String Endtime
			  ) throws InterruptedException, ExecutionException{
		  this.databaseinteractor.updateEndttime(condo,name, Endtime);
	  }
	  
	  public void updateAvalability(String name, boolean status
			  ) throws InterruptedException, ExecutionException{
		  this.databaseinteractor.updateAvalability(condo,name, status);
			
	  }
	  
	  public void getAmenities() throws InterruptedException, ExecutionException {
		  this.databaseinteractor.getAmenities(condo);
	  }
}

