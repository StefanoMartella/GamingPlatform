package src.view.gioco;

import src.controller.*;
import src.model.*;
import src.view.utente.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.BorderLayout;

import java.util.*;
import java.sql.*;

public class GiocoView{
	JFrame frmPiattaformaGaming;
	Utente ut;
	Gioco gioco;
	
	public GiocoView(JFrame frame, Utente ut, Gioco g){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		this.gioco=g;
		initialize();
	}
	
	public void initialize(){
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_6);
		panel_6.setLayout(null);
		panel_6.setVisible(true);
		
		JLabel lblNewLabel_3 = new JLabel("Nome:");
		lblNewLabel_3.setBounds(40, 10, 114, 14);
		panel_6.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Esperienza fornita:");
		lblNewLabel_4.setBounds(270, 10, 114, 14);
		panel_6.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(gioco.getNome());
		lblNewLabel_5.setBounds(90, 10, 114, 14);
		panel_6.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(""+gioco.getExp());
		lblNewLabel_6.setBounds(390, 10, 114, 14);
		panel_6.add(lblNewLabel_6);
		
		JButton btnGioca = new JButton("Gioca");
		btnGioca.setBounds(115, 227, 89, 23);
		panel_6.add(btnGioca);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(50, 40, 350, 50);
		panel_6.add(textPane);
		
		JButton btnReview = new JButton("Recensisci");
		btnReview.setBounds(50, 100, 130, 23);
		panel_6.add(btnReview);
		
		JButton btnvReview = new JButton("Vedi recensioni");
		btnvReview.setBounds(270, 100, 130, 23);
		panel_6.add(btnvReview);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("1");
		rdbtnNewRadioButton.setActionCommand("1");
		rdbtnNewRadioButton.setBounds(130, 140, 40, 23);
		panel_6.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("2");
		rdbtnNewRadioButton_1.setActionCommand("2");
		rdbtnNewRadioButton_1.setBounds(170, 140, 40, 23);
		panel_6.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("3");
		rdbtnNewRadioButton_2.setActionCommand("3");
		rdbtnNewRadioButton_2.setBounds(210, 140, 40, 23);
		panel_6.add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("4");
		rdbtnNewRadioButton_3.setActionCommand("4");
		rdbtnNewRadioButton_3.setBounds(250, 140, 40, 23);
		panel_6.add(rdbtnNewRadioButton_3);
		bg.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("5");
		rdbtnNewRadioButton_4.setActionCommand("5");
		rdbtnNewRadioButton_4.setBounds(290, 140, 40, 23);
		panel_6.add(rdbtnNewRadioButton_4);
		bg.add(rdbtnNewRadioButton_4);
		
		JButton btnVota = new JButton("Vota");
		btnVota.setBounds(130, 170, 89, 23);
		panel_6.add(btnVota);
		
		DecimalFormat df = new DecimalFormat("###.#");
		JLabel mediaVoti = new JLabel("Media voti: " + df.format(new GiocoController(gioco).avgVote()));
		mediaVoti.setBounds(250,165,150,30);
		panel_6.add(mediaVoti);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(250, 227, 89, 23);
		panel_6.add(btnBack);
		
		
		btnGioca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int lvl = ut.getLivello();
					new UtenteController(ut,gioco).play();
					JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Hai giocato!");
					if(ut.getLivello()>lvl){
						JPanel opP = new JPanel();
						opP.setLayout(new BorderLayout());
						JLabel text = new JLabel();
						text.setText("<html><body>Complimenti, hai raggiunto il livello " + ut.getLivello() +
							"<br>Hai ottenuto un trofeo!</body></html>");
						ImageIcon icon = new ImageIcon(getClass().getResource("../img/Trofeo"+ ut.getLivello() +".png"));
						JLabel ico = new JLabel(icon);
						opP.add(ico,BorderLayout.SOUTH);
						opP.add(text,BorderLayout.EAST);
						JOptionPane.showMessageDialog(
							frmPiattaformaGaming, opP, "Nuovo livello",
							JOptionPane.PLAIN_MESSAGE);
					}
			}
		});
		
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new UtenteController(ut,gioco).review("Recensione di " + ut.getUsername() + ":\n" + textPane.getText()));
							textPane.setText("");
		}});
		
		btnvReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_6.setVisible(false);
				new GiocoRecensioniView(frmPiattaformaGaming,ut,gioco);
		}});
		
		btnVota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bg.getSelection()==null)
					JOptionPane.showMessageDialog(frmPiattaformaGaming, "Devi scegliere un voto", "Vote error", JOptionPane.ERROR_MESSAGE);
				else
				JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new UtenteController(ut,gioco).vote(Integer.parseInt(bg.getSelection().getActionCommand())));
							mediaVoti.setText("Media voti: " + df.format(new GiocoController(gioco).avgVote()));
							textPane.setText("");
		}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_6.setVisible(false);
				new UtenteViewGL(frmPiattaformaGaming, ut);
		}});
		
	}
}