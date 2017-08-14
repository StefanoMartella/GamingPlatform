package src.controller;

import src.model.dao.concrete.*;
import src.model.*;
import java.sql.*;

/**
*Class which implements methods about user's mainteinance
**/
public class GestioneUtenza{
	
	/**
	*Method used to login into the platform
	*@param username user's username
	*@param password user's password
	*@return Utente istance of user
	**/
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
	
	/**
	*Method used to find a user by his username
	*@param username user's username
	*@return Utente istance of user
	**/
	public Utente findUser(String username){
		try{
			return new UtenteDao().findUserByUsername(username);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	} 
	
	
	/**
	*Method used to sign up into the platform
	*@param name user's name
	*@param surname user's surname
	*@param username user's username
	*@param mail user's email
	*@param password user's password
	*@param password2 confirm password
	*@return int information number about enrolment status
	**/
	public int signIn(String name, String surname, String username, String mail, String password, String password2){
		try{
			if(new UtenteDao().usernameAlreadyUsed(username)){ return 1;}
			if(new UtenteDao().emailAlreadyUsed(mail)){ return 2;}
		
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
