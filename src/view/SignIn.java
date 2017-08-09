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

public class SignIn{
	
	JFrame frmPiattaformaGaming;
	
	public SignIn(JFrame frm){
		this.frmPiattaformaGaming = frm;
		initialize();
	}
	
	public void initialize(){
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		panel_3.setVisible(true);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(53, 23, 173, 14);
		panel_3.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(53, 69, 173, 14);
		panel_3.add(lblUsername);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(53, 92, 173, 14);
		panel_3.add(lblEmail);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(53, 116, 173, 14);
		panel_3.add(lblPassword_1);
		
		JLabel lblCognme = new JLabel("Cognome");
		lblCognme.setBounds(53, 46, 173, 14);
		panel_3.add(lblCognme);
		
		
	
	
	
		JTextField textField_2;
		textField_2 = new JTextField();
		textField_2.setBounds(183, 23, 173, 20);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JTextField textField_3;
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(183, 46, 173, 20);
		panel_3.add(textField_3);
		
		JTextField textField_4;
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(183, 69, 173, 20);
		panel_3.add(textField_4);
		
		JTextField textField_5;
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(183, 92, 173, 20);
		panel_3.add(textField_5);
		
		JPasswordField passwordField;
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 116, 173, 20);
		passwordField.setEchoChar('*'); 
		panel_3.add(passwordField);
		
		JButton btnRegistrati = new JButton("REGISTRATI");
		btnRegistrati.setBounds(183, 157, 139, 23);
		panel_3.add(btnRegistrati);
		
		JButton btnLogin = new JButton("INDIETRO");
		btnLogin.setBounds(335, 227, 89, 23);
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
						new String(passwordField.getPassword()));
					if(test==0){
							JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Registrato con successo");
							panel_3.setVisible(false);
							new Login();
					}
					if(test==1){
					JOptionPane.showMessageDialog(
					frmPiattaformaGaming, "Username già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
					}
					if(test==2){
						JOptionPane.showMessageDialog(
					frmPiattaformaGaming, "E-Mail già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
					}
					if(test==3){
						JOptionPane.showMessageDialog(
					frmPiattaformaGaming, "Compilare tutti i campi", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
					}
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					passwordField.setText("");
				}
			
		});
	}
	
}