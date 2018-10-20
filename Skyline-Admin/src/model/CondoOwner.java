package model;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CondoOwner {

  private List<Condo> condos;
  private DatabaseInteractor databaseInteractor;
  private String owner;

  public CondoOwner(DatabaseInteractor databaseInteractor, String name) throws InterruptedException, ExecutionException {
    this.databaseInteractor = databaseInteractor;
    this.condos = new ArrayList<>();
    this.owner = name;
    this.getCondos();
  }

  private void deleteManager(String name) throws InterruptedException, ExecutionException {
    this.databaseInteractor.deleteManager(name);
  }

  private void makeManager(String name, String condo) throws InterruptedException, ExecutionException{
    this.databaseInteractor.makeManager(name, condo);
  }

  private void deleteCondo(String id) throws InterruptedException, ExecutionException {
    this.databaseInteractor.deleteCondo(id);
  }

  private void makeCondo(String name, String address) throws InterruptedException, ExecutionException {
    this.databaseInteractor.makeCondo(name, address, this.owner);
  }

  private void getCondos() throws InterruptedException, ExecutionException {
    this.condos = databaseInteractor.getCondos(this.owner);
  }
}