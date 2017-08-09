package view;

import controller.*;
import model.*;

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

public class UtenteViewProfiloGaming{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public UtenteViewProfiloGaming(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	
	public void initialize(){
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(true);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBounds(10, 31, 114, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblLivello_1 = new JLabel("Livello");
		lblLivello_1.setBounds(10, 85, 114, 14);
		panel_1.add(lblLivello_1);
		
		JLabel lblLivello = new JLabel("Punti EXP");
		lblLivello.setBounds(10, 149, 114, 14);
		panel_1.add(lblLivello);
		
		JLabel lblTrofei = new JLabel("Trofei");
		lblTrofei.setBounds(10, 215, 114, 14);
		panel_1.add(lblTrofei);
		
		JLabel lblNewLabel_4 = new JLabel(ut.getUsername());
		lblNewLabel_4.setBounds(134, 31, 166, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel label = new JLabel(""+ut.getLivello());
		label.setBounds(134, 85, 166, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel(""+ut.getPuntiExp());
		label_1.setBounds(134, 149, 166, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Hai collezionato " + ut.getLivello() + " trofei!");
		label_2.setBounds(134, 215, 166, 14);
		panel_1.add(label_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(335, 227, 89, 23);
		panel_1.add(btnHome);
		
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ut.getTipo().equals("moderatore")){
					panel_1.setVisible(false);
					new ModeratoreView(frmPiattaformaGaming, ut);
				}
				else{
					panel_1.setVisible(false);
					new UtenteView(frmPiattaformaGaming, ut);
				}
		}});
	}
}