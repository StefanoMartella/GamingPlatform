package src.model;

/**
*Class which represents a user
*/
public class Utente{
  private int id;
  private String nome;
  private String cognome;
  private String username;
  private String email;
  private String password;
  private String tipo;
  private int livello;
  private int puntiExp;

  /**
  *Basic constructor
  */
  public Utente(){}
  
  /**
  *Constructor
  *
  *@param username user's username
  *@param password user's password
  */
  public Utente(String username, String password){
    this.username = username;
    this.password = password;
  }
  
  /**
  *Secoond constructor
  *
  *@param nome user's name
  *@param cognome user's surname
  *@param username user's username
  *@param email user's email
  *@param password user's password
  */
  public Utente(String nome, String cognome, String username, String email, String password){
    this.nome = nome;
    this.cognome = cognome;
    this.username = username;
    this.email = email;
    this.password = password;  
  }
  
  /**
  *Full constructor
  *
  *@param id user's ID
  *@param nome user's name
  *@param cognome user's surname
  *@param username user's username
  *@param email user's email
  *@param password user's password
  *@param tipo user's type
  *@param livello user's level
  *@param puntiExp user's experience points
  */
  public Utente(int id, String nome, String cognome, String username, String email, String password, String tipo, int livello, int puntiExp){
    // Calling this() causes problems with interactions with db.
    this.id = id;
    this.nome = nome;
    this.cognome = cognome;
    this.username = username;
    this.email = email;
    this.password = password; 
    this.tipo = tipo;
    this.livello = livello;
    this.puntiExp = puntiExp;
  }
  
  /**
  *Third constructor
  *
  *@param id user's ID
  *@param nome user's name
  *@param cognome user's surname
  *@param username user's username
  *@param email user's email
  *@param password user's password
  *@param tipo user's type
  *@param puntiExp user's experience points
  */
  public Utente(int id, String nome, String cognome, String username, String email, String password, String tipo, int puntiExp){
    this.id = id;
    this.nome = nome;
    this.cognome = cognome;
    this.username = username;
    this.email = email;
    this.password = password; 
    this.tipo = tipo;
    this.puntiExp = puntiExp;
    if( puntiExp >= 500 )
        this.livello = 5;
    else
        this.livello = puntiExp / 100;
  }
  
  
  /**
  *Method used to get user's ID
  *
  *@return int number of ID
  */
  public int getId(){ return this.id; }
  
  /**
  *Method used to get user's name
  *
  *@return String name of user
  */
  public String getNome(){ return this.nome; }
  
  /**
  *Method used to get user's surname
  *
  *@return String surname of user
  */
  public String getCognome(){ return this.cognome; }
  
  /**
  *Method used to get user's username
  *
  *@return String username of user
  */
  public String getUsername(){ return this.username; }
  
  /**
  *Method used to get user's email
  *
  *@return String email of user
  */
  public String getEmail(){ return this.email; }
  
  /**
  *Method used to get user's password
  *
  *@return String password of user
  */
  public String getPassword(){ return this.password; }
  
  /**
  *Method used to get user's type
  *
  *@return String type of user
  */
  public String getTipo(){ return this.tipo; }
  
  /**
  *Method used to get user's level
  *
  *@return int level of user
  */
  public int getLivello(){ return this.livello; }
  
  /**
  *Method used to get user's experience points
  *
  *@return int amount of exp
  */
  public int getPuntiExp(){ return this.puntiExp; }
  
  /**
  *Method used to set user's ID
  *
  *@param id new user's ID
  */
  public void setId(int id){ this.id = id; }
  
  /**
  *Method used to set user's name
  *
  *@param nome new user's name
  */
  public void setNome(String nome){ this.nome = nome; }
  
  /**
  *Method used to set user's surname
  *
  *@param cognome new user's surname
  */
  public void setCognome(String cognome){ this.cognome = cognome; }
  
  /**
  *Method used to set user's username
  *
  *@param username new user's username
  */
  public void setUsername(String username){ this.username = username; }
  
  /**
  *Method used to set user's email
  *
  *@param email new user's email
  */
  public void setEmail(String email){ this.email = email; }
  
  /**
  *Method used to set user's password
  *
  *@param password new user's password
  */
  public void setPassword(String password){ this.password = password; }
  
  /**
  *Method used to set user's type
  *
  *@param tipo new user's type
  */
  public void setTipo(String tipo){ this.tipo = tipo; }
  
  /**
  *Method used to set user's level
  *
  *@param livello new user's level
  */
  public void setLivello(int livello){ this.livello = livello; }
  
  /**
  *Method used to set user's experience points
  *
  *@param puntiExp new user's exp amount
  */
  public void setPuntiExp(int puntiExp){ this.puntiExp = puntiExp; }  
  
  /**
  *Method used to get user's info
  *
  *@return String user's name, surname and username
  */
  @Override
  public String toString(){
    return this.getNome() + " " + this.getCognome() + " ," + this.getUsername();
  }
  
  /**
  *Method used to compare users
  *
  *@param obj user to compare with
  *@return boolean true if this user is equal to the given one, false otherwise
  */
  @Override
  public boolean equals(Object obj){
    Utente utente;
    if( obj instanceof Utente )
        utente = (Utente) obj;
    else
	return false;
	
    if( this.username.equals(utente.getUsername()) || this.email.equals(utente.getEmail()) )
	return true;
    return false; 
  }
}
