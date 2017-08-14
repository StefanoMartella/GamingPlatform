package src.view.utente;

import src.controller.*;
import src.model.*;
import src.view.moderatore.*;
import src.view.gioco.*;


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
import net.miginfocom.swing.MigLayout;
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
		panel_5.setBounds(0, 0, 465, 300);
		frmPiattaformaGaming.getContentPane().add(panel_5);
		panel_5.setLayout(new MigLayout());
		panel_5.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("center center, wrap, gapy 5"));
		
		List<Gioco> gl = new GiocoController().listOfGames();
		ArrayList<JButton> jl = new ArrayList<JButton>();
		for(Gioco g: gl){
				jl.add(new JButton(g.getNome()));
		}
		for(JButton j: jl){
			ps.add(j, "width 420");
			j.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.setVisible(false);
				new GiocoView(frmPiattaformaGaming, ut, new GiocoController().findGame(j.getText()));
			}});
		}
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		panel_5.add(scroll, "pos 0px 0px, width 460, height 240");
		
		
		JButton btnHome = new JButton("Home");
		panel_5.add(btnHome, "pos 180px 250px, width 90, height 25");

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

