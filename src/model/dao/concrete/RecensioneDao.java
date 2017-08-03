package concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.interfaces.RecensioneDaoInterface;
import database.DB;
import model.Recensione;

public class RecensioneDao implements RecensioneDaoInterface{


  public void insertReview(String testo, int idGioco, int idUtente) throws SQLException{

  }
  public void deleteReview(int idRecensione) throws SQLException{

  }
  public List<Recensione> allReviews() throws SQLException{

  }
  public List<Recensione> allReviewsNotApproved() throws SQLException{

  }
  public List<Recensione> AllGameReviews(int idGioco) throws SQLException0{

  }
  public void deleteAllReviews() throws SQLException{

  }
  public boolean reviewAlreadyMadeByUser(int idUser, int idGioco) throws SQLException{

  }
}
