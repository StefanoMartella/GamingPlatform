package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Recensione;

public interface RecensioneDaoInterface{

  public Recensione insertReview(Recensione recensione) throws SQLException;
  public void updateReview(Recensione recensione) throws SQLException;
  public void deleteReview(Recensione recensione) throws SQLException;
  public List<Recensione> allReviews() throws SQLException;
  public List<Recensione> allReviewsNotApproved() throws SQLException;
  public void deleteAllReviews() throws SQLException;
  public Recensione findById(int id) throws SQLException;
}
