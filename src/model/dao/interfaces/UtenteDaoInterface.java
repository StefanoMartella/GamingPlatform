package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Utente;

public interface UtenteDaoInterface{

  public void insertUser(Utente utente) throws SQLException;
  public void updateUser(Utente utente) throws SQLException;
  public void deleteUser(Utente utente) throws SQLException;
  public List<Utente> allUsers() throws SQLException;
  public void deleteAllUsers() throws SQLException;
  public Utente findById(int idUtente) throws SQLException;
  public void voteGame(int idGioco) throws SQLException;
  public void reviewGame(int idGioco) throws SQLException;
  public void play(int idGioco) throws SQLException;
}
