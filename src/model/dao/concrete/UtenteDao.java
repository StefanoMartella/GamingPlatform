package concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.interfaces.UtenteDaoInterface;
import database.DB;
import model.Utente;

public class UtenteDao implements UtenteDaoInterface{


  public void insertUser(String nome, String cognome, String username, String email, String password, String tipo) throws SQLException{

  }
  public void deleteUser(int idUtente) throws SQLException{

  }
  public List<Utente> allUsers() throws SQLException{

  }
  public void deleteAllUsers() throws SQLException{

  }
  public String getUserType(int idUtente) throws SQLException{

  }
  public boolean userAlreadyRegistrated(Utente utente) throws SQLException{

  }
  public void voteGame(int idGioco, int voto) throws SQLException{

  }
  public void reviewGame(int idGioco, String testoRecensione) throws SQLException{

  }
  public void promoteUser(int idUtente) throws SQLException{

  }
  public void demoteUser(int idUtente) throws SQLException{

  }
  public void play(int idGioco) throws SQLException{

  }
}
