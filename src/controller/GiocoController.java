package controller;

import model.dao.concrete.*;
import model.*;
import java.util.ArrayList;
import java.sql.*;

public class GiocoController{
	Utente utente;
	Gioco gioco;
	
	public GiocoController(){
		
	}
	
	public GiocoController(Utente u, Gioco g){
		this.gioco=g;
		this.utente=u;
	}
	
	public ArrayList<Gioco> listOfGames(){
		try{
			return new GiocoDao().allGames();
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
	
	public Gioco findGame(String nome){
		try{
			return new GiocoDao().findGameByName(nome);
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		return null;
	}
}