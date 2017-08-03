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

  public int getId(){ return this.id; }
  public boolean getApprovazione(){ return this.approvazione; }
  public String getTesto(){ return this.testo; }

  public void setId(int id){ this.id = id; }
  public void setApprovazione(boolean approvazione){ this.approvazione = approvazione; }
  public void setTesto(String testo){ this.testo = testo; }
}
