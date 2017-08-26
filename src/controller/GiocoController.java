package src.controller;

import java.util.ArrayList;
import java.sql.SQLException;

import src.model.dao.concrete.*;
import src.model.*;

/**
*Class which represents the controller of MVC pattern for the game
*/
public class GiocoController{
	private Gioco gioco;
	
	/**
	*Basic constructor
	*/
	public GiocoController(){}
	
	/**
	*Game constructor
	*
	*@param gioco game on which GiocoController will operate
	*/
	public GiocoController(Gioco gioco){		
		this.gioco = gioco;
	}
	
	/**
	*Method used to get the list of games
	*
	*@return ArrayList games' list
	*/
	public ArrayList<Gioco> listOfGames(){
		try{
			return new GiocoDao().allGames();
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
	
	/**
	*Method used to find a game by its name
	*
	*@param nome name of game to find
	*@return Gioco istance of game
	*/
	public Gioco findGame(String nome){
		try{
			return new GiocoDao().findGameByName(nome);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
	
	
	/**
	*Method used to get votes avarage of a game
	*
	*@return float avarage
	**/
	public float avgVote(){
		try{
			return new GiocoDao().getVotesAverage(gioco);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return -1;
	}
	
	/**
	*Method used to get the list of all reviews about a game
	*
	*@return ArrayList list of reviews
	*/
	public ArrayList<Recensione> allReviews(){
		try{
			return new GiocoDao().allGameReviews(gioco);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
}
