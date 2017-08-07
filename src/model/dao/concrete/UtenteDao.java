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
      Utente utente = new Utente(rset.getInt("id"), rset.getString("nome"), rset.getString("cognome"), rset.getString("username"), rset.getString("email"), rset.getString("password"), rset.getString("tipo"), 0, 0);
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
  public void voteGame(int voto, Utente utente, Gioco gioco) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(VOTE_GAME);
    ps.setInt(1, voto);
    ps.setInt(2, utente.getId());
    ps.setInt(3, gioco.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void reviewGame(String testoRecensione, Gioco gioco, Utente utente) throws SQLException{
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
      timeline.put(rset.getInt("livello"), rset.getDate("data").toString());
    }
    ps.close();
    rset.close();
    connection.close();
    return timeline;
  }
}
