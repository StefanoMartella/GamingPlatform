package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class ModeratoreController extends UtenteController{ 
	// Eredita da UtenteController utente e gioco
	Utente utente_target; // oggetto che viene passato al metodo promote o demote.
	Recensione recensione;
	
	public ModeratoreController(){
		super();
	}
	
	public ModeratoreController(Utente u, Gioco g, Utente u_T, Recensione rec){
		super(u,g);
		this.utente_target=u_T;
		this.recensione=rec;
	}
	
	public String promote() throws SQLException{
		// Non serve controllare se l'utente esiste, filtraggio fatto dalla view
		if( utente_target.getTipo().equals("amministratore") )
			return "Promozione non riuscita, l'utente è un ammimistratore";

		new UtenteDao().promoteUser(utente_target);
		return "Promozione andata a buon fine.";
	}
	
	public String demote() throws SQLException{
		// Non serve controllare se l'utente esiste, filtraggio fatto dalla view
		if( utente_target.getTipo().equals("amministratore") )
			return "Retrocessione non riuscita, l'utente è un amministratore";

		new UtenteDao().demoteUser(utente_target);
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