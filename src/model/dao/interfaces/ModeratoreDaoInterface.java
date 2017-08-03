package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Moderatore;
import model.Utente;
import model.Recensione;

public interface ModeratoreDaoInterface{

  public void insertModerator(Moderatore moderatore) throws SQLException;
  public void updateModerator(Moderatore moderatore) throws SQLException;
  public void deleteModerator(Moderatore moderatore) throws SQLException;
  public List<Moderatore> allModerators() throws SQLException;
  public void deleteAllModerators() throws SQLException;
  public Moderatore findById(int idModeratore) throws SQLException;
  public void approveReview(int idRecensione) throws SQLException;
  public void disapproveReview(int idRecensione) throws SQLException;
  public void promoteUser(int idUser) throws SQLException;
  public void demoteUser(int idUser) throws SQLException;
}
