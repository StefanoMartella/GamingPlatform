package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Utente;

public interface UtenteDaoInterface{

  Utente insertUser(Utente utente) throws SQLException;
  void deleteUser(Utente utente) throws SQLException;
  List<Utente> allUsers() throws SQLException;
  void deleteAllUsers() throws SQLException;
  Utente findById(int id) throws SQLException;
}
