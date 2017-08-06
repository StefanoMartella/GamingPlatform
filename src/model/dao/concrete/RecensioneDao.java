package model.dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.interfaces.RecensioneDaoInterface;
import model.database.DB;
import model.*;

public class RecensioneDao implements RecensioneDaoInterface{
  private static final String
  INSERT = "INSERT INTO recensione(testo, gioco, utente) VALUES (?, ?, ?)";

  private static final String
  DELETE = "DELETE FROM recensione WHERE id = ?";

  private static final String
  ALL = "SELECT * FROM recensione";

  private static final String
  ALL_NOT_APPROVED = "SELECT * FROM recensione WHERE approvazione = 0";

  private static final String
  DELETE_ALL = "DELETE FROM gioco";

  private static final String
  ALREADY_MADE = "SELECT COUNT(*) AS total FROM recensione WHERE gioco = ? and utente = ?";

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

  public List<Recensione> filterReviews(String QUERY) throws SQLException{
    List<Recensione> filtred_reviews = new ArrayList<Recensione>();
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(QUERY);
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      Recensione recensione = new Recensione(rset.getInt("id"), rset.getBoolean("approvazione"), rset.getString("testo"), rset.getInt("gioco"), rset.getInt("utente"));
			filtred_reviews.add(recensione);
    }
    ps.close();
    rset.close();
	connection.close();
    return filtred_reviews;
  }

  @Override
  public List<Recensione> allReviews() throws SQLException{
    return filterReviews(ALL);
  }

  @Override
  public List<Recensione> allReviewsNotApproved() throws SQLException{
    return filterReviews(ALL_NOT_APPROVED);
  }

  @Override
  public void deleteAllReviews() throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE_ALL);
    ps.executeUpdate();
    ps.close();
	connection.close();
  }

  @Override
  public boolean reviewAlreadyMadeByUser(Utente utente, Gioco gioco) throws SQLException{
    boolean already_made = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALREADY_MADE);
    ps.setInt(1, utente.getId());
    ps.setInt(2, gioco.getId());
    ResultSet rset = ps.executeQuery();
    if(rset.getInt("total") == 1){ already_made = true; }
    ps.close();
    rset.close();
	connection.close();
    return already_made;
  }
}
