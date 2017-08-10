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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

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
		panel_9.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		panel_9.setVisible(true);
		
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
		for(Gioco g: gl){
				jl.add(new JLabel(g.getNome()));
		}
		int i=0;
		for(JLabel j: jl){
			j.setBounds(15,15+25*i,120,20);
			panel_9.add(j);
			i++;
			JButton selez = new JButton("ELIMINA");
			selez.setBounds(200,j.getY(),100,20);
			panel_9.add(selez);
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new AmministratoreController(new GiocoController().findGame(j.getText())).deleteGame());
							j.setVisible(false);
							selez.setVisible(false);
			}});
		}
	}
}