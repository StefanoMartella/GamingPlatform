package src.model;

/**
*Class which represents a game
*/
public class Gioco{
  private int id;
  private String nome;
  private int exp;

  public Gioco(){}
  
  /**
  * Class' constructor
  *
  *@param nome game's name
  *@param exp game's exp
  */
  public Gioco(String nome, int exp){
    this.nome = nome;
    this.exp = exp;  
  }
  
  /**
  * Class' constructor
  *
  *@param id game's id
  *@param nome game's name
  *@param exp game's exp
  */
  public Gioco(int id, String nome, int exp){
    // Calling this() causes problems with interactions with db.
    this.id = id;
    this.nome = nome;
    this.exp = exp;
  }
  
  /**
  * Method used to get the game's ID
  *
  *@return int number of ID
  */
  public int getId(){ return this.id; }
  
  /**
  * Method used to get the game's name
  *
  *@return String name of game
  */
  public String getNome(){ return this.nome; }
  
  /**
  * Method used to get the game's EXP
  *
  *@return int amount of exp
  */
  public int getExp(){ return this.exp; }

  /**
  * Method used to set the game's ID
  *
  *@param id new game's id
  */
  public void setId(int id){ this.id = id; }
  
  /**
  * Method used to set the game's name
  *
  *@param nome new game's name
  */
  public void setNome(String nome){ this.nome = nome; }
  
  /**
  * Method used to set the game's exp
  *
  *@param exp new game's exp
  */
  public void setExp(int exp){ this.exp = exp; }
  
  /**
  * Method used to get the game's information (ID + name)
  *
  *@return String game's ID and name
  */
  @Override
  public String toString(){
    return this.getId() + " - " + this.getNome();
  }

  /**
  *Method to compare games
  *
  *@param obj game to compare with
  *@return boolean true if this game is equals to the given one, false otherwise
  */
  @Override
  public boolean equals(Object obj){
    Gioco gioco;
    if(obj instanceof Gioco)
        gioco = (Gioco) obj;
    else
        return false;

    if(this.nome.equals(gioco.getNome()) && this.exp == gioco.getExp())
        return true;
    return false;
  }
}
