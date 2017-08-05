package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.*;

public interface RecensioneDaoInterface{

  public void insertReview(String testo, Gioco gioco, Utente utente) throws SQLException;
  public void deleteReview(Recensione recensione) throws SQLException;
  public List<Recensione> allReviews() throws SQLException;
  public List<Recensione> allReviewsNotApproved() throws SQLException;
  public void deleteAllReviews() throws SQLException;
  public boolean reviewAlreadyMadeByUser(Utente utente, Gioco gioco) throws SQLException;
}
