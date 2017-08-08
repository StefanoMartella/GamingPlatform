package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class GestioneUtenza{
	public Utente login(String username, String password) throws SQLException{
		
		if(new UtenteDao().findUserByUsername(username) == null){ return null; }		
			
		Utente utente = new UtenteDao().findUserByUsername(username);
		if(password.equals(utente.getPassword())){
			return utente;
		}
		return null;
	} 
}
