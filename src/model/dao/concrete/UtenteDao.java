package src.model.dao.concrete;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import src.model.dao.interfaces.UtenteDaoInterface;
import src.model.database.DB;
import src.model.*;

/**
*Class which contains all MySql queries to get user's informations from database
**/
public class UtenteDao implements UtenteDaoInterface{
  private static final String
  INSERT = "INSERT INTO utente(nome, cognome, username, email, password) VALUES (?, ?, ?, ?, ?);";

  private static final String
  DELETE = "DELETE FROM utente WHERE id = ?;";

  private static final String
  ALL = "SELECT * FROM utente ORDER BY username;";

  private static final String
  DELETE_ALL = "DELETE FROM utente;";
  
  private static final String
  FIND_BY_USERNAME = "SELECT * FROM utente WHERE username = ?;";
  
  private static final String
  PLAY = "UPDATE utente SET puntiExp = ? + ? WHERE username = ?;";
  
  private static final String
  UPDATE_LEVEL = "UPDATE utente SET livello = ? WHERE username = ?;";

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
  
  private static final String
  USERNAME_ALREADY_USED = "SELECT COUNT(*) AS total FROM utente WHERE username = ?;";
  
  private static final String
  EMAIL_ALREADY_USED = "SELECT COUNT(*) AS total FROM utente WHERE email = ?;";

  /**
  *Method to insert a user
  *@param utente user we want to add to the database
  *@throws SQLException if no database connection is found or another error occurs
  **/
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

  /**
  *Method to delete a user
  *@param utente user we want to delete from database
  *@throws SQLException if no database connection is found or another error occurs
  **/
  @Override
  public void deleteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setInt(1, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  /**
  *Method to get all users
  *@return ArrayList all users
  *@throws SQLException if no database connection is found or another error occurs
  **/
  @Override
  public ArrayList<Utente> allUsers() throws SQLException{
    ArrayList<Utente> all_users = new ArrayList<>();
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

  /**
  *Method to delete all users
  *@throws SQLException if no database connection is found or another error occurs
  **/
  @Override
  public void deleteAllUsers() throws SQLException{
    Connection connection = DB.openConnection();
    Statement s = connection.createStatement();
    s.executeUpdate(DELETE_ALL);
    s.close();
    connection.close();
  }

  /**
  *Method to find a user thanks to his username
  *@param username user's username
  *@return Utente searched user
  *@throws SQLException if no database connection is found or another error occurs
  **/
  @Override
  public Utente findUserByUsername(String username) throws SQLException{
    Utente utente;
    Connection connection = DB.openConnection();  
    PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME);
    ps.setString(1, username);
    ResultSet rset = ps.executeQuery();
    if (rset.first() == false) return null;
    utente = new Utente(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getInt(8), rset.getInt(9));
    ps.close();
    rset.close();
    connection.close();
    return utente;
  }
  
  /**
  *Method to play with a game
  *@param ut user who plays
  *@param g played game
  *@throws SQLException if no database connection is found or another error occurs
  */
  @Override
  public void play(Utente ut, Gioco g) throws SQLException{
    Connection connection = DB.openConnection();  
    PreparedStatement ps = connection.prepareStatement(PLAY);
    ps.setInt(1, ut.getPuntiExp());
    ps.setInt(2, g.getExp());
    ps.setString(3, ut.getUsername());
    ps.executeUpdate();
    ps.close();
    PreparedStatement ps2 = connection.prepareStatement(UPDATE_LEVEL);
    if(ut.getPuntiExp()>= 500)
	ps2.setInt(1, 5);
    else
	ps2.setInt(1, (ut.getPuntiExp()+g.getExp())/100);
    ps2.setString(2, ut.getUsername());
    ps2.executeUpdate();
    ps2.close();
    connection.close();
    ut.setPuntiExp(ut.getPuntiExp()+g.getExp());
    if(ut.getPuntiExp()>= 500){
	ut.setLivello(5);
    }
    else
	ut.setLivello(ut.getPuntiExp()/100);
  }
  
  /**
  *Method to play vote a game
  *@param voto game's vote
  *@param utente user who votes
  *@param gioco voted game
  *@throws SQLException if no database connection is found or another error occurs
  */
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

  /**
  *Method to play review a game
  *@param testoRecensione review's text
  *@param utente user who reviews
  *@param gioco reviewed game
  *@throws SQLException if no database connection is found or another error occurs
  */
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

  /**
  *Method to approve a review
  *@param recensione review we want to approve
  *@throws SQLException if no database connection is found or another error occurs
  */
  @Override
  public void approveReview(Recensione recensione) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(APPROVE_REVIEW);
    ps.setInt(1, recensione.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }
  
  /**
  *Method to promote a user
  *@param utente user we want to promote
  *@throws SQLException if no database connection is found or another error occurs
  */
  @Override
  public void promoteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(PROMOTE_USER);
    ps.setInt(1, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  /**
  *Method to demote a user
  *@param utente user we want to demote
  *@throws SQLException if no database connection is found or another error occurs
  */
  @Override
  public void demoteUser(Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DEMOTE_USER);
    ps.setInt(1, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  /**
  *Method to disapprove a review
  *@param recensione review we want to disapprove
  *@throws SQLException if no database connection is found or another error occurs
  */
  @Override
  public void disapproveReview(Recensione recensione) throws SQLException{
    new RecensioneDao().deleteReview(recensione);
  }

  /**
  *Method to get user's timeline
  *@param utente user we want the timeline
  *@return  TreeMap user's timeline
  *@throws SQLException if no database connection is found or another error occurs
  */
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
  
  /**
  *Method to check if a user has already voted a game
  *@param utente user who votes
  *@param gioco voted game
  *@return  boolean true if the user has already voted the game, false otherwise
  *@throws SQLException if no database connection is found or another error occurs
  */
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
  
  /**
  *Method to check if a user has already review a game
  *@param utente user who reviews
  *@param gioco reviewed game
  *@return  boolean true if the user has already reviewed the game, false otherwise
  *@throws SQLException if no database connection is found or another error occurs
  */
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
  
  /**
  *Method to check if an email or an username are already been used
  *@param QUERY query for the database
  *@param email_or_username email/username we want check
  *@return  boolean true if email or username are already been used, false otherwise
  *@throws SQLException if no database connection is found or another error occurs
  */
  public boolean usernameOrEmailAlreadyUsed(String QUERY, String email_or_username) throws SQLException{
    boolean username_or_email_used = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(QUERY);
    ps.setString(1, email_or_username);
    ResultSet rset = ps.executeQuery();
    rset.first();
    if(rset.getInt(1) == 1){ username_or_email_used = true; }
    ps.close();
    rset.close();
    connection.close();
    return username_or_email_used;
  }
  
  /**
  *Method to check if an username is already been used
  *@param  username username
  *@return  boolean true if username is already been used, false otherwise
  *@throws SQLException if no database connection is found or another error occurs
  */
  @Override
  public boolean usernameAlreadyUsed(String username) throws SQLException{
    return usernameOrEmailAlreadyUsed(USERNAME_ALREADY_USED, username);
  }
  
  /**
  *Method to check if an email is already been used
  *@param  email email
  *@return  boolean true if email is already been used, false otherwise
  *@throws SQLException if no database connection is found or another error occurs
  */
  @Override 
  public boolean emailAlreadyUsed(String email) throws SQLException{
    return usernameOrEmailAlreadyUsed(EMAIL_ALREADY_USED, email);
  }
}
