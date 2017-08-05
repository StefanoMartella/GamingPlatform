package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB{
  private static String URL = "jdbc:mysql://127.0.0.1:3306/";
  private static String DRIVER = "com.mysql.jdbc.Driver";
  private static String USER = "gaming";
  private static String PASSWORD = "gaming";

  public static Connection openConnection(){
    try {
			Class.forName(DRIVER).newInstance();
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			System.err.println("Impossibile salvare i dati! Il database non rispondono!");
		}
		return null;
  }
}