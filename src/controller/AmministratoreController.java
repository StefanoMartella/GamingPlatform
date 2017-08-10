package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class AmministratoreController extends UtenteController{
	Utente amministratore;
	Utente utente;
	Gioco gioco;
	
	public AmministratoreController(Utente amministratore, Utente utente, Gioco gioco){
		this.amministratore=amministratore;
		this.utente=utente;
		this.gioco=gioco;
	}
	
	public String insertGame(String nome, int exp){
		try{
			new GiocoDao().insertGame(new Gioco(nome, exp));
			return "Gioco inserito con successo!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
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