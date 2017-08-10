package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;
import java.util.*;

public class ModeratoreController extends UtenteController{ 
	// Utente utente;
	// Gioco gioco;
	Utente utente_target;
	Recensione recensione;
	
	public ModeratoreController(){
		
	}
	
	public ModeratoreController(Utente utente, Gioco gioco){
		super();
	}
	
	public ModeratoreController(Utente utente, Gioco gioco, Utente utente_target, Recensione recensione){
		super(utente,gioco);
		this.utente_target=utente_target;
		this.recensione=recensione;
	}
	public ModeratoreController(Utente utente_target){
		this.utente_target=utente_target;
	}
	
	public String promote(){
		try{
			if( utente_target.getTipo().equals("amministratore") )
				return "Promozione non riuscita, l'utente è un ammimistratore";

			new UtenteDao().promoteUser(utente_target);
			return "Promozione andata a buon fine.";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	} 
	
	public String demote(){
		try{
			if( utente_target.getTipo().equals("amministratore") )
				return "Retrocessione non riuscita, l'utente è un amministratore";

			new UtenteDao().demoteUser(utente_target);
			return "Retrocessione andata a buon fine.";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String approve(){
		try{
			new UtenteDao().approveReview(recensione);
			return "Recensione approvata.";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String disapprove(){
		try{
			new UtenteDao().approveReview(recensione);
			return "Recensione approvata.";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Utente> usersList(){
		try{
			return new UtenteDao().allUsers();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}