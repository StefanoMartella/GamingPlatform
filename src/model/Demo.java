package model;

import model.dao.concrete.*;
import java.util.*;
import java.sql.*;

public class Demo{
	public static void main(String[] args){
		Utente spiderman = new Utente(1, "Peter", "Parker", "Spiderman", "uomoragno@avengers.com", "ragnetto", "utente", 0 ,25);
		Utente ironman = new Utente(2, "Tony", "Stark", "IronMan", "starkcorporation@avengers.com", "avengers", "amministratore", 3, 100);
		Gioco poker = new Gioco(1, "Poker", 30);
		Recensione recensione = new Recensione (4, false, "Recensione del signor Tony", 2, 2);
		
		try{
			//System.out.println(new GiocoDao().gameAlredyVotedByUser(spiderman, poker));
			
			TreeMap<Integer, String> timeline = new UtenteDao().getTimeline(ironman);
			for(Map.Entry<Integer, String> entry : timeline.entrySet()){
				System.out.print(entry.getKey() + " - ");
				System.out.println(entry.getValue());			
			}
			
			//new UtenteDao().approveReview(recensione);
			
			//new UtenteDao().reviewGame("Bellisssimooo", poker, ironman);
			
			/*List<Utente> utenti = new UtenteDao().allUsers();
			for(Utente utente : utenti){
			System.out.println(utente.getNome());}*/
			
			//System.out.println(new GiocoDao().getVotesAverage(poker));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}