package src.view;

import src.controller.*;
import src.model.*;

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

/**
*Class which represents the view of MVC pattern of sign-up
*/
public class SignUp{
	
	JFrame frmPiattaformaGaming;
	
	public SignUp(JFrame frm){
		this.frmPiattaformaGaming = frm;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		panel_3.setVisible(true);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(55, 28, 173, 14);
		panel_3.add(lblName);
		
				
		JLabel lblCognme = new JLabel("Cognome");
		lblCognme.setBounds(55, 58, 173, 14);
		panel_3.add(lblCognme);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(55, 88, 173, 14);
		panel_3.add(lblUsername);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(55, 118, 173, 14);
		panel_3.add(lblEmail);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(55, 148, 173, 14);
		panel_3.add(lblPassword_1);	
		
		JLabel lblPassword_2 = new JLabel("Ripeti Password");
		lblPassword_2.setBounds(55, 178, 173, 14);
		panel_3.add(lblPassword_2);	
	
	
	
		JTextField textField_2;
		textField_2 = new JTextField();
		textField_2.setBounds(190, 25, 200, 23);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JTextField textField_3;
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(190, 55, 200, 23);
		panel_3.add(textField_3);
		
		JTextField textField_4;
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(190, 85, 200, 23);
		panel_3.add(textField_4);
		
		JTextField textField_5;
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(190, 115, 200, 23);
		panel_3.add(textField_5);
		
		JPasswordField passwordField;
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 175, 200, 23);
		panel_3.add(passwordField);
		
		JPasswordField passwordField2;
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(190, 145, 200, 23);
		panel_3.add(passwordField2);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBounds(190, 220, 89, 23);
		panel_3.add(btnRegistrati);
		
		JButton btnLogin = new JButton("Indietro");
		btnLogin.setBounds(300, 220, 89, 23);
		panel_3.add(btnLogin);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(false);
				new Login();
		}});
		
		
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					int test = new GestioneUtenza().signIn(
						textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(),
						new String(passwordField.getPassword()), new String(passwordField2.getPassword()));
						
						switch(test){
							case 0: JOptionPane.showMessageDialog(
									frmPiattaformaGaming, "Registrato con successo");
									panel_3.setVisible(false);
									new Login();
									break;
							
							case 1: JOptionPane.showMessageDialog(
									frmPiattaformaGaming, "Compilare tutti i campi", "Errore registrazione", JOptionPane.ERROR_MESSAGE); 
									textField_2.setText(textField_2.getText());
									textField_3.setText(textField_3.getText());
									textField_4.setText(textField_4.getText());
									textField_5.setText(textField_5.getText());
									passwordField.setText(String.valueOf(passwordField.getPassword()));
									passwordField2.setText(String.valueOf(passwordField2.getPassword()));
									break;
										
							case 2: JOptionPane.showMessageDialog(
									frmPiattaformaGaming, "Username già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
									textField_2.setText(textField_2.getText());
									textField_3.setText(textField_3.getText());
									textField_4.setText("");
									textField_5.setText(textField_5.getText());
									passwordField.setText(String.valueOf(passwordField.getPassword()));
									passwordField2.setText(String.valueOf(passwordField2.getPassword()));
									break;
							
							case 3: JOptionPane.showMessageDialog(
									frmPiattaformaGaming, "E-Mail già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
									textField_2.setText(textField_2.getText());
									textField_3.setText(textField_3.getText());
									textField_4.setText(textField_4.getText());
									textField_5.setText("");
									passwordField.setText(String.valueOf(passwordField.getPassword()));
									passwordField2.setText(String.valueOf(passwordField2.getPassword()));
									break;
							
							case 4: JOptionPane.showMessageDialog(
									frmPiattaformaGaming, "Email non valida!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
									textField_2.setText(textField_2.getText());
									textField_3.setText(textField_3.getText());
									textField_4.setText(textField_4.getText());
									textField_5.setText("");
									passwordField.setText(String.valueOf(passwordField.getPassword()));
									passwordField2.setText(String.valueOf(passwordField2.getPassword()));
									break;
							
							case 5: JOptionPane.showMessageDialog(
									frmPiattaformaGaming, "La password deve essere di almeno 8 caratteri!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
									textField_2.setText(textField_2.getText());
									textField_3.setText(textField_3.getText());
									textField_4.setText(textField_4.getText());
									textField_5.setText(textField_5.getText());
									passwordField.setText("");
									passwordField2.setText("");
									break;
									
							case 6: JOptionPane.showMessageDialog(
									frmPiattaformaGaming, "Le due password non coincidono!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
									textField_2.setText(textField_2.getText());
									textField_3.setText(textField_3.getText());
									textField_4.setText(textField_4.getText());
									textField_5.setText(textField_5.getText());
									passwordField.setText("");
									passwordField2.setText("");
						}
			}
		});
	}
	
}
