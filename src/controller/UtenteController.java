package src.controller;

import java.util.TreeMap;

import src.model.*;
import src.model.dao.concrete.*;
import java.sql.*;

/**
*Class which represents the controller of MVC pattern for the user
**/
public class UtenteController{
	Utente utente;
	Gioco gioco;
	
	/**
	*Basic constructor
	**/
	public UtenteController(){};
	
	/**
	*Constructor to set User
	*@param utente user
	*/
	public UtenteController(Utente utente){
		this.utente = utente;
	}
	
	/**
	*Constructor to set Game
	*@param gioco game
	*/
	public UtenteController(Gioco gioco){
		this.gioco = gioco;
	}
	
	/**
	*Full constructor
	*@param utente user of UtenteController
	*@param gioco game on which UtenteController will operate
	*/
	public UtenteController(Utente utente, Gioco gioco){
		this.utente=utente;
		this.gioco=gioco;
	}
	
	/**
	*Method used to play a game
	*/
	public void play(){
		try{
			new UtenteDao().play(utente,gioco);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		
	}
	
	
	/**
	*Method used to vote a game
	*@param voto vote to assign
	*@return String string information about voting
	*/
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
	
	
	/**
	*Method used to review a game
	*@param testoRecensione review's text
	*@return String string information about review
	*/
	public String review(String testoRecensione){
		try{
			if(new UtenteDao().reviewAlreadyMadeByUser(utente,gioco))
			return "Hai gia' scritto una recensione per questo gioco.";
		else{
		new UtenteDao().reviewGame(testoRecensione, utente, gioco);
		if(utente.getTipo().equals("moderatore")){
			new UtenteDao().approveReview(new RecensioneDao().findReviewByUserAndGame(utente, gioco));
			return "Recensione inserita!";
		}
		return "Recensione inviata, dovrete aspettare il consenso di un moderatore.";
		}
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
	
	
	/**
	*Method used to get user's timeline
	*@param username user's username
	*@return TreeMap user's timeline
	*/
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
