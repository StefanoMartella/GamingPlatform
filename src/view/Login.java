package src.view;

import src.controller.*;
import src.model.*;
import src.view.utente.*;
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
*Class which represents the view of MVC pattern of login
*/
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
		frmPiattaformaGaming.setBounds(700, 300, 465, 315);
		frmPiattaformaGaming.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPiattaformaGaming.setResizable(false);
		frmPiattaformaGaming.getContentPane().setLayout(null);
		
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
		
		JLabel name = new JLabel("Gaming Platform");
		name.setForeground(Color.BLUE);
		Font font = new Font("SEGOE UI Light", Font.BOLD | Font.ITALIC, 20);
		name.setFont(font);
		name.setBounds(175, 20, 200, 35);
		panel.add(name);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(75, 95, 150, 20);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(75, 140, 150, 20);
		panel.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(160, 95, 200, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		psswrdField = new JPasswordField();
		psswrdField.setColumns(10);
		psswrdField.setBounds(160, 140, 200, 25);
		psswrdField.setEchoChar('*'); 
		panel.add(psswrdField);
		
		JButton btnSignUp = new JButton("Registrati");
		btnSignUp.setBounds(270, 195, 89, 23);
		panel.add(btnSignUp);
		
		JButton btnLogin = new JButton("Accedi");
		
		btnLogin.setBounds(160, 195, 89, 23);
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
					panel.setVisible(false);
					if(ut.getTipo().equals("utente")){
						new UtenteView(frmPiattaformaGaming, ut);
					}
					if(ut.getTipo().equals("moderatore")){
						new ModeratoreView(frmPiattaformaGaming, ut);
					}
					if(ut.getTipo().equals("amministratore")){
						new AmministratoreView(frmPiattaformaGaming, ut);
					}
				
				}
		}
			});
		
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(false);
				new SignUp(frmPiattaformaGaming);
			}
		});
		
	}
}
