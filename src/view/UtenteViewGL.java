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
import javax.swing.JScrollPane;
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
		
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(335, 227, 89, 23);
		panel_5.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ut.getTipo().equals("moderatore")){
					panel_5.setVisible(false);
					new ModeratoreView(frmPiattaformaGaming, ut);
				}
				else{
					panel_5.setVisible(false);
					new UtenteView(frmPiattaformaGaming, ut);
				}
		}});
		
		List<Gioco> gl = new GiocoController().listOfGames();
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
				panel_5.setVisible(false);
				new GiocoView(frmPiattaformaGaming, ut, new GiocoController().findGame(j.getText()));
		}});

		}
	}

}