package model.dao.concrete;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  private static String
  VOTES_AVERAGE = "SELECT AVG(votazione) AS average FROM gioco JOIN voto ON gioco.id = voto.gioco WHERE id = ?;";

  private static final String
  ALL_GAME_REVIEWS = "SELECT * FROM recensione WHERE recensione.gioco = ? AND recensione.approvazione = 1;";

  private  static final String
  ALREADY_VOTED = "SELECT COUNT(*) AS total FROM voto WHERE utente = ? and gioco = ?;";

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
  public List<Gioco> allGames() throws SQLException{
    List<Gioco> all_games = new ArrayList<>();
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
  public List<Recensione> allGameReviews(Gioco gioco) throws SQLException{
    List<Recensione> game_reviews = new ArrayList<>();
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

  @Override
  public boolean gameAlredyVotedByUser(Utente utente, Gioco gioco) throws SQLException{
    boolean already_voted = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALREADY_VOTED);
    ps.setInt(1, utente.getId());
    ps.setInt(2, gioco.getId());
    ResultSet rset = ps.executeQuery();
    if(rset.getInt("total") == 1){ already_voted = true; }
    ps.close();
    rset.close();
    connection.close();
    return already_voted;
  }
}
