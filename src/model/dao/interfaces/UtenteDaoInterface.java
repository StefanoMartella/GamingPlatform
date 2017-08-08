package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.TreeMap;

import model.*;

public interface UtenteDaoInterface{

  public void insertUser(Utente utente) throws SQLException;
  public void deleteUser(Utente utente) throws SQLException;
  public List<Utente> allUsers() throws SQLException;
  public void deleteAllUsers() throws SQLException;
  public Utente findUserByUsername(String username) throws SQLException;
  public void voteGame(int voto, Utente utente, Gioco gioco) throws SQLException;
  public void reviewGame(String testoRecensione, Utente utente, Gioco gioco) throws SQLException;
  public void approveReview(Recensione recensione) throws SQLException;
  public void promoteUser(Utente utente) throws SQLException;
  public void demoteUser(Utente utente) throws SQLException;
  public void disapproveReview(Recensione recensione) throws SQLException;
  public TreeMap<Integer, String> getTimeline(Utente utente) throws SQLException;
  public boolean reviewAlreadyMadeByUser(Utente utente, Gioco gioco) throws SQLException;
  public boolean gameAlreadyVotedByUser(Utente utente, Gioco gioco) throws SQLException;
  public boolean usernameAlreadyUsed(String username) throws SQLException;
  public boolean emailAlreadyUsed(String email) throws SQLException;
}
