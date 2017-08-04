package interfaces;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.TreeMap;

import model.Utente;

public interface UtenteDaoInterface{

  public void insertUser(String nome, String cognome, String username, String email, String password) throws SQLException;
  public void deleteUser(int idUtente) throws SQLException;
  public List<Utente> allUsers() throws SQLException;
  public void deleteAllUsers() throws SQLException;
  public String getUserType(int idUtente) throws SQLException;
  public void voteGame(int voto, int idUtente, int idGioco) throws SQLException;
  public void reviewGame(String testoRecensione, int idGioco, int idUtente) throws SQLException;
  public void promoteUser(int idModeratore, int idUtente) throws SQLException;
  public void demoteUser(int idModeratore, int idUtente) throws SQLException;
  public void approveReview(int idRecensione) throws SQLException;
  public void disapproveReview(int idRecensione) throws SQLException;
  public TreeMap<Integer, String> getTimeline(int idUtente) throws SQLException;
}
