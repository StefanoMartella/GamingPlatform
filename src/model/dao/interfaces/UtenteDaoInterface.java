package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.TreeMap;

import model.Recensione;
import model.Utente;
import model.Gioco;

public interface UtenteDaoInterface{

  public void insertUser(Utente utente) throws SQLException;
  public void deleteUser(Utente utente) throws SQLException;
  public List<Utente> allUsers() throws SQLException;
  public void deleteAllUsers() throws SQLException;
  public void voteGame(int voto, Utente utente, Gioco gioco) throws SQLException;
  public void reviewGame(String testoRecensione, Gioco gioco, Utente utente) throws SQLException;
  public void promoteUser(Utente utente) throws SQLException;
  public void demoteUser(Utente utente) throws SQLException;
  public void approveReview(Recensione recensione) throws SQLException;
  public void disapproveReview(Recensione recensione) throws SQLException;
  public TreeMap<Integer, String> getTimeline(int idUtente) throws SQLException;
}
