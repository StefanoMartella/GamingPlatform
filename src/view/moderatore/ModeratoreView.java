package src.view.moderatore;

import src.controller.*;
import src.model.*;
import src.view.*;
import src.view.utente.*;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/**
*Class which represents the view of MVC pattern for moderator
*/
public class ModeratoreView {
	
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	
	public ModeratoreView(JFrame frame, Utente ut){
		this.frmPiattaformaGaming = frame;
		this.ut=ut;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(true);
		
		JLabel lblBenvenutoUtente = new JLabel();
		lblBenvenutoUtente.setForeground(Color.RED);
		Font font = new Font("SEGOE UI Light", Font.BOLD | Font.ITALIC ,20);
		lblBenvenutoUtente.setFont(font);
		lblBenvenutoUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoUtente.setBounds(150, 17, 350, 69);
		lblBenvenutoUtente.setText("Benvenuto " + ut.getUsername() + " [M]");
		panel_2.add(lblBenvenutoUtente);
		
		JButton btnProfiloPersonale = new JButton("Profilo Personale");
		btnProfiloPersonale.setBounds(50, 140, 250, 35);
		panel_2.add(btnProfiloPersonale);
		
		JButton btnProfiloGaming = new JButton("Profilo Gaming");
		btnProfiloGaming.setBounds(50, 200, 250, 35);
		panel_2.add(btnProfiloGaming);
		
		JButton btnVaiAiGiochi = new JButton("Vai ai Giochi!");
		btnVaiAiGiochi.setBounds(50, 260, 250, 35);
		panel_2.add(btnVaiAiGiochi);
		
		JButton btnListaUtente = new JButton("Lista Utenti");
		btnListaUtente.setBounds(350, 140, 250, 35);
		panel_2.add(btnListaUtente);
		
		JButton btnLR = new JButton("Lista Recensioni");
		btnLR.setBounds(350, 200, 250, 35);
		List<Recensione> lr = new ModeratoreController().reviewsList();
		if(lr.isEmpty())
				btnLR.setBackground(Color.LIGHT_GRAY);
		panel_2.add(btnLR);
		
		JButton btnLogout = new JButton("Esci");
		btnLogout.setBounds(350,260, 250, 35);
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
		
		btnLR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if( lr.isEmpty() ){
						JOptionPane.showMessageDialog(
						frmPiattaformaGaming, "Non ci sono recensioni da approvare/rifiutare!", "No recensioni", JOptionPane.ERROR_MESSAGE);
					}
					else{
						panel_2.setVisible(false);
						new ModeratoreViewLR(frmPiattaformaGaming,ut);
					}
		}});
		
		btnListaUtente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new ModeratoreViewLU(frmPiattaformaGaming,ut);
		}});
	}
}
	
