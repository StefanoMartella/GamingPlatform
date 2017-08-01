package model;

public class Gioco{
  private int id;
  private String nome;
  private int exp;

  public Gioco(){}
  public Gioco(int id, String nome, int exp){
    this.id = id;
    this.nome = nome;
    this.exp = exp;
  }

  // Getters.
  public int getId(){ return this.id; }
  public String getNome(){ return this.nome; }
  public int getExp(){ return this.exp; }

  // Setters.
  public void setId(int id){ this.id = id; }
  public void setNome(String nome){ this.nome = nome; }
  public void setExp(int exp){ this.exp = exp; }
}