package controller;

import model.dao.concrete.*;
import model.*;
import java.sql.*;

public class GestioneUtenza{
	public Utente login(String username, String password){
		try{
			if(new UtenteDao().findUserByUsername(username) == null){ return null; }		
			
			Utente utente = new UtenteDao().findUserByUsername(username);
			//Mysql is case insensitive
			if ( !utente.getUsername().equals(username) ){
				return null;
			}
			if(password.equals(utente.getPassword())){
				return utente;
			}
			return null;
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	} 
	
	public Utente findUser(String username){
		try{
			return new UtenteDao().findUserByUsername(username);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	} 
	
	public int signIn(String name, String surname, String username, String mail, String password, String password2){
		try{
			if(new UtenteDao().usernameAlreadyUsed(username)){ return 1;}
			if(new UtenteDao().emailAlreadyUsed(username)){ return 2;}
		
			if(name.equals("") || surname.equals("") || username.equals("") || mail.equals("") || password.equals("") || password2.equals(""))
				return 3;
			if( !password.equals(password2) )
				return 4;
			Utente ut = new Utente(name,surname,username,mail,password);
		
			new UtenteDao().insertUser(ut);
			return 0;
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return -1;
	}
}
