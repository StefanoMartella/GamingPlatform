package controller;

import java.util.TreeMap;

import model.*;
import model.dao.concrete.*;
import java.sql.*;

public class UtenteController{
	Utente utente;
	Gioco gioco;

	public UtenteController(){};
	
	public UtenteController(Utente utente, Gioco gioco){
		this.utente=utente;
		this.gioco=gioco;
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
			return "Hai gia' votato questo gioco.";
		
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
			return "Hai gia' scritto una recensione per questo gioco.";
		else{
		new UtenteDao().reviewGame(testoRecensione, utente, gioco);
		if(utente.getTipo().equals("moderatore"))
			return "Recensione inserita!";
		return "Recensione inviata, dovrete aspettare il consenso di un moderatore.";
		}
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
