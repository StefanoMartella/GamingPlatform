package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class AmministratoreController extends UtenteController{
	Utente amministratore;
	Utente utente;
	Gioco gioco;
	
	public void setModeratore(Utente amministratore){ this.amministratore= amministratore; }
	public void setUtente(Utente utente){ this.utente = utente; }
	public void setGioco(Gioco gioco){ this.gioco = gioco; }
	public Utente getAmministratore(){ return this.amministratore; }
	public Utente getUtente(){ return this.utente; }
	public Gioco getGioco(){ return this.gioco; }
	
	public String insertGame(String nome, int exp) throws SQLException{
		new GiocoDao().insertGame(new Gioco(nome, exp));
		return "Gioco inserito con successo!";
	}
	
	public String deleteGame() throws SQLException{		
		new GiocoDao().deleteGame(gioco);
		return "Gioco eliminato con successo!";
	}
	
	public String deleteUser() throws SQLException{
		new UtenteDao().deleteUser(utente);
		return "Utente eliminato con successo!";
	}
}
