package src.model.dao.concrete;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.model.dao.interfaces.RecensioneDaoInterface;
import src.model.database.DB;
import src.model.*;

public class RecensioneDao implements RecensioneDaoInterface{
  private static final String
  INSERT = "INSERT INTO recensione(testo, gioco, utente) VALUES (?, ?, ?);";

  private static final String
  DELETE = "DELETE FROM recensione WHERE id = ?;";

  private static final String
  ALL = "SELECT * FROM recensione;";

  private static final String
  ALL_NOT_APPROVED = "SELECT * FROM recensione WHERE approvazione = 0;";

  private static final String
  DELETE_ALL = "DELETE FROM gioco;";
  
  private static final String
  FIND_REVIEW = "SELECT * FROM recensione WHERE id = ?;";
  
  private static final String
  FIND_REVIEW_BY_USER_AND_GAME = "SELECT * from recensione WHERE utente = ? AND gioco = ?;";

  @Override
  public void insertReview(String testo, Gioco gioco, Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, testo);
    ps.setInt(2, gioco.getId());
    ps.setInt(3, utente.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  @Override
  public void deleteReview(Recensione recensione) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setInt(1, recensione.getId());
    ps.executeUpdate();
    ps.close();
    connection.close();
  }

  public ArrayList<Recensione> filterReviews(String QUERY) throws SQLException{
    ArrayList<Recensione> filtred_reviews = new ArrayList<>();
    Connection connection = DB.openConnection();
    Statement s = connection.createStatement();
    ResultSet rset = s.executeQuery(QUERY);
    while (rset.next()){
      Recensione recensione = new Recensione(rset.getByte(1), rset.getBoolean(2), rset.getString(3), rset.getInt(4), rset.getInt(5));
      filtred_reviews.add(recensione);
    }
    s.close();
    rset.close();
    connection.close();
    return filtred_reviews;
  }

  @Override
  public ArrayList<Recensione> allReviews() throws SQLException{
    return filterReviews(ALL);
  }

  @Override
  public ArrayList<Recensione> allReviewsNotApproved() throws SQLException{
    return filterReviews(ALL_NOT_APPROVED);
  }

  @Override
  public void deleteAllReviews() throws SQLException{
    Connection connection = DB.openConnection();
    Statement s = connection.createStatement();
    s.executeUpdate(DELETE_ALL);
    s.close();
    connection.close();
  }
  
  @Override
  public Recensione findReviewById(int id) throws SQLException{
	Recensione r;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(FIND_REVIEW);
    ps.setInt(1, id);
	ResultSet rset = ps.executeQuery();
    if (rset.first() == false) return null;
	r = new Recensione(rset.getInt(1), rset.getBoolean(2), rset.getString(3), rset.getInt(4), rset.getInt(5));
	ps.close();
    connection.close();
	return r;
  }
  
  @Override
  public Recensione findReviewByUserAndGame(Utente utente, Gioco gioco) throws SQLException{
	Recensione r;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(FIND_REVIEW_BY_USER_AND_GAME);
    ps.setInt(1, utente.getId());
	ps.setInt(2, gioco.getId());
	ResultSet rset = ps.executeQuery();
    if (rset.first() == false) return null;
	r = new Recensione(rset.getInt(1), rset.getBoolean(2), rset.getString(3), rset.getInt(4), rset.getInt(5));
	ps.close();
    connection.close();
	return r;
  }
}
