package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class UtenteController{
	Utente utente;
	Gioco gioco;
	
	public void setUtente(Utente utente){this.utente = utente;};
	public void setGioco(Gioco gioco){this.gioco = gioco;};
	
	public String vote(int voto){
		try{
			if(new UtenteDao().gameAlreadyVotedByUser(utente, gioco))
				return "Hai già votato questo gioco.";
			new UtenteDao().voteGame(vote,utente,gioco);
			return "Votazione andata a buon fine!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return "Errore nella votazione";
		}
	
	public String review(String text){
		try{
			if(new UtenteDao().reviewAlreadyMadeByUser(utente,gioco))
				return "Hai già scritto una recensione per questo gioco."
			new UtenteDao().reviewGame(text, utente, gioco);
			return "Recensione inviata; dovrete aspettare il consenso di un moderatore."
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return "Errore nella recensione";
}
