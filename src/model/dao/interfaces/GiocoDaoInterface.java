package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Gioco;

public interface GiocoDaoInterface{

  Gioco insertGame(Gioco gioco) throws SQLException;
  void deleteGame(Gioco gioco) throws SQLException;
  List<Gioco> allGames() throws SQLException;
  void deleteAllGames() throws SQLException;
  Gico findById(int id) throws SQLException;
}
