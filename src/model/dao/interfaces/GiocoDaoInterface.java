package src.model.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import src.model.*;

/**
*Interface that defines the standard operations to be performed on a Gioco model objec
*/
public interface GiocoDaoInterface{

  public void insertGame(Gioco gioco) throws SQLException;
  
  public void deleteGame(Gioco gioco) throws SQLException;
  
  public ArrayList<Gioco> allGames() throws SQLException;
  
  public void deleteAllGames() throws SQLException;
  
  public Gioco findGameByName(String name) throws SQLException;
  
  public float getVotesAverage(Gioco gioco) throws SQLException;
  
  public ArrayList<Recensione> allGameReviews(Gioco gioco) throws SQLException;
}
