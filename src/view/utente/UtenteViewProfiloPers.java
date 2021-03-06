package src.view.utente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import src.controller.*;
import src.model.*;
import src.view.moderatore.*;
import src.view.amministratore.*;

/**
*Class which represents the view of MVC pattern of user's personal page
*/
public class UtenteViewProfiloPers{
	private JFrame frmPiattaformaGaming;
	private Utente utente;
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*@param utente current user
	*/
	public UtenteViewProfiloPers(JFrame frame, Utente utente){
		this.frmPiattaformaGaming = frame;
		this.utente = utente;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		panel_4.setVisible(true);
		
		JLabel personal = new JLabel("Profilo Personale");
		personal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		personal.setBounds(250, 10, 200, 25);
		panel_4.add(personal);
		
		JLabel lblNewLabel_3 = new JLabel("Nome:");
		lblNewLabel_3.setBounds(20, 80, 114, 14);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblLivello_1 = new JLabel("Cognome:");
		lblLivello_1.setBounds(20, 130, 114, 14);
		panel_4.add(lblLivello_1);
		
		JLabel lblLivello = new JLabel("Username:");
		lblLivello.setBounds(20, 180, 114, 14);
		panel_4.add(lblLivello);
		
		JLabel lblTrofei = new JLabel("E-Mail:");
		lblTrofei.setBounds(20, 230, 114, 14);
		panel_4.add(lblTrofei);
		
		JLabel lblNewLabel_4 = new JLabel(utente.getNome());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(200, 80, 166, 20);
		panel_4.add(lblNewLabel_4);
		
		JLabel label = new JLabel(utente.getCognome());
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(200, 130, 166, 20);
		panel_4.add(label);
		
		JLabel label_1 = new JLabel(utente.getUsername());
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(200, 180, 166, 20);
		panel_4.add(label_1);
		
		JLabel label_2 = new JLabel(utente.getEmail());
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(200, 230, 250, 20);
		panel_4.add(label_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(540, 340, 100, 25);
		panel_4.add(btnHome);
		
		JLabel update = new JLabel("Modifica  dati  personali:");
		update.setFont(new Font("Tahoma", Font.BOLD, 13));
		update.setBounds(25, 300, 200, 20);
		panel_4.add(update);
		
		String[] values = {"nome", "cognome", "username", "email", "password"};
		JComboBox<String> comboBox = new JComboBox<>(values);
		comboBox.setBounds(20, 340, 100, 25);
		panel_4.add(comboBox);
		
		JButton selection = new JButton("Modifica");
		selection.setBounds(135, 340, 100, 25);
		panel_4.add(selection);
		
		frmPiattaformaGaming.getRootPane().setDefaultButton(selection);
		
		selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch( (String) comboBox.getSelectedItem() ){
				
					case "nome":    	String nuovo_nome = JOptionPane.showInputDialog("Seleziona nuovo nome:");
								if( nuovo_nome != null ){
									JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("nome", nuovo_nome, utente));
									lblNewLabel_4.setText(utente.getNome());
								}
								break;


					case "cognome":		String nuovo_cognome = JOptionPane.showInputDialog("Seleziona nuovo cognome:");
								if( nuovo_cognome != null ){
									JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("cognome", nuovo_cognome, utente));
									label.setText(utente.getCognome());
								}
								break;

					case "username":	String nuova_username = JOptionPane.showInputDialog("Seleziona nuovo username:");
								if( nuova_username != null ){
									JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("username", nuova_username, utente));
									label_1.setText(utente.getUsername());
								}
								break;

					case "email":		String nuova_email = JOptionPane.showInputDialog("Seleziona nuovo email:");
								if( nuova_email != null ){
									JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("email", nuova_email, utente));
									label_2.setText(utente.getEmail());
								}
								break;

					case "password":	JPasswordField pf  = new JPasswordField();
								JPasswordField pf2 = new JPasswordField();
								JLabel text = new JLabel("Scegli e conferma la nuova password:");
								Object[] message = { text, pf, pf2 };
								Integer pressed_button = JOptionPane.showConfirmDialog(null, message, "Nuova password", JOptionPane.OK_CANCEL_OPTION);
								String nuova_password = String.valueOf(pf.getPassword());
								String conferma_password = String.valueOf(pf2.getPassword());
								if( pressed_button == 0 )
									JOptionPane.showMessageDialog(frmPiattaformaGaming, new GestioneUtenza().updateValue("password", nuova_password, conferma_password, utente));
				}				
		}});
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( utente.getTipo().equals("moderatore") ){
					frmPiattaformaGaming.getRootPane().setDefaultButton(null);
					panel_4.setVisible(false);
					new ModeratoreView(frmPiattaformaGaming, utente);
				}
				else if( utente.getTipo().equals("amministratore") ){
					frmPiattaformaGaming.getRootPane().setDefaultButton(null);
					panel_4.setVisible(false);
					new AmministratoreView(frmPiattaformaGaming, utente);
				}
				else{
					frmPiattaformaGaming.getRootPane().setDefaultButton(null);
					panel_4.setVisible(false);
					new UtenteView(frmPiattaformaGaming, utente);
				}
		}});
	}
}
