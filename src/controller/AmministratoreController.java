package src.controller;

import src.model.dao.concrete.*;
import src.model.*;
import java.sql.*;

/**
*Class which represents the controller of MVC pattern for the adminstrator
**/
public class AmministratoreController extends UtenteController{
	Utente amministratore;
	Utente utente;
	Gioco gioco;
	
	/**
	*Basic constructor
	**/
	public AmministratoreController(){}
	
	
	/**
	*Full constructor
	*@param amministratore AmministratoreController's adminstrator
	*@param utente AmministratoreController's user on which admin can operate
	*@param gioco AmministratoreController's game on which admin can operate
	**/
	public AmministratoreController(Utente amministratore, Utente utente, Gioco gioco){
		this.amministratore=amministratore;
		this.utente=utente;
		this.gioco=gioco;
	}
	
	/**
	*User constructor
	*@param utente AmministratoreController's user on which admin can operate
	**/
	public AmministratoreController(Utente utente){
		this.utente=utente;
	}
	
	/**
	*Game constructor
	*@param gioco AmministratoreController's game on which admin can operate
	**/
	public AmministratoreController(Gioco gioco){
		this.gioco=gioco;
	}
	
	
	/**
	*Method used to insert a new game
	*@param nome game's name
	*@param exp game's amount of exp
	*@return String information string about insertion
	**/
	public String insertGame(String nome, int exp){
		try{
			new GiocoDao().insertGame(new Gioco(nome, exp));
			return "Gioco inserito con successo!";
		}
		catch(SQLException e){
			return "Gioco gia' esistente.";
		}
	}
	
	/**
	*Method used to delete an existing game
	*@return String information string about cancellation
	**/
	public String deleteGame(){		
		try{
			new GiocoDao().deleteGame(gioco);
			return "Gioco eliminato con successo!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	*Method used to delete an existing user
	*@return String information string about cancellation
	**/
	public String deleteUser(){
		try{
			new UtenteDao().deleteUser(utente);
			return "Utente eliminato con successo!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}