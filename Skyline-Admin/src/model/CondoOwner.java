package model;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CondoOwner {

  private List<Condo> condos;
  private DatabaseInteractor db;
  private String owner;

  public CondoOwner(DatabaseInteractor db, String name) throws InterruptedException, ExecutionException {
    this.db = db;
    this.condos = new ArrayList<>();
    this.owner = name;
    this.getCondos();
  }

  private void deleteManager(String name) throws InterruptedException, ExecutionException {
    this.db.deleteManager(name);
  }

  private void makeManager(String name, String condo) throws InterruptedException, ExecutionException{
    this.db.makeManager(name, condo);
  }

  private void deleteCondo(String id) throws InterruptedException, ExecutionException {
    this.db.deleteCondo(id);
  }

  private void makeCondo(String name, String address) throws InterruptedException, ExecutionException {
    this.db.makeCondo(name, address, this.owner);
  }

  private void getCondos() throws InterruptedException, ExecutionException {
    this.condos = db.getCondos(this.owner);
  }
}