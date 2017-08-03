package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Utente;

public interface UtenteDaoInterface{

  public Utente insertUser(Utente utente) throws SQLException;
  public void updateUser(Utente utente) throws SQLException;
  public void deleteUser(Utente utente) throws SQLException;
  public List<Utente> allUsers() throws SQLException;
  public void deleteAllUsers() throws SQLException;
  public Utente findById(int id) throws SQLException;
}
