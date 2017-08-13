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
import net.miginfocom.swing.MigLayout;

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
		panel_9.setBounds(0, 0, 465, 300);
		frmPiattaformaGaming.getContentPane().add(panel_9);
		panel_9.setLayout(new MigLayout());
		panel_9.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("", "[]170[]", "[][]"));
		
		JButton btnHome = new JButton("Home");
		panel_9.add(btnHome, "pos 339px 250px, width 90, height 20");
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel_9.setVisible(false);
					new AmministratoreView(frmPiattaformaGaming, ut);
		}});
		
		JButton btnAG = new JButton("Inserisci");
		panel_9.add(btnAG, "pos 15px 250px, width 90, height 20");
		
		btnAG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel_9.setVisible(false);
					new AmministratoreViewAG(frmPiattaformaGaming, ut);
		}});
		
		List<Gioco> gl = new GiocoController().listOfGames();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		for(Gioco g: gl){
				jl.add(new JLabel("" + g.getNome()));
		}
		for(JLabel j: jl){
			ps.add(j);
			JButton selez = new JButton("Elimina");
			ps.add(selez, "wmin 150, wrap");
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
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setSize(460,240);
		panel_9.add(scroll, "pos 0px 0px, width 460, height 240");
	}
}