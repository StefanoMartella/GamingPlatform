package model;

public class Recensione{
  private int id;
  private boolean approvazione;
  private String testo;
  private int idGioco;
  private int idUtente;

  public Recensione(){}
  public Recensione(String testo, int idGioco, int idUtente){
    this.testo = testo;
    this.idGioco = idGioco;
    this.idUtente = idUtente;  
  }
  public Recensione(int id, boolean approvazione, String testo, int idGioco, int idUtente){
    // Calling this() causes problems with interactions with db.
    this.id = id;
    this.approvazione = approvazione;
    this.testo = testo;
    this.idGioco = idGioco;
    this.idUtente = idUtente;
  }

  public int getId(){ return this.id; }
  public boolean getApprovazione(){ return this.approvazione; }
  public String getTesto(){ return this.testo; }
  public int getIdGioco(){ return this.idGioco; }
  public int getIdUtente(){ return this.idUtente;}

  public void setId(int id){ this.id = id; }
  public void setApprovazione(boolean approvazione){ this.approvazione = approvazione; }
  public void setTesto(String testo){ this.testo = testo; }
  public void setIdGioco(int idGioco){ this.idGioco = idGioco; }
  public void setIdUtente(int idUtente){ this.idUtente = idUtente; }
  
  @Override
  public String toString(){
    return this.getId() + " - " + this.getTesto();
  }
}
