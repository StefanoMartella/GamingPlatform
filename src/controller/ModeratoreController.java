package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class ModeratoreController extends UtenteController{ 
	// Eredita da UtenteController utente e gioco
	Utente utente_target; // oggetto che viene passato al metodo promote o demote.
	Recensione recensione;
	
	public void setUtenteTarget(Utente utente_target){ this.utente_target = utente_target; }
	public void setRecensione(Recensione recensione){ this.recensione = recensione; }
	public Utente getUtenteTarget(){ return this.utente_target; }
	public Recensione getRecensione(){ return this.recensione; }
	
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 5a2327551b76ccbc3913a22e3062c43a8bab43e9
