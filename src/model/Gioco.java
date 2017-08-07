package model;

public class Gioco{
  private int id;
  private String nome;
  private int exp;

  public Gioco(){}
  public Gioco(String nome, int exp){
    this.nome = nome;
    this.exp = exp;  
  }
  public Gioco(int id, String nome, int exp){
	// Calling this() causes problems with interactions with db.
	this.id = id;
    this.nome = nome;
    this.exp = exp;
  }

  public int getId(){ return this.id; }
  public String getNome(){ return this.nome; }
  public int getExp(){ return this.exp; }

  public void setId(int id){ this.id = id; }
  public void setNome(String nome){ this.nome = nome; }
  public void setExp(int exp){ this.exp = exp; }
  
  public String toString(){
    return this.getId() + " - " + this.getNome();
  }
}
