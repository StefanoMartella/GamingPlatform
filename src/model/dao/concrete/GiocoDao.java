package model.dao.concrete;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.interfaces.GiocoDaoInterface;
import model.database.DB;
import model.*;

public class GiocoDao implements GiocoDaoInterface{
  private static final String
  INSERT = "INSERT INTO gioco(nome, exp) VALUES (?, ?);";

  private static final String
  DELETE = "DELETE FROM gioco WHERE id = ?;";

  private static final String
  ALL = "SELECT * FROM gioco;";

  private static final String
  DELETE_ALL = "DELETE FROM gioco;";
  
  private static final String
  FIND_BY_NAME = "SELECT * FROM gioco WHERE nome = ?;";

  private static String
  VOTES_AVERAGE = "SELECT AVG(votazione) AS average FROM gioco JOIN voto ON gioco.id = voto.gioco WHERE id = ?;";

  private static final String
  ALL_GAME_REVIEWS = "SELECT * FROM recensione WHERE recensione.gioco = ? AND recensione.approvazione = 1;";

  @Override
  public void insertGame(Gioco gioco) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, gioco.getNome());
    ps.setInt(2, gioco.getExp());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void deleteGame(Gioco gioco) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setInt(1, gioco.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public ArrayList<Gioco> allGames() throws SQLException{
    ArrayList<Gioco> all_games = new ArrayList<>();
    Connection connection = DB.openConnection();
    Statement s = connection.createStatement();
    ResultSet rset = s.executeQuery(ALL);
    while (rset.next()){
      Gioco gioco = new Gioco(rset.getInt(1), rset.getString(2), rset.getInt(3));
      all_games.add(gioco);
    }
    s.close();
    rset.close();
    connection.close();
    return all_games;
  }

  @Override
  public void deleteAllGames() throws SQLException{
    Connection connection = DB.openConnection();
    Statement s = connection.createStatement();
    s.executeUpdate(DELETE_ALL);
    s.close();
    connection.close();
  }

  @Override
  public Gioco findGameByName(String name) throws SQLException{
    Gioco gioco;
    Connection connection = DB.openConnection();  
    PreparedStatement ps = connection.prepareStatement(FIND_BY_NAME);
    ps.setString(1, name);
    ResultSet rset = ps.executeQuery();
    if (rset.first() == false) return null;
    gioco = new Gioco(rset.getInt(1), rset.getString(2), rset.getInt(3));
    ps.close();
    rset.close();
    connection.close();
    return gioco;
  }
  
  @Override
  public float getVotesAverage(Gioco gioco) throws SQLException{
    float votes_avarage;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(VOTES_AVERAGE);
    ps.setInt(1, gioco.getId());
    ResultSet rset = ps.executeQuery();
    rset.first();
    votes_avarage = rset.getFloat(1);
    ps.close();
    rset.close();
    connection.close();
    return votes_avarage;
  }

  @Override
  public ArrayList<Recensione> allGameReviews(Gioco gioco) throws SQLException{
    ArrayList<Recensione> game_reviews = new ArrayList<>();
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALL_GAME_REVIEWS);
    ps.setInt(1, gioco.getId());
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      Recensione recensione = new Recensione(rset.getInt(1), rset.getBoolean(2), rset.getString(3), rset.getInt(4), rset.getInt(5));
      game_reviews.add(recensione);
    }
    ps.close();
    rset.close();
    connection.close();
    return game_reviews;
  }
}
