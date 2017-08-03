package interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Recensione;

public interface RecensioneDaoInterface{

  public Recensione insertReview(String testo, int idGioco, int idUtente) throws SQLException;
  public void deleteReview(int idRecensione) throws SQLException;
  public List<Recensione> allReviews() throws SQLException;
  public List<Recensione> allReviewsNotApproved() throws SQLException;
  public List<Recensione> AllGameReviews(int idGioco) throws SQLException;
  public void deleteAllReviews() throws SQLException;
  public Recensione findReviewById(int idRecensione) throws SQLException;
  public boolean reviewAlreadyMadeByUser(int idUser, int idGioco) throws SQLException;
}
