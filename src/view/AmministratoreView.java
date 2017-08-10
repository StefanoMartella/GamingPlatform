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


public class AmministratoreView {
	
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public AmministratoreView(JFrame frame, Utente ut){
		this.frmPiattaformaGaming = frame;
		this.ut=ut;
		initialize();
	}
	
	public void initialize(){
		
		JPanel panel_0 = new JPanel();
		panel_0.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_0);
		panel_0.setLayout(null);
		panel_0.setVisible(true);
		
		JLabel lblBenvenutoUtente = new JLabel();
		lblBenvenutoUtente.setForeground(Color.RED);
		Font font = new Font("SEGOE UI Light", Font.BOLD,16);
		lblBenvenutoUtente.setFont(font);
		lblBenvenutoUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoUtente.setBounds(100, 8, 250, 30);
		lblBenvenutoUtente.setText("BENVENUTO [A]" + ut.getUsername());
		panel_0.add(lblBenvenutoUtente);
		
		JButton btnProfiloPersonale = new JButton("PROFILO PERSONALE");
		btnProfiloPersonale.setBounds(145, 50, 160, 30);
		panel_0.add(btnProfiloPersonale);
		
		JButton btnLG = new JButton("LISTA GIOCHI");
		btnLG.setBounds(145, 90, 160, 30);
		panel_0.add(btnLG);
		
		JButton btnLU = new JButton("LISTA UTENTI");
		btnLU.setBounds(145, 130, 160, 30);
		panel_0.add(btnLU);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBounds(145,170,160,30);
		panel_0.add(btnLogout);
		
		btnProfiloPersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				new UtenteViewProfiloPers(frmPiattaformaGaming,ut);
		}});
		
		btnLG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				new AmministratoreViewLG(frmPiattaformaGaming,ut);
		}});
		
		btnLU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				new AmministratoreViewLU(frmPiattaformaGaming,ut);
		}});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ut = null;
				panel_0.setVisible(false);
				new Login();
		}});
	}
}