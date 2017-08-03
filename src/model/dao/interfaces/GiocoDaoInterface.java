package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Gioco;

public interface GiocoDaoInterface{

  public void insertGame(Gioco gioco) throws SQLException;
  public void updateGame(Gioco gioco) throws SQLExeption;
  public void deleteGame(Gioco gioco) throws SQLException;
  public List<Gioco> allGames() throws SQLException;
  public void deleteAllGames() throws SQLException;
  public Gioco findById(int idGioco) throws SQLException;
  public float getVotesAvarege() throws SQLException;
  public boolean gameAlredyVotedByUser(int idUser) throws SQLException;
}
