package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class ModeratoreController extends UtenteController{ 
	// Eredita da UtenteController utente e gioco
	Utente utente_base; //o moderatore, oggetto che viene passato al metodo promote o demote.
	Recensione recensione;
	
	public void setUtenteBase(Utente utente_base){ this.utente_base = utente_base; }
	public void setRecensione(Recensione recensione){ this.recensione = recensione; }
	public Utente getUtenteBase(){ return this.utente_base; }
	public Recensione getRecensione(){ return this.recensione; }
	
	public String promote() throws SQLException{
		// Non serve controllare se l'utente esiste, filtraggio fatto dalla view
		new UtenteDao().promoteUser(utente_base);
		return "Promozione andata a buon fine.";
	}
	
	public String demote() throws SQLException{
		// Non serve controllare se l'utente esiste, filtraggio fatto dalla view
		new UtenteDao().demoteUser(utente_base);
		return "Retrocessione andata a buon fine.";
	}
	
	public String approve() throws SQLException{
		new UtenteDao().approveReview(recensione);
		return "Recensione approvata.";
	}
	
	public String disapprove() throws SQLException{
		new UtenteDao().approveReview(recensione);
		return "Recensione approvata.";
	}
}
