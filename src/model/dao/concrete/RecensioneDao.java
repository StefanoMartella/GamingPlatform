package model.dao.concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dao.interfaces.RecensioneDaoInterface;
import model.database.DB;
import model.Recensione;
import model.Utente;
import model.Gioco;

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


  public void insertReview(String testo, Gioco gioco, Utente utente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, testo);
    ps.setString(2, gioco.getId());
    ps.setString(3, utente.getId());
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public void deleteReview(Recensione recensione) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setString(1, recensione.getId());
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public List<Recensione> filterReviews(String QUERY){
    List<Recensione> filtred_reviews = new ArrayList<Recensione>();
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(QUERY);
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      Recensione recensione = new Recensione(rset.getInt("id"), res.getInt("approvazione"), res.getString("testo"), res.getInt("gioco"), res.getInt("utente"));
			filtred_reviews.add(recensione);
    }
    ps.close();
    rset.close();
    return filtred_reviews;
  }
  public List<Recensione> allReviews() throws SQLException{
    return filterReviews(ALL);
  }
  public List<Recensione> allReviewsNotApproved() throws SQLException{
    return filterReviews(ALL_NOT_APPROVED);
  }
  public void deleteAllReviews() throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE_ALL);
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public boolean reviewAlreadyMadeByUser(Utente utente, Gioco gioco) throws SQLException{
    boolean already_made = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALREADY_MADE);
    ps.setString(1, utente.getId());
    ps.setString(2, gioco.getId());
    ResultSet rset = ps.executeQuery();
    if(rset.geInt("total") == 1){ already_made = true; }
    ps.close();
    rset.close();
    return already_made;
  }
}
