package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.*;

public interface GiocoDaoInterface{

  public void insertGame(Gioco gioco) throws SQLException;
  public void deleteGame(Gioco gioco) throws SQLException;
  public List<Gioco> allGames() throws SQLException;
  public void deleteAllGames() throws SQLException;
  public float getVotesAvarege(Gioco gioco) throws SQLException;
  public List<Recensione> allGameReviews(Gioco gioco) throws SQLException;
  public boolean gameAlredyVotedByUser(Utente utente, Gioco gioco) throws SQLException;
}
