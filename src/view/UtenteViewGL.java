package view;

import controller.*;
import model.*;


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
import java.awt.GridLayout;
import java.awt.Dimension;

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
		panel_5.setBounds(0, 0, 450, 261);
		frmPiattaformaGaming.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		panel_5.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new GridLayout(0,1,0,20));
		
		List<Gioco> gl = new GiocoController().listOfGames();
		ArrayList<JButton> jl = new ArrayList<JButton>();
		for(Gioco g: gl){
				jl.add(new JButton(g.getNome()));
		}
		for(JButton j: jl){
			j.setPreferredSize(new Dimension(100, 10));
			ps.add(j);
			j.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.setVisible(false);
				new GiocoView(frmPiattaformaGaming, ut, new GiocoController().findGame(j.getText()));
		}});
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setSize(450,235);
		panel_5.add(scroll);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(339, 238, 89, 23);
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
		}
	}

}