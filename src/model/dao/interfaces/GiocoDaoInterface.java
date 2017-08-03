package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Gioco;

public interface GiocoDaoInterface{

  public Gioco insertGame(String nome, int exp) throws SQLException;
  public void deleteGame(int idGioco) throws SQLException;
  public List<Gioco> allGames() throws SQLException;
  public void deleteAllGames() throws SQLException;
  public float getVotesAvarege() throws SQLException;
  public boolean gameAlredyVotedByUser(int idUser) throws SQLException;
}
