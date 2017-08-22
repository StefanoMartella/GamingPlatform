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
	/**
	 * Launch the application.
	 *
	 *@param args command line args
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
		frmPiattaformaGaming.setBounds(700, 300, 665, 415);
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
		panel.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		
		JLabel name = new JLabel("Gaming Platform");
		name.setForeground(Color.BLUE);
		Font font = new Font("SEGOE UI Light", Font.BOLD | Font.ITALIC, 25);
		name.setFont(font);
		name.setBounds(245, 20, 200, 35);
		panel.add(name);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(125, 130, 150, 20);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(125, 187, 150, 30);
		panel.add(lblPassword);
		
		JTextField textField = new JTextField();
		textField.setBounds(220, 130, 250, 25);
		panel.add(textField);
		textField.setColumns(10);
		
		JPasswordField psswrdField = new JPasswordField();
		psswrdField.setColumns(10);
		psswrdField.setBounds(220, 190, 250, 25); 
		panel.add(psswrdField);
		
		JButton btnSignUp = new JButton("Registrati");
		btnSignUp.setBounds(370, 260, 100, 23);
		panel.add(btnSignUp);
		
		JButton btnLogin = new JButton("Accedi");
		
		btnLogin.setBounds(220, 260, 100, 23);
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Utente ut = new GestioneUtenza().logIn(textField.getText(), new String(psswrdField.getPassword()));
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
