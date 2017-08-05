package concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.interfaces.GiocoDaoInterface;
import model.database.DB;
import mode.Utente;
import model.Gioco;
import model.Recensione;

public class GiocoDao implements GiocoDaoInterface{
  private static final String
  INSERT = "INSERT INTO gioco(nome, exp) VALUES (?, ?)";

  private static final String
  DELETE = "DELETE FROM gioco WHERE id = ?";

  private static final String
  ALL = "SELECT * FROM gioco";

  private static final String
  DELETE_ALL = "DELETE FROM gioco";

  private static String
  VOTES_AVARAGE = "SELECT AVG(votazione) FROM (gioco JOIN voto on gioco.id = voto.gioco) WHERE id = ?";

  private static final String
  ALL_GAME_REVIEWS = "SELECT * FROM recensione WHERE recensione.gioco = ? AND recensione.approvazione = 1";

  private  static final String
  ALREADY_VOTED = "SELECT COUNT(*) AS total FROM voto WHERE utente = ? and gioco = ?";

  public void insertGame(Gioco gioco) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(INSERT);
    ps.setString(1, gioco.getNome());
    ps.setString(2, gioco.getExp());
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public void deleteGame(Gioco gioco) throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE);
    ps.setString(1, gioco.getId());
    ResultSet rset = ps.executeUpdate();
    rset.close();
    ps.close();
  }
  public List<Gioco> allGames() throws SQLException{
    List<Gioco> all_games = ArrayList<Gioco>;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALL);
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      Gioco gioco = new Gioco(rset.getInt("id"), res.getString("nome"), res.getInt("exp"));
			all_games.add(gioco);
		}
    ps.close();
    rset.close();
    return all_games;
  }
  public void deleteAllGames() throws SQLException{
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(DELETE_ALL);
    ResultSet rset = ps.executeUpdate();
    ps.close();
    rset.close();
  }
  public float getVotesAvarege(Gioco gioco) throws SQLException{
    float votes_avarage;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(VOTES_AVARAGE);
    ps.setString(1, gioco.getId());
    ResultSet rset = ps.executeQuery();
    votes_avarage = rset.getFloat("AVG(votazione)");
    return votes_avarage;
  }
  public List<Recensione> allGamesReviews(Gioco gioco){
    List<Recensione> game_reviews = ArrayList<Recensione>;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALL_GAME_REVIEWS);
    ps.setString(1, gioco.getId());
    ResultSet rset = ps.executeQuery();
    while (rset.next()){
      Recensione recensione = new Recensione(rset.getInt("id"), res.getInt("approvazione"), res.getString("testo"), res.getInt("gioco"), res.getInt("utente"));
			game_reviews.add(recensione);
    }
    ps.close();
    rset.close();
    return game_reviews;
  }
  public boolean gameAlredyVotedByUser(Utente utente, Gioco gioco) throws SQLException{
    boolean already_voted = false;
    Connection connection = DB.openConnection();
    PreparedStatement ps = connection.prepareStatement(ALREADY_VOTED);
    ps.setString(1, utente.getId());
    ps.setString(2, gioco.getId());
    ResultSet rset = ps.executeQuery();
    if(rset.geInt("total") == 1){ already_voted = true; }
    ps.close();
    rset.close();
    return already_voted;
  }
}
