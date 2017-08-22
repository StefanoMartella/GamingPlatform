package src.view.utente;

import src.model.*;
import src.view.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

/**
*Class which represents the view of MVC pattern of the user
*/
public class UtenteView {
	
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	
	public UtenteView(JFrame frame, Utente ut) {
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(true);
		
		JLabel lblBenvenutoUtente = new JLabel();
		lblBenvenutoUtente.setForeground(Color.BLUE);
		Font font = new Font("SEGOE UI Light", Font.BOLD | Font.ITALIC, 20);
		lblBenvenutoUtente.setFont(font);
		lblBenvenutoUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoUtente.setBounds(150, 17, 350, 69);
		lblBenvenutoUtente.setText("Benvenuto " + ut.getUsername());
		panel_2.add(lblBenvenutoUtente);
		
		JButton btnProfiloPersonale = new JButton("Profilo Personale");
		btnProfiloPersonale.setBounds(200, 100, 250, 35);
		panel_2.add(btnProfiloPersonale);
		
		JButton btnProfiloGaming = new JButton("Profilo Gaming");
		btnProfiloGaming.setBounds(200, 160, 250, 35);
		panel_2.add(btnProfiloGaming);
		
		JButton btnVaiAiGiochi = new JButton("Vai ai Giochi!");
		btnVaiAiGiochi.setBounds(200, 220, 250, 35);
		panel_2.add(btnVaiAiGiochi);
		
		
		JButton btnLogout = new JButton("Esci");
		btnLogout.setBounds(200,280,250,35);
		panel_2.add(btnLogout);
		
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ut = null;
				panel_2.setVisible(false);
				new Login();
		}});
		
		btnProfiloPersonale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewProfiloPers(frmPiattaformaGaming,ut);
		}});
		
		btnProfiloGaming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewProfiloGaming(frmPiattaformaGaming,ut);
		}});
		btnVaiAiGiochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewGL(frmPiattaformaGaming,ut);
		}});
	}
}
