package view;

import controller.*;
import model.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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


public class GiocoRecensioniView {
	
	JFrame frmPiattaformaGaming;
	Gioco gioco;
	Utente utente;
	
	public GiocoRecensioniView(JFrame frame, Utente utente, Gioco gioco){
		this.frmPiattaformaGaming=frame;
		this.gioco=gioco;
		this.utente=utente;
		
		initialize();
	}
	
	public void initialize(){
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 0, 450, 300);
		frmPiattaformaGaming.getContentPane().add(panel_7);
		panel_7.setLayout(null);
		panel_7.setVisible(true);
		
		JTextArea ta = new JTextArea();
		ta.setBounds(0,0,450,180);
		ta.setEditable(false);
		panel_7.add(ta);
		
		ArrayList<Recensione> al = new GiocoController(gioco).allReviews();
		for(Recensione r: al){
			ta.setText(ta.getText()+ "\r\n----------------------\r\n" + r.getTesto());
		}
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(0, 200, 89, 23);
		panel_7.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_7.setVisible(false);
				new GiocoView(frmPiattaformaGaming,utente,gioco);
		}});
	}
}