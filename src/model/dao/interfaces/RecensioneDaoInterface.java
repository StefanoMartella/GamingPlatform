package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.Recensione;

public interface RecensioneDaoInterface{

  Recensione insertReview(Recensione recensione) throws SQLException;
  void deleteReview(Recensione recensione) throws SQLException;
  List<Recensione> allReviews() throws SQLException;
  List<Recensione> allReviewsNotApproved() throws SQLException;
  void deleteReviews() throws SQLException;
  Recensione findById(int id) throws SQLException;
}
