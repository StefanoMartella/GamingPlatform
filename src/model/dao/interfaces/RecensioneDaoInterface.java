package src.model.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import src.model.*;

/**
*Interface that defines the standard operations to be performed on a Recensione model objec
*/
public interface RecensioneDaoInterface{

  public void insertReview(String testo, Gioco gioco, Utente utente) throws SQLException;
  
  public void deleteReview(Recensione recensione) throws SQLException;
  
  public ArrayList<Recensione> allReviews() throws SQLException;
  
  public ArrayList<Recensione> allReviewsNotApproved() throws SQLException;
  
  public void deleteAllReviews() throws SQLException;
  
  public Recensione findReviewById(int id) throws SQLException;
  
  public Recensione findReviewByUserAndGame(Utente utente, Gioco gioco) throws SQLException;
}
