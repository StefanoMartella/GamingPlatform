package model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.sql.SQLException;

import model.dao.concrete.*;

public class Demo{
	public static void main(String[] args){
		Utente spiderman = new Utente(1, "Peter", "Parker", "Spiderman", "uomoragno@avengers.com", "ragnetto", "utente", 0, 25);
		Utente stefano = new Utente("Stefano", "Martella", "stefanmarte", "stefano.martella9614@gmail.com", "ciaociao");
		Gioco poker = new Gioco(1, "Poker", 30);
		
		try{
			List<Gioco> giochi = new GiocoDao().allGames();
			for(Gioco gioco : giochi){
				System.out.println(gioco);
		    }
		//System.out.println(new GiocoDao().gameAlredyVotedByUser(spiderman, poker));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}