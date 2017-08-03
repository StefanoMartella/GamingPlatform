package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Moderatore;

public interface ModeratoreDaoInterface{

  public Moderatore insertModerator(Moderatore moderatore) throws SQLException;
  public void updateModerator(Moderatore moderatore) throws SQLException;
  public void deleteModerator(Moderatore moderatore) throws SQLException;
  public List<Moderatore> allModerators() throws SQLException;
  public void deleteAllModerators() throws SQLException;
  public Moderatore findById(int id) throws SQLException;
}
