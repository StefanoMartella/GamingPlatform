package src.model;

/**
*Class which represents a review
*/
public class Recensione{
  private int id;
  private int approvazione;
  private String testo;
  private int idGioco;
  private int idUtente;
  
  /**
  *Basic constructor
  */
  public Recensione(){}
  
  /**
  *Constructor
  *
  *@param testo review's text
  *@param idGioco game's ID which review is about
  *@param idUtente user's ID who has written the review
  *
  */
  
  public Recensione(String testo, int idGioco, int idUtente){
    this.testo = testo;
    this.idGioco = idGioco;
    this.idUtente = idUtente;  
  }
  
   /**
  *Full constructor
  *
  *@param id review's ID
  *@param approvazione number which specifies if the review is accepted or not
  *@param testo review's text
  *@param idGioco game's ID which review is about
  *@param idUtente user's ID who has written the review
  */
  public Recensione(int id, int approvazione, String testo, int idGioco, int idUtente){
    // Calling this() causes problems with interactions with db.
    this.id = id;
    this.approvazione = approvazione;
    this.testo = testo;
    this.idGioco = idGioco;
    this.idUtente = idUtente;
  }

  /**
  *Method used to get review's ID
  *
  *@return int number of ID
  */
  public int getId(){ return this.id; }
  
  /**
  *Method used to get review's approval
  *
  *@return int id of approval
  */
  public int getApprovazione(){ return this.approvazione; }
  
  /**
  *Method used to get review's text
  *
  *@return String review's text
  */
  public String getTesto(){ return this.testo; }
  
  /**
  *Method used to get review's gameID
  *
  *@return int number of gameID
  */
  public int getIdGioco(){ return this.idGioco; }
  
  /**
  *Method used to get review's userID
  *
  *@return int number of userID
  */
  public int getIdUtente(){ return this.idUtente;}

  /**
  *Method used to set review's ID
  *
  *@param id new review's ID
  */
  public void setId(int id){ this.id = id; }
  
  /**
  *Method used to set review's approval
  *
  *@param approvazione new review's approval
  */
  public void setApprovazione(int approvazione){ this.approvazione = approvazione; }
  
  /**
  *Method used to set review's text
  *
  *@param testo new review's text
  */
  public void setTesto(String testo){ this.testo = testo; }
  
  /**
  *Method used to set review's gameID
  *
  *@param idGioco new review's gameID
  */
  public void setIdGioco(int idGioco){ this.idGioco = idGioco; }
  
  /**
  *Method used to set review's userID
  *
  *@param idUtente new review's userID
  */
  public void setIdUtente(int idUtente){ this.idUtente = idUtente; }
  
  /**
  *Method used to get review's information
  *
  *@return String review's ID and text
  */
  @Override
  public String toString(){
    return this.getId() + " - " + this.getTesto();
  }

  /**
  *Method to compare reviews
  *
  *@param obj review to compare with
  *@return boolean true if this review is equals to the given one, false otherwise
  */
  public boolean equals(Object obj){
    Recensione recensione;
    if( obj instanceof Recensione )
        recensione = (Recensione) obj;
    else
        return false;

    if( this.idGioco == recensione.getIdGioco() && this.idUtente == recensione.getIdUtente() && this.testo.equals(recensione.getTesto()) )
        return true;
    return false;
  }
}
