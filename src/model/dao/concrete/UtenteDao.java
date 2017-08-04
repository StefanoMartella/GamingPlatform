package concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import dao.interfaces.UtenteDaoInterface;
import database.DB;
import model.Utente;

public class UtenteDao implements UtenteDaoInterface{
  private static final String
  INSERT = "INSERT INTO utente(nome, cognome, username, email, password) VALUES (?, ?, ?, ?, ?)";

  private static final String
  DELETE = "DELETE FROM utente WHERE id = ?";

  private static final String
  ALL = "SELECT * FROM utente";

  private static final String
  DELETE_ALL = "DELETE FROM utente";

  private static final String
  GET_USER_TYPE = "SELECT tipo FROM utente WHERE id = ?"; //"

  private static final String
  USER_REGISTATED = "SELECT COUNT(*) AS total FROM utente WHERE username = ? and email = ?";

  private static final String
  VOTE_GAME = "INSERT INTO voto(votazione, utente, gioco) VALUES (?, ?, ?)";

  private static final String
  REVIEW_GAME = "INSERT INTO recensione(testo, gioco, utente) VALUES (?, ?, ?)";

  public void insertUser(String nome, String cognome, String username, String email, String password) throws SQLException{

  }
  public void deleteUser(int idUtente) throws SQLException{

  }
  public List<Utente> allUsers() throws SQLException{

  }
  public void deleteAllUsers() throws SQLException{

  }
  public String getUserType(int idUtente) throws SQLException{

  }
  public boolean userAlreadyRegistrated(String username, String email) throws SQLException{

  }
  public void voteGame(int idGioco, int idUtente, int voto) throws SQLException{
    //check if game has already been voted by user.
  }
  public void reviewGame(String testoRecensione, int idGioco, int idUtente) throws SQLException{
    // check if game has already been reviewed by user.
  }
  public void promoteUser(int idModeratore, int idUtente) throws SQLException{

  }
  public void demoteUser(int idModeratore, int idUtente) throws SQLException{

  }
  public void play(int idGioco) throws SQLException{

  }
}
