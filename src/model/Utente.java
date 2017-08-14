package src.model;

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

  public Utente(){}
  public Utente(String username, String password){
    this.username = username;
    this.password = password;
  }
  public Utente(String nome, String cognome, String username, String email, String password){
    this.nome = nome;
    this.cognome = cognome;
    this.username = username;
    this.email = email;
    this.password = password;  
  }
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
  public Utente(int id, String nome, String cognome, String username, String email, String password, String tipo, int puntiExp){
    this.id = id;
    this.nome = nome;
    this.cognome = cognome;
    this.username = username;
    this.email = email;
    this.password = password; 
    this.tipo = tipo;
	if(puntiExp >= 500)
		this.livello = 5;
	else
		this.livello = puntiExp/100;
    this.puntiExp = puntiExp;
  }
  

  public int getId(){ return this.id; }
  public String getNome(){ return this.nome; }
  public String getCognome(){ return this.cognome; }
  public String getUsername(){ return this.username; }
  public String getEmail(){ return this.email; }
  public String getPassword(){ return this.password; }
  public String getTipo(){ return this.tipo; }
  public int getLivello(){ return this.livello; }
  public int getPuntiExp(){ return this.puntiExp; }

  public void setId(int id){ this.id = id; }
  public void setNome(String nome){ this.nome = nome; }
  public void setCognome(String cognome){ this.cognome = cognome; }
  public void setUsername(String username){ this.username = username; }
  public void setEmail(String email){ this.email = email; }
  public void setPassword(String password){ this.password = password; }
  public void setTipo(String tipo){ this.tipo = tipo; }
  public void setLivello(int livello){ this.livello = livello; }
  public void setPuntiExp(int puntiExp){ this.puntiExp = puntiExp; }
  
  @Override
  public String toString(){
    return this.getNome() + " " + this.getCognome() + " ," + this.getUsername();
  }
  
  @Override
  public boolean equals(Object obj){
	Utente utente;
	if(obj instanceof Utente)
		utente = (Utente)obj;
	else
		return false;
	
	if((this.username.equals(utente.username) || this.email.equals(utente.email)) && this.password.equals(utente.password))
		return true;
	return false;
	  
  }
}
