package src.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*Class which has the aim to enstablish a connection with the database
**/
public class DB{
  private static String URL = "jdbc:mysql://localhost/Gaming?autoReconnect=true&useSSL=false";
  private static String DRIVER = "com.mysql.jdbc.Driver";
  private static String USER = "gaming";
  private static String PASSWORD = "gaming";

  
  /**
  *Method used to enstablish a connection with database
  *
  *@return Connection database connection
  **/
  public static Connection openConnection(){
    try {
	Class.forName(DRIVER).newInstance();
	Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	return connection;
    } 
    catch (SQLException e) {
	e.printStackTrace();
    } catch (Exception ex){
	System.err.println("Impossibile salvare/caricare i dati! Il database non risponde!");
    }
    return null;
  }
}
