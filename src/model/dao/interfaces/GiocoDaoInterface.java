package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Gioco;
import model.Recensione;

public interface GiocoDaoInterface{

  public void insertGame(Gioco gioco) throws SQLException;
  public void deleteGame(Gioco gioco) throws SQLException;
  public List<Gioco> allGames() throws SQLException;
  public void deleteAllGames() throws SQLException;
  public float getVotesAvarege(Gioco gioco) throws SQLException;
  public List<Recensione> AllGameReviews(Gioco gioco) throws SQLException;
  public boolean gameAlredyVotedByUser(Utente utente, Gioco gioco) throws SQLException;
}
