package src.controller;

import src.model.dao.concrete.*;
import src.model.*;
import java.sql.*;
import java.util.*;
/**
*Class which represents the controller of MVC pattern for the moderator
**/
public class ModeratoreController extends UtenteController{ 
	// Utente utente;
	// Gioco gioco;
	Utente utente_target;
	Recensione recensione;
	
	
	/**
	*Basic constructor
	*/
	public ModeratoreController(){
		
	}
	
	/**
	*Constructor which calls UserController constructor
	*@param utente moderator user
	*@param gioco game on which ModeratoreController will operate
	*/
	public ModeratoreController(Utente utente, Gioco gioco){
		super();
	}
	
	/**
	*Full constructor
	*@param utente user
	*@param gioco game on which ModeratoreController will operate
	*@param utente_target user on which ModeratoreController will operate
	*@param recensione review on which ModeratoreController will operate
	*/
	public ModeratoreController(Utente utente, Gioco gioco, Utente utente_target, Recensione recensione){
		super(utente,gioco);
		this.utente_target=utente_target;
		this.recensione=recensione;
	}
	
	/**
	*Utente contructor
	*@param utente_target user on which ModeratoreController will operate
	*/
	public ModeratoreController(Utente utente_target){
		this.utente_target=utente_target;
	}
	
	/**
	*Method which promotes a simple user to a moderator
	*@return String string information about promoting
	*/
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
	
	/**
	*Method which demotes a moderator to a simple user
	*@return String string information about demoting
	*/
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
	
	
	/**
	*Method which approves a review made by a simple user
	*@param recensione review that has to be approved
	*@return String string information about approval
	*/
	public String approve(Recensione recensione){
		try{
			new UtenteDao().approveReview(recensione);
			return "Recensione approvata.";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	*Method which disapproves a review made by a simple user
	*@param recensione review that has to be disapproved
	*@return String string information about disapproval
	*/
	public String disapprove(Recensione recensione){
		try{
			new UtenteDao().approveReview(recensione);
			return "Recensione respinta.";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* Method which gives the users' list
	*@return ArrayList list of users
	*/
	public ArrayList<Utente> usersList(){
		try{
			return new UtenteDao().allUsers();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* Method which gives the reviews' list
	*@return ArrayList list of games
	*/
	public ArrayList<Recensione> reviewsList(){
		try{
			return new RecensioneDao().allReviewsNotApproved();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Recensione findReview(int id){
		try{
			return new RecensioneDao().findReviewById(id);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}