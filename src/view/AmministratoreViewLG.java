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
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;

import java.util.*;
import java.sql.*;

public class AmministratoreViewLG{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public AmministratoreViewLG(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	
	public void initialize(){
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(0, 0, 450, 300);
		frmPiattaformaGaming.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		panel_9.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new GridLayout(0,2, 0, 20));
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(335, 227, 89, 23);
		panel_9.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel_9.setVisible(false);
					new AmministratoreView(frmPiattaformaGaming, ut);
		}});
		
		JButton btnAG = new JButton("INSERISCI");
		btnAG.setBounds(15, 227, 130, 23);
		panel_9.add(btnAG);
		
		btnAG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel_9.setVisible(false);
					new AmministratoreViewAG(frmPiattaformaGaming, ut);
		}});
		
		List<Gioco> gl = new GiocoController().listOfGames();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		int i=0;
		for(Gioco g: gl){
				jl.add(new JLabel(g.getNome()));
		}
		for(JLabel j: jl){
			ps.add(j);
			i++;
			JButton selez = new JButton("ELIMINA");
			selez.setSize(180,20);
			ps.add(selez);
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new AmministratoreController(new GiocoController().findGame(j.getText())).deleteGame());
							j.setVisible(false);
							selez.setVisible(false);
			}});
		}
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setSize(430,210);
		panel_9.add(scroll);
	}
}