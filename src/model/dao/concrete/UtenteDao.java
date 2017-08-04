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
import database.DB;
import model.Utente;

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
  GET_USER_TYPE = "SELECT tipo FROM utente WHERE id = ?";

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

  public void insertUser(String nome, String cognome, String username, String email, String password) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, nome);
    ps.setString(2, cognome);
    ps.setString(3, username);
    ps.setString(4, email);
    ps.setString(5, password);
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public void deleteUser(int idUtente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setString(1, idUtente);
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
  public String getUserType(int idUtente) throws SQLException{
    String type = null;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(GET_USER_TYPE);
    ps.setString(1, idUtente);
    ResultSet rset = ps.executeQuery();
    type = rset.getString("tipo");
    ps.close();
    rset.close();
    return type;
  }
  public void voteGame(int voto, int idUtente, int idGioco) throws SQLException{
    if( ! new GiocoDao().gameAlredyVotedByUser(idUtente, idGioco) ){
      Connection connection = DB.openConnection();
      PreparedStatement ps = connection.prepareStatement(VOTE_GAME);
      ps.setString(1, voto);
      ps.setString(2, idUtente);
      ps.setString(3, idGioco);
      ResultSet rset = ps.executeUpdate();
      ps.close();
      rset.close();
    }
    else{ System.out.println("L'utente ha già votato questo gioco!";) }
  }
  public void reviewGame(String testoRecensione, int idGioco, int idUtente) throws SQLException{
    if( ! new RecensioneDao().reviewAlreadyMadeByUser(idGioco, idUtente) ){
      Connection connection = DB.openConnection();
      PreparedStatement ps = connection.prepareStatement(REVIEW_GAME);
      ps.setString(1, testoRecensione);
      ps.setString(2, idGioco);
      ps.setString(3, idUtente);
      ResultSet rset = ps.executeUpdate();
      ps.close();
      rset.close();
    }
    else{ System.out.println("L'utente ha già votato questo gioco!";) }
  }
  public void promoteUser(int idModeratore, int idUtente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(PROMOTE_USER);
    ps.setString(1, idUtente);
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void demoteUser(int idModeratore, int idUtente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DEMOTE_USER);
    ps.setString(1, idUtente);
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void approveReview(int idRecensione) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(APPROVE_REVIEW);
    ps.setString(1, idRecensione);
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public void disapproveReview(int idRecensione){
    new RecensioneDao().deleteReview(idRecensione);
  }
  public TreeMap<Integer, String> getTimeline(int idUtente) throws SQLException{
    TreeMap<Integer, String> timeline = new TreeMap<Integer, String>();
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(GET_TIMELINE);
    ps.setString(1, idUtente);
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      timeline.put(rset.getInt("livello"), rset.getDate("data").toString());
    }
    ps.close();
    rset.close();
    return timeline;
  }
}
