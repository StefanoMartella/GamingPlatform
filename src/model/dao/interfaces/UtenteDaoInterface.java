package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Utente;

public interface UtenteDaoInterface{

  public Utente insertUser(String nome, String cognome, String username, String email, String password, String tipo) throws SQLException;
  public void deleteUser(int idUtente) throws SQLException;
  public List<Utente> allUsers() throws SQLException;
  public void deleteAllUsers() throws SQLException;
  public Utente findUserById(int idUtente) throws SQLException;
  public boolean userAlreadyRegistrated(Utente utente) throws SQLException;
  public void voteGame(int idGioco, int voto) throws SQLException;
  public void reviewGame(int idGioco) throws SQLException;
  public void play(int idGioco) throws SQLException;
}
