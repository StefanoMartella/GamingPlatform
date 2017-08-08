package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class UtenteController{
	Utente utente;
	Gioco gioco;
	
	public void setUtente(Utente utente){ this.utente = utente; };
	public void setGioco(Gioco gioco){ this.gioco = gioco; };
	
	public String vote(int voto) throws SQLException{
		if(new UtenteDao().gameAlreadyVotedByUser(utente, gioco))
			return "Hai già votato questo gioco.";
		
		new UtenteDao().voteGame(voto,utente,gioco);
		return "Votazione andata a buon fine!";
	}
	
	public String review(String testoRecensione) throws SQLException{
		if(new UtenteDao().reviewAlreadyMadeByUser(utente,gioco))
			return "Hai già scritto una recensione per questo gioco.";
		
		new UtenteDao().reviewGame(testoRecensione, utente, gioco);
		return "Recensione inviata; dovrete aspettare il consenso di un moderatore.";
	}
}
