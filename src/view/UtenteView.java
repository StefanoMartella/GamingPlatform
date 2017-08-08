package view;

import controller.*;
import model.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

	private JFrame frmPiattaformaGaming;
	private JTextField textField;
	private JPasswordField psswrdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UtenteView window = new UtenteView();
					window.frmPiattaformaGaming.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UtenteView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPiattaformaGaming = new JFrame();
		frmPiattaformaGaming.setTitle("Piattaforma gaming");
		frmPiattaformaGaming.setBounds(100, 100, 450, 300);
		frmPiattaformaGaming.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPiattaformaGaming.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(56, 41, 99, 14);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(56, 125, 99, 14);
		panel.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(138, 38, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		psswrdField = new JPasswordField();
		psswrdField.setColumns(10);
		psswrdField.setBounds(138, 122, 86, 20);
		psswrdField.setEchoChar('*'); 
		panel.add(psswrdField);
		
		JLabel lblNewLabel_1 = new JLabel("Sei nuovo?");
		lblNewLabel_1.setBounds(338, 52, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Registrati!");
		lblNewLabel_2.setBounds(338, 77, 86, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("SIGN-IN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(324, 102, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnLogin = new JButton("LOGIN");
		
		btnLogin.setBounds(138, 150, 89, 23);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
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
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(134, 31, 166, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel label = new JLabel("New label");
		label.setBounds(134, 85, 166, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(134, 149, 166, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(134, 215, 166, 14);
		panel_1.add(label_2);
		
		JButton btnHome = new JButton("HOME");
		btnHome.setBounds(335, 227, 89, 23);
		panel_1.add(btnHome);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		
		JLabel lblBenvenutoUtente = new JLabel();
		lblBenvenutoUtente.setForeground(Color.RED);
		Font font = new Font("SEGOE UI Light", Font.BOLD,16);
		lblBenvenutoUtente.setFont(font);
		lblBenvenutoUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoUtente.setBounds(133, 35, 179, 69);
		panel_2.add(lblBenvenutoUtente);
		
		JButton btnProfiloPersonale = new JButton("PROFILO PERSONALE");
		btnProfiloPersonale.setBounds(52, 182, 160, 23);
		panel_2.add(btnProfiloPersonale);
		
		JButton btnProfiloGaming = new JButton("PROFILO GAMING");
		btnProfiloGaming.setBounds(256, 182, 147, 23);
		panel_2.add(btnProfiloGaming);
		
		JButton btnVaiAiGiochi = new JButton("VAI AI GIOCHI!");
		btnVaiAiGiochi.setBounds(154, 227, 147, 23);
		panel_2.add(btnVaiAiGiochi);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_2.setVisible(true);
				try{
				Utente ut = new GestioneUtenza().login(textField.getText(), new String(psswrdField.getPassword()));
				lblBenvenutoUtente.setText("BENVENUTO " + ut.getUsername());
				}
				catch(SQLException exc){
			exc.printStackTrace();
		}
				
		}});
	}
}
