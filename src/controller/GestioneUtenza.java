package src.controller;

import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import src.model.dao.concrete.*;
import src.model.*;

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
	public Utente logIn(String username, String password){
		try{
			if( new UtenteDao().findUserByUsername(username) == null )
				return null;
			
			Utente utente = new UtenteDao().findUserByUsername(username);
			
			//Mysql is case insensitive
			if ( !utente.getUsername().equals(username) )
				return null;
			if( password.equals(utente.getPassword()) )
				return utente;
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
	public int signUp(String name, String surname, String username, String mail, String password, String password2){
		
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[\\w!#$%&â€™*+/=?`{|}~^-]+(?:\\.[\\w!#$%&â€™*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
		
		try{
			if( name.trim().isEmpty() || surname.trim().isEmpty() || username.trim().isEmpty() || mail.trim().isEmpty() || password.trim().isEmpty() || password2.trim().isEmpty())
				return 1;
			
			if( name.length() > 30 || surname.length() > 30 || surname.length() > 30 || mail.length() > 45 || password.length() > 30 )
				return 2;
			
			if( new UtenteDao().usernameAlreadyUsed(username) )
				return 3;
			
			if( new UtenteDao().emailAlreadyUsed(mail) )
				return 4;
			
			if( !matcher.find() ) 
				return 5;
			
			if( password2.length() < 8 )
				return 6;
			
			if( !password.equals(password2) )
				return 7;
			
			Utente utente = new Utente(name,surname,username,mail,password);
		
			new UtenteDao().insertUser(utente);
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
	*@return String message to be displayed
	*/
	public String updateValue(String column, String newValue, Utente utente){
		
		try{
			switch( column ){
				
				case "nome":		if( newValue.trim().isEmpty() )
								return "Il nome non puo' essere vuoto!";
							
							if( newValue.length() > 30 )
								return "Il nome non deve superare i 30 caratteri!";
							
							new UtenteDao().updateUser(column, newValue, utente);
							utente.setNome(newValue);
							return "Nome aggiornato!";
								
				case "cognome":		if( newValue.trim().isEmpty() )
								return "Il nome non puo' essere vuoto!";
							
							if( newValue.length() > 30 )
								return "Il cognome non deve superare i 30 caratteri!";
							
							new UtenteDao().updateUser(column, newValue, utente);
							utente.setCognome(newValue);
							return "Cognome aggiornato!";
								
				case "username":	if( newValue.trim().isEmpty() )
								return "Lo username non puo' essere vuoto!";
							
							if( newValue.length() > 30 )
								return "Lo username non deve superare i 30 caratteri!";
							
							if(new UtenteDao().usernameAlreadyUsed(newValue))
								return "Username gia' in uso!";
							
							new UtenteDao().updateUser(column, newValue, utente);
							utente.setUsername(newValue);
							return "Username aggiornato!";
									
				case "email":		if( newValue.trim().isEmpty() )
								return "L'email non puo' essere vuota!";
							
							if( newValue.length() > 45 )
								return "L'email non deve superare i 45 caratteri!";
							
							Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[\\w!#$%&â€™*+/=?`{|}~^-]+(?:\\.[\\w!#$%&â€™*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
							Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(newValue);
									
							if( new UtenteDao().emailAlreadyUsed(newValue) )
								return "Email gia' in uso!";
							
							if( !matcher.find() )
								return "Email non valida!";
							
							new UtenteDao().updateUser(column, newValue, utente);
							utente.setEmail(newValue);
							return "Email aggiornata!";

			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	//This is an example of overload.
	
	/**
	*Method to update user's password
	*
	*@param column password column
	*@param nuova_password new password
	*@param conferma_password password check
	*@param utente user that receives the update
	*@return String message to be displayed
	*/
	public String updateValue(String column, String nuova_password, String conferma_password, Utente utente){
		if( nuova_password.trim().isEmpty() )
			return "La password non puo' essere vuota!";
		
		if( nuova_password.length() > 30 )
			return "Lo password non deve superare i 30 caratteri!";
		
		if( nuova_password.length() < 8 )
			return "La password deve essere di minimo 8 caratteri!";
		
		if( !nuova_password.equals(conferma_password) )
			return "Le due password non coincidono!";
		
		try{
			new UtenteDao().updateUser(column, nuova_password, utente);
			utente.setPassword(nuova_password);
			return "Password aggiornata!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
