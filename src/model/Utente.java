package model;

public class Utente{
  private int id;
  private String nome;
  private String cognome;
  private String username;
  private String email;
  private String password;
  private int livello;
  private int puntiExp;

  public Utente(){}
  public Utente(int id, String nome, String cognome, String username, String email, String password, int livello, int puntiExp){
    this.id = id;
    this.nome = nome;
    this.cognome = cognome;
    this.username = username;
    this.email = email;
    this.password = password;
    this.livello = livello;
    this.puntiExp = puntiExp;
  }

  // Getters.
  public int getId(){ return this.id; }
  public String getNome(){ return this. nome; }
  public String getCognome(){ return this.cognome; }
  public String getUsername(){ return this.username; }
  public String getEmail(){ return this.email; }
  public String getPassword(){ return this.password; }
  public int getLivello(){ return this.livello; }
  public int getPuntiExp(){ return this.puntiExp; }

  // Setters.
  public void setId(int id){ this.id = id; }
  public void setNome(String nome){ this.nome = nome; }
  public void setCognome(String cognome){ this.cognome = cognome; }
  public void setUsername(String username){ this.username = username; }
  public void setEmail(String email){ this.email = email; }
  public void setPassword(String password){ this.password = password; }
  public void setLivello(int livello){ this.livello = livello; }
  public void setPuntiExp(int puntiExp){ this.puntiExp = puntiExp; }
}
