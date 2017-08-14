package src.controller;

import src.model.dao.concrete.*;
import src.model.*;
import java.sql.*;

public class AmministratoreController extends UtenteController{
	Utente amministratore;
	Utente utente;
	Gioco gioco;
	
	public AmministratoreController(){}
	
	public AmministratoreController(Utente amministratore, Utente utente, Gioco gioco){
		this.amministratore=amministratore;
		this.utente=utente;
		this.gioco=gioco;
	}
	public AmministratoreController(Utente utente){
		this.utente=utente;
	}
	public AmministratoreController(Gioco gioco){
		this.gioco=gioco;
	}
	
	public String insertGame(String nome, int exp){
		try{
			new GiocoDao().insertGame(new Gioco(nome, exp));
			return "Gioco inserito con successo!";
		}
		catch(SQLException e){
			return "Gioco gia' esistente.";
		}
	}
	
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