package concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import dao.interfaces.UtenteDaoInterface;
import dao.concrete.GiocoDao;
import dao.concrete.RecensioneDao;
import model.database.DB;
import model.Utente;
import model.Recensione;
import model.Gioco;

public class UtenteDao implements UtenteDaoInterface{
  private static final String
  INSERT = "INSERT INTO utente(nome, cognome, username, email, password) VALUES (?, ?, ?, ?, ?)";

  private static final String
  DELETE = "DELETE FROM utente WHERE id = ?";

  private static final String
  ALL = "SELECT * FROM utente";

  private static final String
  DELETE_ALL = "DELETE FROM utente";

  private static final String
  VOTE_GAME = "INSERT INTO voto(votazione, gioco, utente) VALUES (?, ?, ?)";

  private static final String
  REVIEW_GAME = "INSERT INTO recensione(testo, gioco, utente) VALUES (?, ?, ?)";

  private static final String
  APPROVE_REVIEW = "UPDATE recensione SET approvazione = 1 WHERE id = ?";

  private static final String
  PROMOTE_USER = "UPDATE utente SET tipo = \"M\" WHERE id = ?";

  private static final String
  DEMOTE_USER = "UPDATE utente SET tipo = \"U\" WHERE id = ?";

  private static final String
  GET_TIMELINE = "SELECT * FROM timeline WHERE utente = ?";

  public void insertUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, utente.getNome());
    ps.setString(2, utente.getCognome());
    ps.setString(3, utente.getUsername());
    ps.setString(4, utente.getEmail());
    ps.setString(5, utente.getPassword());
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public void deleteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setString(1, utente.getId());
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public List<Utente> allUsers() throws SQLException{
    List<Utente> all_users = ArrayList<Utente>;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALL);
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      Utente utente = new Utente(rset.getInt("id"), rset.getString("nome"), rset.getString("cognome"), rset.getString("username"), rset.getString("email"). rset.getString("password"), 0, 0);
			all_users.add(utente);
    }
    ps.close();
    rset.close();
    return all_users;
  }
  public void deleteAllUsers() throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE_ALL);
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void voteGame(int voto, Gioco gioco, Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(VOTE_GAME);
    ps.setString(1, voto);
    ps.setString(2, utente.getId());
    ps.setString(3, gioco.getId());
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void reviewGame(String testoRecensione, Gioco gioco, Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(REVIEW_GAME);
    ps.setString(1, testoRecensione);
    ps.setString(2, gioco.getId());
    ps.setString(3, utente.getId());
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void promoteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(PROMOTE_USER);
    ps.setString(1, utente.getId());
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void demoteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DEMOTE_USER);
    ps.setString(1, utente.getId());
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void approveReview(Recensione recensione) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(APPROVE_REVIEW);
    ps.setString(1, recensione.getId());
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void disapproveReview(Recensione recensione) throws SQLException{
    new RecensioneDao().deleteReview(recensione);
  }
  public TreeMap<Integer, String> getTimeline(Utente utente) throws SQLException{
    TreeMap<Integer, String> timeline = new TreeMap<Integer, String>();
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(GET_TIMELINE);
    ps.setString(1, utente.getId());
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      timeline.put(rset.getInt("livello"), rset.getDate("data").toString());
    }
    ps.close();
    rset.close();
    return timeline;
  }
}
