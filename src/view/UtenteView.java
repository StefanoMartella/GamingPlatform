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


public class UtenteView {
	
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public UtenteView(JFrame frame, Utente ut) {
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}

	public void initialize() {
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 434, 300);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(true);
		
		JLabel lblBenvenutoUtente = new JLabel();
		lblBenvenutoUtente.setForeground(Color.RED);
		Font font = new Font("SEGOE UI Light", Font.BOLD,16);
		lblBenvenutoUtente.setFont(font);
		lblBenvenutoUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoUtente.setBounds(50, 17, 350, 69);
		lblBenvenutoUtente.setText("BENVENUTO " + ut.getUsername());
		panel_2.add(lblBenvenutoUtente);
		
		JButton btnProfiloPersonale = new JButton("Profilo Personale");
		btnProfiloPersonale.setBounds(145, 86, 160, 30);
		panel_2.add(btnProfiloPersonale);
		
		JButton btnProfiloGaming = new JButton("Profilo Gaming");
		btnProfiloGaming.setBounds(145, 132, 160, 30);
		panel_2.add(btnProfiloGaming);
		
		JButton btnVaiAiGiochi = new JButton("Vai ai Giochi!");
		btnVaiAiGiochi.setBounds(145, 178, 160, 30);
		panel_2.add(btnVaiAiGiochi);
		
		
		JButton btnLogout = new JButton("Esci");
		btnLogout.setBounds(145,224,160,30);
		panel_2.add(btnLogout);
		
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ut = null;
				panel_2.setVisible(false);
				new Login();
		}});
		
		btnProfiloPersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewProfiloPers(frmPiattaformaGaming,ut);
		}});
		
		btnProfiloGaming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewProfiloGaming(frmPiattaformaGaming,ut);
		}});
		btnVaiAiGiochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewGL(frmPiattaformaGaming,ut);
		}});
	}
}
