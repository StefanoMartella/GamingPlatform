package controller;

import model.*;
import model.dao.concrete.*;
import java.sql.*;

public class UtenteController{
	Utente utente;
	Gioco gioco;

	public UtenteController(){};
	
<<<<<<< HEAD
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
=======
	public void setUtente(Utente utente){ this.utente = utente; }
	public void setGioco(Gioco gioco){ this.gioco = gioco; }
	public Utente getUtente(){ return this.utente; }
	public Gioco getGioco(){ return this.gioco; }
>>>>>>> 5a2327551b76ccbc3913a22e3062c43a8bab43e9
	
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
}
