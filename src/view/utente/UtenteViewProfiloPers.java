package src.view.utente;

import src.controller.*;
import src.model.*;
import src.view.moderatore.*;
import src.view.amministratore.*;

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
*Class which represents the view of MVC pattern of user's personal page
*/
public class UtenteViewProfiloPers{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public UtenteViewProfiloPers(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
	JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		panel_4.setVisible(true);
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setBounds(10, 30, 114, 14);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblLivello_1 = new JLabel("Cognome");
		lblLivello_1.setBounds(10, 70, 114, 14);
		panel_4.add(lblLivello_1);
		
		JLabel lblLivello = new JLabel("Username");
		lblLivello.setBounds(10, 110, 114, 14);
		panel_4.add(lblLivello);
		
		JLabel lblTrofei = new JLabel("E-Mail");
		lblTrofei.setBounds(10, 150, 114, 14);
		panel_4.add(lblTrofei);
		
		JLabel lblNewLabel_4 = new JLabel(ut.getNome());
		lblNewLabel_4.setBounds(134, 30, 166, 14);
		panel_4.add(lblNewLabel_4);
		
		JLabel label = new JLabel(ut.getCognome());
		label.setBounds(134, 70, 166, 14);
		panel_4.add(label);
		
		JLabel label_1 = new JLabel(ut.getUsername());
		label_1.setBounds(134, 110, 166, 14);
		panel_4.add(label_1);
		
		JLabel label_2 = new JLabel(ut.getEmail());
		label_2.setBounds(134, 150, 250, 20);
		panel_4.add(label_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(335, 227, 89, 23);
		panel_4.add(btnHome);
		
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ut.getTipo().equals("moderatore")){
					panel_4.setVisible(false);
					new ModeratoreView(frmPiattaformaGaming, ut);
				}
				else if(ut.getTipo().equals("amministratore")){
					panel_4.setVisible(false);
					new AmministratoreView(frmPiattaformaGaming, ut);
				}
				else{
					panel_4.setVisible(false);
					new UtenteView(frmPiattaformaGaming, ut);
				}
		}});
	}
}