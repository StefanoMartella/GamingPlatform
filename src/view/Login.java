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


public class Login {

	private static JFrame frmPiattaformaGaming;
	private JTextField textField;
	private JPasswordField psswrdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login(frmPiattaformaGaming);
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
	public Login() {
		initialize();
	}
	public Login(JFrame frm){
		this.frmPiattaformaGaming=frm;
		frmPiattaformaGaming = new JFrame();
		frmPiattaformaGaming.setTitle("Piattaforma gaming");
		frmPiattaformaGaming.setBounds(100, 100, 450, 300);
		frmPiattaformaGaming.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPiattaformaGaming.getContentPane().setLayout(null);
		frmPiattaformaGaming.setResizable(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		
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
		
		JButton btnSignIn = new JButton("SIGN-IN");
		btnSignIn.setBounds(324, 102, 89, 23);
		panel.add(btnSignIn);
		
		JButton btnLogin = new JButton("LOGIN");
		
		btnLogin.setBounds(138, 150, 89, 23);
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Utente ut = new GestioneUtenza().login(textField.getText(), new String(psswrdField.getPassword()));
				if(ut==null)
				{
					JOptionPane.showMessageDialog(frmPiattaformaGaming, "Username o password errati", "Login error", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					psswrdField.setText("");
				}
				else{
					if(ut.getTipo().equals("utente")){
					panel.setVisible(false);
					new UtenteView(frmPiattaformaGaming, ut);
					}
					if(ut.getTipo().equals("moderatore")){
						
					}
					if(ut.getTipo().equals("amministratore")){
						
					}
				
				}
		}
			});
		
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(false);
				new SignIn(frmPiattaformaGaming);
			}
		});
		
	}
}