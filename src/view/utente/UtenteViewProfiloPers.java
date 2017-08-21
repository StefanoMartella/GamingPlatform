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
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

import java.util.*;
import java.sql.*;

/**
*Class which represents the view of MVC pattern of user's personal page
*/
public class UtenteViewProfiloPers{
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	
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
		
		JLabel lblNewLabel_3 = new JLabel("Nome:");
		lblNewLabel_3.setBounds(10, 30, 114, 14);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblLivello_1 = new JLabel("Cognome:");
		lblLivello_1.setBounds(10, 70, 114, 14);
		panel_4.add(lblLivello_1);
		
		JLabel lblLivello = new JLabel("Username:");
		lblLivello.setBounds(10, 110, 114, 14);
		panel_4.add(lblLivello);
		
		JLabel lblTrofei = new JLabel("E-Mail:");
		lblTrofei.setBounds(10, 150, 114, 14);
		panel_4.add(lblTrofei);
		
		JLabel lblNewLabel_4 = new JLabel(ut.getNome());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(134, 30, 166, 14);
		panel_4.add(lblNewLabel_4);
		
		JLabel label = new JLabel(ut.getCognome());
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(134, 70, 166, 14);
		panel_4.add(label);
		
		JLabel label_1 = new JLabel(ut.getUsername());
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(134, 110, 166, 14);
		panel_4.add(label_1);
		
		JLabel label_2 = new JLabel(ut.getEmail());
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(134, 150, 250, 20);
		panel_4.add(label_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(335, 235, 89, 20);
		panel_4.add(btnHome);
		
		JLabel update = new JLabel("Modifica      dati      personali:");
		update.setBounds(30, 205, 200, 20);
		panel_4.add(update);
		
		String[] values = {"nome", "cognome", "username", "email", "password"};
		JComboBox<String> comboBox = new JComboBox<>(values);
		comboBox.setBounds(10, 235, 100, 20);
		panel_4.add(comboBox);
		
		JButton selection = new JButton("Modifica");
		selection.setBounds(110,235, 100, 20);
		panel_4.add(selection);
		
		selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch((String) comboBox.getSelectedItem()){
				
				case "nome":    	String nuovo_nome = JOptionPane.showInputDialog("Seleziona nuovo nome:");
									if(nuovo_nome != null){
										JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("nome", nuovo_nome, ut));
										ut.setNome(nuovo_nome);
									}
									panel_4.setVisible(false);
									new UtenteViewProfiloPers(frmPiattaformaGaming, ut);
									break;
					

				case "cognome":		String nuovo_cognome = JOptionPane.showInputDialog("Seleziona nuovo cognome:");
									if(nuovo_cognome != null){
										JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("cognome", nuovo_cognome, ut));
										ut.setCognome(nuovo_cognome);
									}
									panel_4.setVisible(false);
									new UtenteViewProfiloPers(frmPiattaformaGaming, ut);
									break;
								
				case "username":	String nuova_username = JOptionPane.showInputDialog("Seleziona nuovo username:");
									if(nuova_username != null){
										JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("username", nuova_username, ut));
										ut.setUsername(nuova_username);
									}
									panel_4.setVisible(false);
									new UtenteViewProfiloPers(frmPiattaformaGaming, ut);
									break;
					
				case "email":		String nuova_email = JOptionPane.showInputDialog("Seleziona nuovo email:");
									if(nuova_email != null){
										JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("email", nuova_email, ut));
										ut.setEmail(nuova_email);
									}
									panel_4.setVisible(false);
									new UtenteViewProfiloPers(frmPiattaformaGaming, ut);
									break;
									
				case "password":	JPasswordField pf  = new JPasswordField();
									JPasswordField pf2 = new JPasswordField();
									Object[] message = { pf, pf2 };
									Integer pressed_button = JOptionPane.showConfirmDialog(null, message, "Nuova password", JOptionPane.OK_CANCEL_OPTION);
									String nuova_password = String.valueOf(pf.getPassword());
									String conferma_password = String.valueOf(pf2.getPassword());
									if(pressed_button == 0){
										JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("password", nuova_password, conferma_password, ut));
										ut.setPassword(nuova_password);
									}
				}				
		}});
		
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