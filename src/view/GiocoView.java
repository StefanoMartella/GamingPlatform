package view;

import controller.*;
import model.*;
import model.dao.concrete.*;

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
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setBounds(10, 31, 114, 14);
		panel_6.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Esperienza fornita");
		lblNewLabel_4.setBounds(10, 85, 114, 14);
		panel_6.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(gioco.getNome());
		lblNewLabel_5.setBounds(250, 31, 114, 14);
		panel_6.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(""+gioco.getExp());
		lblNewLabel_6.setBounds(250, 85, 114, 14);
		panel_6.add(lblNewLabel_6);
		
		JButton btnGioca = new JButton("GIOCA");
		btnGioca.setBounds(40, 130, 89, 23);
		panel_6.add(btnGioca);
		
		
		btnGioca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					new UtenteDao().play(ut,new GiocoDao().findGameByName(gioco.getNome()));
					JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Hai giocato!");
				}
				catch(SQLException exc){
			exc.printStackTrace();
		}
		}});
		
		JButton btnHome = new JButton("HOME");
		btnHome.setBounds(335, 227, 89, 23);
		panel_6.add(btnHome);
		
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_6.setVisible(false);
				new UtenteView(frmPiattaformaGaming, ut);
		}});
		
	}
}