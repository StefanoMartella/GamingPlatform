package src.view.amministratore;

import src.model.*;
import src.view.*;
import src.view.utente.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
*Class which represents the view of MVC pattern of administrator
*/
public class AmministratoreView {
	
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	
	public AmministratoreView(JFrame frame, Utente ut){
		this.frmPiattaformaGaming = frame;
		this.ut=ut;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		
		JPanel panel_0 = new JPanel();
		panel_0.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_0);
		panel_0.setLayout(null);
		panel_0.setVisible(true);
		
		JLabel lblBenvenutoUtente = new JLabel();
		lblBenvenutoUtente.setForeground(Color.RED);
		Font font = new Font("SEGOE UI Light", Font.BOLD | Font.ITALIC, 20);
		lblBenvenutoUtente.setFont(font);
		lblBenvenutoUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoUtente.setBounds(150, 17, 350, 69);
		lblBenvenutoUtente.setText("Benvenuto " + ut.getUsername() + " [A]");
		panel_0.add(lblBenvenutoUtente);
		
		JButton btnProfiloPersonale = new JButton("Profilo Personale");
		btnProfiloPersonale.setBounds(200, 100, 250, 35);
		panel_0.add(btnProfiloPersonale);
		
		JButton btnLG = new JButton("Lista Giochi");
		btnLG.setBounds(200, 160, 250, 35);
		panel_0.add(btnLG);
		
		JButton btnLU = new JButton("Lista Utenti");
		btnLU.setBounds(200, 220, 250, 35);
		panel_0.add(btnLU);
		
		JButton btnLogout = new JButton("Esci");
		btnLogout.setBounds(200,280,250,35);
		panel_0.add(btnLogout);
		
		btnProfiloPersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				new UtenteViewProfiloPers(frmPiattaformaGaming,ut);
		}});
		
		btnLG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				new AmministratoreViewLG(frmPiattaformaGaming,ut);
		}});
		
		btnLU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_0.setVisible(false);
				new AmministratoreViewLU(frmPiattaformaGaming,ut);
		}});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ut = null;
				panel_0.setVisible(false);
				new Login();
		}});
	}
}