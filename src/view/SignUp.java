package src.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
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
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*/
	public SignUp(JFrame frame){
		this.frmPiattaformaGaming = frame;
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
	
		JLabel check1 = new JLabel(); // name validity check
		JLabel check2 = new JLabel(); // surname validity check
		JLabel check3 = new JLabel(); // username validity check
		JLabel check4 = new JLabel(); // email validity check
		JLabel check5 = new JLabel(); // password validity check
		JLabel check6 = new JLabel(); // password confirmation validity check
		
		JLabel password_security = new JLabel();
		password_security.setFont(new Font("SEGOE UI Light", Font.BOLD, 15));
		password_security.setBounds(580, 225, 100, 20);
		panel_3.add(password_security);
	
		ImageIcon red_check = new ImageIcon(getClass().getResource("img/red.png"));
		ImageIcon green_check = new ImageIcon(getClass().getResource("img/green.png"));
		
		JTextField textField_2;
		textField_2 = new JTextField();
		textField_2.setBounds(300, 65, 250, 25);
		textField_2.setToolTipText("Inserisci il tuo nome, massimo 30 caratteri.");
		panel_3.add(textField_2);
		textField_2.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				String text = textField_2.getText();
				check1.setBounds(555, 60, 30, 30);
				panel_3.add(check1);
				if( !new GestioneUtenza().updateValue("nome", text, null).equals("Nome aggiornato!") ) {
					check1.setIcon(red_check);
				}
				else { 
					check1.setIcon(green_check);
				}
			}
		});
		
		JTextField textField_3;
		textField_3 = new JTextField();
		textField_3.setBounds(300, 105, 250, 25);
		textField_3.setToolTipText("Inserisci il tuo cognome, massimo 30 caratteri.");
		panel_3.add(textField_3);
		textField_3.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				String text = textField_3.getText();
				check2.setBounds(555, 100, 30, 30);
				panel_3.add(check2);
				if( !new GestioneUtenza().updateValue("cognome", text, null).equals("Cognome aggiornato!") ) {
					check2.setIcon(red_check);
				}
				else { 
					check2.setIcon(green_check);
				}
			}
		});
		
		JTextField textField_4;
		textField_4 = new JTextField();
		textField_4.setBounds(300, 145, 250, 25);
		textField_4.setToolTipText("Inserisci il tuo username, massimo 30 caratteri.");
		panel_3.add(textField_4);
		textField_4.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				String text = textField_4.getText();
				check3.setBounds(555, 140, 30, 30);
				panel_3.add(check3);
				if( !new GestioneUtenza().updateValue("username", text, null).equals("Username aggiornato!") ) {
					check3.setIcon(red_check);
				}
				else { 
					check3.setIcon(green_check);
				}
			}
		});
		
		JTextField textField_5;
		textField_5 = new JTextField();
		textField_5.setBounds(300, 185, 250, 25);
		textField_5.setToolTipText("Inserisci la tua email, massimo 45 caratteri.");
		panel_3.add(textField_5);
		textField_5.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				String text = textField_5.getText();
				check4.setBounds(555, 180, 30, 30);
				panel_3.add(check4);
				if( !new GestioneUtenza().updateValue("email", text, null).equals("Email aggiornata!") ) {
					check4.setIcon(red_check);
				}
				else { 
					check4.setIcon(green_check);
				}
			}
		});
		
		JPasswordField passwordField;
		passwordField = new JPasswordField();
		passwordField.setBounds(300, 225, 250, 25);
		passwordField.setToolTipText("Inserisci la tua password, massimo 30 caratteri, minimo 8.");
		panel_3.add(passwordField);
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				String text = String.valueOf(passwordField.getPassword());
				check5.setBounds(555, 220, 30, 30);
				panel_3.add(check5);
				if( text.trim().isEmpty() || text.length() < 8 || text.length() > 30 ) {
					check5.setIcon(red_check);
					password_security.setVisible(false);
				} 
				else {
					int strengthPercentage=0;
					String[] partialRegexChecks = { ".*[a-z]+.*", ".*[A-Z]+.*",  ".*[\\d]+.*", ".*[@#$%]+.*"};
					
					if (text.matches(partialRegexChecks[0])) {
					    strengthPercentage += 25;
					}
					if (text.matches(partialRegexChecks[1])) {
					    strengthPercentage += 25;
					}
					if (text.matches(partialRegexChecks[2])) {
					    strengthPercentage += 25;
					}
					if (text.matches(partialRegexChecks[3])) {
					    strengthPercentage += 25;
					}
					switch( strengthPercentage ) {

						case 0:		
						case 25:	password_security.setText("Basso");
										password_security.setForeground(Color.RED);
										break;

						case 50:	password_security.setText("Mediocre");
										password_security.setForeground(Color.MAGENTA);
										break;

						case 75:	password_security.setText("Buono");
										password_security.setForeground(Color.ORANGE);
										break;

						case 100:	password_security.setText("Eccellente");
										password_security.setForeground(Color.GREEN);

					}

					password_security.setVisible(true);
					check5.setIcon(green_check);
				}
			}
		});
		
		JPasswordField passwordField2;
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(300, 265, 250, 25);
		passwordField2.setToolTipText("Ripeti la password.");
		panel_3.add(passwordField2);
		passwordField2.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {
				String text = String.valueOf(passwordField.getPassword());
				String text2 = String.valueOf(passwordField2.getPassword());
				check6.setBounds(555, 260, 30, 30);
				panel_3.add(check6);
				if( text2.trim().isEmpty() || text2.length() < 8 || text2.length() > 30  || !text2.equals(text) ) {
					check6.setIcon(red_check);
				} 
				else {
					check6.setIcon(green_check);
				}
			}
		});
		
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.setBounds(300, 320, 100, 23);
		panel_3.add(btnRegistrati);
		
		JButton btnLogin = new JButton("Indietro");
		btnLogin.setBounds(450, 320,100, 23);
		panel_3.add(btnLogin);
		
		frmPiattaformaGaming.getRootPane().setDefaultButton(btnRegistrati);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(false);
				frmPiattaformaGaming.getRootPane().setDefaultButton(null);
				new Login();
		}});
		
		
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int test = new GestioneUtenza().signUp(textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(), new String(passwordField.getPassword()), new String(passwordField2.getPassword()));
						
				switch( test ){
							
						case 0: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Registrato con successo");
							panel_3.setVisible(false);
							frmPiattaformaGaming.getRootPane().setDefaultButton(null);
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
							
						case 2: JOptionPane.showMessageDialog(frmPiattaformaGaming, "<html><body>La lunghezza dei campi è limitata: <br><br>nome, cognome, username e password 30 caratteri max. <br>email 45 caratteri max.</html></body>", "Errore registrazione", JOptionPane.ERROR_MESSAGE); 
							textField_2.setText("");
							textField_3.setText("");
							textField_4.setText("");
							textField_5.setText("");
							passwordField.setText("");
							passwordField2.setText("");
							break;
							
						case 3: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Username già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
							textField_2.setText(textField_2.getText());
							textField_3.setText(textField_3.getText());
							textField_4.setText("");
							textField_5.setText(textField_5.getText());
							passwordField.setText(String.valueOf(passwordField.getPassword()));
							passwordField2.setText(String.valueOf(passwordField2.getPassword()));
							break;
							
						case 4: JOptionPane.showMessageDialog(frmPiattaformaGaming, "E-Mail già in uso", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
							textField_2.setText(textField_2.getText());
							textField_3.setText(textField_3.getText());
							textField_4.setText(textField_4.getText());
							textField_5.setText("");
							passwordField.setText(String.valueOf(passwordField.getPassword()));
							passwordField2.setText(String.valueOf(passwordField2.getPassword()));
							break;
							
						case 5: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Email non valida!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
							textField_2.setText(textField_2.getText());
							textField_3.setText(textField_3.getText());
							textField_4.setText(textField_4.getText());
							textField_5.setText("");
							passwordField.setText(String.valueOf(passwordField.getPassword()));
							passwordField2.setText(String.valueOf(passwordField2.getPassword()));
							break;
							
						case 6: JOptionPane.showMessageDialog(frmPiattaformaGaming, "La password deve essere di almeno 8 caratteri!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
							textField_2.setText(textField_2.getText());
							textField_3.setText(textField_3.getText());
							textField_4.setText(textField_4.getText());
							textField_5.setText(textField_5.getText());
							passwordField.setText("");
							passwordField2.setText("");
							break;
									
						case 7: JOptionPane.showMessageDialog(frmPiattaformaGaming, "Le due password non coincidono!", "Errore registrazione", JOptionPane.ERROR_MESSAGE);
							textField_2.setText(textField_2.getText());
							textField_3.setText(textField_3.getText());
							textField_4.setText(textField_4.getText());
							textField_5.setText(textField_5.getText());
							passwordField.setText(String.valueOf(passwordField.getPassword()));
							passwordField2.setText("");
				}
			}
		});
	}
	
}
