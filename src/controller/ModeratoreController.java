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
	Recensione recensione;
	
	
	/**
	*Basic constructor
	*/
	public ModeratoreController(){}
	
	/**
	*Constructor to set User
	*@param utente user
	*/
	public ModeratoreController(Utente utente){
		this.utente = utente;
	}
	
	/**
	*Constructor to set Game
	*@param gioco game
	*/
	public ModeratoreController(Gioco gioco){
		this.gioco = gioco;
	}
	
	/**
	*Constructor to set Review
	*@param recensione Review
	*/
	public ModeratoreController(Recensione recensione){
		this.recensione = recensione;
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
	*@param recensione review on which ModeratoreController will operate
	*/
	public ModeratoreController(Utente utente, Gioco gioco, Recensione recensione){
		super(utente,gioco);
		this.recensione=recensione;
	}
	
	/**
	*Method which promotes a simple user to a moderator
	*@return String string information about promoting
	*/
	public String promote(){
		try{
			new UtenteDao().promoteUser(utente);
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
			new UtenteDao().demoteUser(utente);
			return "Retrocessione andata a buon fine.";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	*Method which approves a review made by a simple user
	*@return String string information about approval
	*/
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
	
	/**
	*Method which disapproves a review made by a simple user
	*@return String string information about disapproval
	*/
	public String disapprove(){
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
