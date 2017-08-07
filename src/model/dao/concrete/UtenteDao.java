package model.dao.concrete;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import model.dao.interfaces.UtenteDaoInterface;
import model.database.DB;
import model.*;

public class UtenteDao implements UtenteDaoInterface{
  private static final String
  INSERT = "INSERT INTO utente(nome, cognome, username, email, password) VALUES (?, ?, ?, ?, ?);";

  private static final String
  DELETE = "DELETE FROM utente WHERE id = ?;";

  private static final String
  ALL = "SELECT * FROM utente;";

  private static final String
  DELETE_ALL = "DELETE FROM utente;";
  
  private static final String
  FIND_BY_USERNAME = "SELECT * FROM utente WHERE username = ?;";

  private static final String
  VOTE_GAME = "INSERT INTO voto(votazione, gioco, utente) VALUES (?, ?, ?);";

  private static final String
  REVIEW_GAME = "INSERT INTO recensione(testo, gioco, utente) VALUES (?, ?, ?);";

  private static final String
  APPROVE_REVIEW = "UPDATE recensione SET approvazione = 1 WHERE id = ?;";

  private static final String
  PROMOTE_USER = "UPDATE utente SET tipo = \"moderatore\" WHERE id = ?;";

  private static final String
  DEMOTE_USER = "UPDATE utente SET tipo = \"utente\" WHERE id = ?;";

  private static final String
  GET_TIMELINE = "SELECT * FROM timeline WHERE utente = ?;";
  
  private  static final String
  GAME_ALREADY_VOTED = "SELECT COUNT(*) AS total FROM voto WHERE utente = ? and gioco = ?;";
  
  private static final String
  GAME_ALREADY_REVIEWED = "SELECT COUNT(*) AS total FROM recensione WHERE utente = ? and gioco = ?;";

  @Override
  public void insertUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, utente.getNome());
    ps.setString(2, utente.getCognome());
    ps.setString(3, utente.getUsername());
    ps.setString(4, utente.getEmail());
    ps.setString(5, utente.getPassword());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void deleteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setInt(1, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public List<Utente> allUsers() throws SQLException{
    List<Utente> all_users = new ArrayList<>();
    Connection connection = DB.openConnection();
    Statement s = connection.createStatement();
    ResultSet rset = s.executeQuery(ALL);
    while (rset.next()){
      Utente utente = new Utente(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9));
      all_users.add(utente);
    }
    s.close();
    rset.close();
    connection.close();
    return all_users;
  }

  @Override
  public void deleteAllUsers() throws SQLException{
    Connection connection = DB.openConnection();
    Statement s = connection.createStatement();
    s.executeUpdate(DELETE_ALL);
    s.close();
    connection.close();
  }

  
  @Override
  public Utente findUserByUsername(String username) throws SQLException{
	Utente utente;
	Connection connection = DB.openConnection();  
	PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME);
	ps.setString(1, username);
	ResultSet rset = ps.executeQuery();
	rset.first();
	utente = new Utente(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9));
	ps.close();
    rset.close();
    connection.close();
	return utente;
  }
  
  @Override
  public void voteGame(int voto, Utente utente, Gioco gioco) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(VOTE_GAME);
    ps.setInt(1, voto);
    ps.setInt(2, gioco.getId());
    ps.setInt(3, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void reviewGame(String testoRecensione, Utente utente, Gioco gioco) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(REVIEW_GAME);
    ps.setString(1, testoRecensione);
    ps.setInt(2, gioco.getId());
    ps.setInt(3, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void promoteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(PROMOTE_USER);
    ps.setInt(1, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void demoteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DEMOTE_USER);
    ps.setInt(1, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void approveReview(Recensione recensione) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(APPROVE_REVIEW);
    ps.setInt(1, recensione.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void disapproveReview(Recensione recensione) throws SQLException{
    new RecensioneDao().deleteReview(recensione);
  }

  @Override
  public TreeMap<Integer, String> getTimeline(Utente utente) throws SQLException{
    TreeMap<Integer, String> timeline = new TreeMap<>();
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(GET_TIMELINE);
    ps.setInt(1, utente.getId());
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      timeline.put(rset.getInt(2), rset.getDate(1).toString());
    }
    ps.close();
    rset.close();
    connection.close();
    return timeline;
  }
  
  @Override
  public boolean gameAlreadyVotedByUser(Utente utente, Gioco gioco) throws SQLException{
    boolean already_voted = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(GAME_ALREADY_VOTED);
    ps.setInt(1, utente.getId());
    ps.setInt(2, gioco.getId());
    ResultSet rset = ps.executeQuery();
    rset.first();
    if(rset.getInt(1) == 1){ already_voted = true; }
    ps.close();
    rset.close();
    connection.close();
    return already_voted;
  }
  
  @Override
  public boolean reviewAlreadyMadeByUser(Utente utente, Gioco gioco) throws SQLException{
    boolean already_reviewed = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(GAME_ALREADY_REVIEWED);
    ps.setInt(1, utente.getId());
    ps.setInt(2, gioco.getId());
    ResultSet rset = ps.executeQuery();
    rset.first();
    if(rset.getInt(1) == 1){ already_reviewed = true; }
    ps.close();
    rset.close();
    connection.close();
    return already_reviewed;
  }
}
