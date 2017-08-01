package model;

public class Recensione{
  private int id;
  private boolean approvazione;
  private String testo;

  public Recensione(){}
  public Recensione(int id, boolean approvazione, String testo){
    this.id = id;
    this.approvazione = approvazione;
    this.testo = testo;
  }

  // Getters.
  public getId(){ return this.id; }
  public getApprovazione(){ return this.approvazione; }
  public getTesto(){ return this.testo; }

  // Setters.
  public void setId(int id){ this.id = id; }
  public void setApprovazione(boolean approvazione){ this.approvazione = approvazione; }
  public void setTesto(String testo){ this.testo = testo; }
}
