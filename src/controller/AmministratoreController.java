package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class AmministratoreController extends UtenteController{
	Utente amministratore;
	Utente utente;
	Gioco gioco;
	
	public AmministratoreController(Utente amm, Utente ut, Gioco g){
		this.amministratore=amm;
		this.utente=ut;
		this.gioco=g;
	}
	
	public String insertGame(String nome, int exp) throws SQLException{
		new GiocoDao().insertGame(new Gioco(nome, exp));
		return "Gioco inserito con successo!";
	}
	
	public String deleteGame() throws SQLException{		
		new GiocoDao().deleteGame(gioco);
		return "Gioco eliminato con successo!";
	}
	
	public String deleteUser() throws SQLException{
		new UtenteDao().deleteUser(utente);
		return "Utente eliminato con successo!";
	}
}