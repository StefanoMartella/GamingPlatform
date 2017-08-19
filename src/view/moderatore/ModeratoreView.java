package src.view.moderatore;

import src.controller.*;
import src.model.*;
import src.view.*;
import src.view.utente.*;

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
*Class which represents the view of MVC pattern for moderator
*/
public class ModeratoreView {
	
	JFrame frmPiattaformaGaming;
	Utente ut;
	
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
		panel_2.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(true);
		
		JLabel lblBenvenutoUtente = new JLabel();
		lblBenvenutoUtente.setForeground(Color.RED);
		Font font = new Font("SEGOE UI Light", Font.BOLD,16);
		lblBenvenutoUtente.setFont(font);
		lblBenvenutoUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenvenutoUtente.setBounds(113, 17, 250, 69);
		lblBenvenutoUtente.setText("BENVENUTO [M] " + ut.getUsername());
		panel_2.add(lblBenvenutoUtente);
		
		JButton btnProfiloPersonale = new JButton("Profilo Personale");
		btnProfiloPersonale.setBounds(24, 86, 160, 30);
		panel_2.add(btnProfiloPersonale);
		
		JButton btnProfiloGaming = new JButton("Profilo Gaming");
		btnProfiloGaming.setBounds(24, 132, 160, 30);
		panel_2.add(btnProfiloGaming);
		
		JButton btnVaiAiGiochi = new JButton("Vai ai Giochi!");
		btnVaiAiGiochi.setBounds(24, 178, 160, 30);
		panel_2.add(btnVaiAiGiochi);
		
		JButton btnListaUtente = new JButton("Lista Utenti");
		btnListaUtente.setBounds(266, 86, 160, 30);
		panel_2.add(btnListaUtente);
		
		JButton btnLR = new JButton("Lista Recensioni");
		btnLR.setBounds(266, 132, 160, 30);
		List<Recensione> lr = new ModeratoreController().reviewsList();
		if(lr.isEmpty())
				btnLR.setBackground(Color.LIGHT_GRAY);
		panel_2.add(btnLR);
		
		JButton btnLogout = new JButton("Esci");
		btnLogout.setBounds(266,178,160,30);
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
						frmPiattaformaGaming, "Non ci sono recensioni da approvare/rifiutare!");
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
	
