package src.controller;

import src.model.dao.concrete.*;
import src.model.*;
import java.sql.*;

/**
*Class which represents the controller of MVC pattern for the adminstrator
**/
public class AmministratoreController extends UtenteController{
	Utente utente_target;
	Gioco gioco;
	
	/**
	*Basic constructor
	**/
	public AmministratoreController(){}
	
	
	/**
	*Full constructor
	*@param utente_target AmministratoreController's user on which admin can operate
	*@param gioco AmministratoreController's game on which admin can operate
	**/
	public AmministratoreController(Utente utente_target, Gioco gioco){
		this.utente_target = utente_target;
		this.gioco = gioco;
	}
	
	/**
	*User constructor
	*@param utente_target AmministratoreController's user on which admin can operate
	**/
	public AmministratoreController(Utente utente_target){
		this.utente_target = utente_target;
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
		if(exp > 100)
			return "Un gioco puo' fornire al massimo 100 punti esperienza!";
		
		gioco = new Gioco(nome, exp);
		
		try{
			new GiocoDao().insertGame(gioco);
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
			new UtenteDao().deleteUser(utente_target);
			return "Utente eliminato con successo!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}