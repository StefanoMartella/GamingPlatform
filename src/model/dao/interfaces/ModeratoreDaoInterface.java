package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Moderatore;

public interface ModeratoreDaoInterface{

  Moderatore insertModerator(Moderatore moderatore) throws SQLException;
  void deleteModerator(Moderatore moderatore) throws SQLException;
  List<Moderatore> allModerators() throws SQLException;
  void deleteAllModerators() throws SQLException;
  Moderatore findById(int id) throws SQLException;
}
