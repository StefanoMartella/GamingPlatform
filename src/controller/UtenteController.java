package controller;

import java.util.TreeMap;

import model.*;
import model.dao.concrete.*;
import java.sql.*;

public class UtenteController{
	Utente utente;
	Gioco gioco;

	public UtenteController(){};
	
	public UtenteController(Utente u, Gioco g){
		this.utente=u;
		this.gioco=g;
	}
	public void play(){
		try{
			new UtenteDao().play(utente,gioco);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		
	}
	
	public String vote(int voto){
		try{
			if(new UtenteDao().gameAlreadyVotedByUser(utente, gioco))
			return "Hai già votato questo gioco.";
		
			new UtenteDao().voteGame(voto,utente,gioco);
			return "Votazione andata a buon fine!";
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
	
	public String review(String testoRecensione){
		try{
			if(new UtenteDao().reviewAlreadyMadeByUser(utente,gioco))
			return "Hai già scritto una recensione per questo gioco.";
		
		new UtenteDao().reviewGame(testoRecensione, utente, gioco);
		return "Recensione inviata; dovrete aspettare il consenso di un moderatore.";
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
	
	public TreeMap<Integer, String> timeline(String username){
		try{
			Utente utente = new UtenteDao().findUserByUsername(username);
			return new UtenteDao().getTimeline(utente);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
