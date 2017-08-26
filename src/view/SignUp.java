package src.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import src.controller.*;

/**
*Class which represents the view of MVC pattern of sign-up
*/
public class SignUp{
	
	private JFrame frmPiattaformaGaming;
	
	public SignUp(JFrame frm){
		this.frmPiattaformaGaming = frm;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		panel_3.setVisible(true);
		
		JLabel signup = new JLabel("Registrazione");
		signup.setFont(new Font("Tahoma", Font.ITALIC, 20));
		signup.setForeground(Color.BLUE);
		signup.setBounds(250, 10, 200, 25);
		panel_3.add(signup);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setBounds(100, 70, 173, 14);
		panel_3.add(lblName);
		
				
		JLabel lblCognme = new JLabel("Cognome");
		lblCognme.setBounds(100, 110, 173, 14);
		panel_3.add(lblCognme);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(100, 150, 173, 14);
		panel_3.add(lblUsername);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(100, 190, 173, 14);
		panel_3.add(lblEmail);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(100, 230, 173, 14);
		panel_3.add(lblPassword_1);	
		
		JLabel lblPassword_2 = new JLabel("Ripeti Password");
		lblPassword_2.setBounds(100, 270, 173, 14);
		panel_3.add(lblPassword_2);	
	
	
	
		JTextField textField_2;
		textField_2 = new JTextField();
		textField_2.setBounds(300, 65, 250, 25);
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JTextField textField_3;
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(300, 105, 250, 25);
		panel_3.add(textField_3);
		
		JTextField textField_4;
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(300, 145, 250, 25);
		panel_3.add(textField_4);
		
		JTextField textField_5;
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(300, 185, 250, 25);
		panel_3.add(textField_5);
		
		JPasswordField passwordField;
		passwordField = new JPasswordField();
		passwordField.setBounds(300, 225, 250, 25);
		panel_3.add(passwordField);
		
		JPasswordField passwordField2;
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(300, 265, 250, 25);
		panel_3.add(passwordField2);
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBounds(300, 320, 100, 23);
		panel_3.add(btnRegistrati);
		
		JButton btnLogin = new JButton("Indietro");
		btnLogin.setBounds(450, 320,100, 23);
		panel_3.add(btnLogin);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(false);
				new Login();
		}});
		
		
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					int test = new GestioneUtenza().signUp(
						textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(),
						new String(passwordField.getPassword()), new String(passwordField2.getPassword()));
						
						switch( test ){
							
							case 0: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Registrato con successo");
								panel_3.setVisible(false);
								new Login();
								break;
							
							case 1: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Compilare tutti i campi", "Errore registrazione", JOptionPane.ERROR_MESSAGE); 
								textField_2.setText(textField_2.getText());
								textField_3.setText(textField_3.getText());
								textField_4.setText(textField_4.getText());
								textField_5.setText(textField_5.getText());
								passwordField.setText(String.valueOf(passwordField.getPassword()));
								passwordField2.setText(String.valueOf(passwordField2.getPassword()));
								break;
										
							case 2: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Username già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
								textField_2.setText(textField_2.getText());
								textField_3.setText(textField_3.getText());
								textField_4.setText("");
								textField_5.setText(textField_5.getText());
								passwordField.setText(String.valueOf(passwordField.getPassword()));
								passwordField2.setText(String.valueOf(passwordField2.getPassword()));
								break;
							
							case 3: JOptionPane.showMessageDialog(frmPiattaformaGaming, "E-Mail già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
								textField_2.setText(textField_2.getText());
								textField_3.setText(textField_3.getText());
								textField_4.setText(textField_4.getText());
								textField_5.setText("");
								passwordField.setText(String.valueOf(passwordField.getPassword()));
								passwordField2.setText(String.valueOf(passwordField2.getPassword()));
								break;
							
							case 4: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Email non valida!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
								textField_2.setText(textField_2.getText());
								textField_3.setText(textField_3.getText());
								textField_4.setText(textField_4.getText());
								textField_5.setText("");
								passwordField.setText(String.valueOf(passwordField.getPassword()));
								passwordField2.setText(String.valueOf(passwordField2.getPassword()));
								break;
							
							case 5: JOptionPane.showMessageDialog(frmPiattaformaGaming, "La password deve essere di almeno 8 caratteri!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
								textField_2.setText(textField_2.getText());
								textField_3.setText(textField_3.getText());
								textField_4.setText(textField_4.getText());
								textField_5.setText(textField_5.getText());
								passwordField.setText("");
								passwordField2.setText("");
								break;
									
							case 6: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Le due password non coincidono!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
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
