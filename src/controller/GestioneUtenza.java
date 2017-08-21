package src.controller;

import src.model.dao.concrete.*;
import src.model.*;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
*Class which implements methods about user's mainteinance
*/
public class GestioneUtenza{
	
	/**
	*Method used to login into the platform
	*
	*@param username user's username
	*@param password user's password
	*@return Utente istance of user
	*/
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
	*
	*@param username user's username
	*@return Utente istance of user
	*/
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
	*
	*@param name user's name
	*@param surname user's surname
	*@param username user's username
	*@param mail user's email
	*@param password user's password
	*@param password2 confirm password
	*@return int information number about enrolment status
	*/
	public int signIn(String name, String surname, String username, String mail, String password, String password2){
		
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
		
		try{
			if(name.equals("") || surname.equals("") || username.equals("") || mail.equals("") || password.equals("") || password2.equals(""))
				return 1;
			if(new UtenteDao().usernameAlreadyUsed(username))
				return 2;
			if(new UtenteDao().emailAlreadyUsed(mail))
				return 3;
			if(!matcher.find()) 
				return 4;
			if(password2.length() < 8)
				return 5;
			if(!password.equals(password2))
				return 6;
			Utente ut = new Utente(name,surname,username,mail,password);
		
			new UtenteDao().insertUser(ut);
			return 0;
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return -1;
	}
	
	/**
	*Method to update user's informations
	*
	*@param column value to be changed
	*@param newValue new value for the column
	*@param utente user that receives the update
	*/
	public String updateValue(String column, String newValue, Utente utente){
		
		try{
			if (column.equals("nome")){
				new UtenteDao().updateUser(column, newValue, utente);
				return "Nome aggiornato!";
			}
			if (column.equals("cognome")){
				new UtenteDao().updateUser(column, newValue, utente);
				return "Cognome aggiornato!";
			}
			if (column.equals("username")){
				if(new UtenteDao().usernameAlreadyUsed(newValue))
					return "Username gia' in uso!";
				new UtenteDao().updateUser(column, newValue, utente);
				return "Username aggiornato!";
			}
			if (column.equals("email")){
				Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			
				Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(newValue);

				
				if(new UtenteDao().emailAlreadyUsed(newValue))
					return "Email gia' in uso!";
				if(!matcher.find())
					return "Email non valida!";
				new UtenteDao().updateUser(column, newValue, utente);
				return "Email aggiornata!";
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	*Method to update user's password
	*
	*@param column value to be changed
	*@param nuova_password new password
	*@param conferma_password password check
	*@param utente user that receives the update
	*/
	public String updatePassword(String nuova_password, String conferma_password, Utente utente){
		if(nuova_password.length() < 8)
			return "La password deve essere di minimo 8 caratteri!";
		if(!nuova_password.equals(conferma_password))
			return "Le due password non coincidono!";
		try{
			new UtenteDao().updateUser("password", nuova_password, utente);
			return "Password aggiornata!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
