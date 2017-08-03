package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Gioco;

public interface GiocoDaoInterface{

  public Gioco insertGame(Gioco gioco) throws SQLException;
  public void updateGame(Gioco gioco) throws SQLExeption;
  public void deleteGame(Gioco gioco) throws SQLException;
  public List<Gioco> allGames() throws SQLException;
  public void deleteAllGames() throws SQLException;
  public Gico findById(int id) throws SQLException;
}
