package concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.RecensioneDaoInterface;
import dao.database.DB;
import model.Recensione;

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


  public void insertReview(String testo, int idGioco, int idUtente) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, testo);
    ps.setString(2, idGioco);
    ps.setString(3, idUtente);
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public void deleteReview(int idRecensione) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setString(1, idRecensione);
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public List<Recensione> filterReviews(String QUERY){
    List<Recensione> filtred_reviews = ArrayList<Recensione>;
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
    return filterReviews(ALL_NOT_APPROVED)
  }
  public void deleteAllReviews() throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE_ALL);
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public boolean reviewAlreadyMadeByUser(int idUtente, int idGioco) throws SQLException{
    boolean already_made = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALREADY_MADE);
    ps.setString(1, idUtente);
    ps.setString(2, idGioco);
    ResultSet rset = ps.executeQuery();
    if(rset.geInt("total") == 1){ already_made = true; }
    ps.close();
    rset.close();
    return already_made;
  }
}
