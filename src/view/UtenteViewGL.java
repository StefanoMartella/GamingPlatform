package view;

import controller.*;
import model.*;
import model.dao.concrete.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

import java.util.*;
import java.sql.*;

public class UtenteViewGL{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public UtenteViewGL(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	
	public void initialize(){
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(true);
		
		try{
		List<Gioco> gl = new GiocoDao().allGames();
		ArrayList<JButton> jl = new ArrayList<JButton>();
		for(Gioco g: gl){
				jl.add(new JButton(g.getNome()));
		}
		int i=0;
		for(JButton j: jl){
			j.setBounds(154, 25+35*i, 160, 30);
			panel_5.add(j);
			i++;
			j.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				panel_5.setVisible(false);
				new GiocoView(frmPiattaformaGaming, ut, new GiocoDao().findGameByName(j.getText()));
				}
				catch(SQLException exc){
			exc.printStackTrace();
		}
		}});
		}
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
	}

}