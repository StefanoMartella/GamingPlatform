package view;

import controller.*;
import model.*;

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

public class UtenteViewProfiloGaming{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public UtenteViewProfiloGaming(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	
	public void initialize(){
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(true);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBounds(10, 30, 114, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblLivello_1 = new JLabel("Livello");
		lblLivello_1.setBounds(10, 60, 114, 14);
		panel_1.add(lblLivello_1);
		
		JLabel lblLivello = new JLabel("Punti EXP");
		lblLivello.setBounds(10, 90, 114, 14);
		panel_1.add(lblLivello);
		
		JLabel lblTrofei = new JLabel("Trofei");
		lblTrofei.setBounds(10, 120, 114, 14);
		panel_1.add(lblTrofei);
		
		JLabel lblNewLabel_4 = new JLabel(ut.getUsername());
		lblNewLabel_4.setBounds(134, 30, 166, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel label = new JLabel(""+ut.getLivello());
		label.setBounds(134, 60, 166, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel(""+ut.getPuntiExp());
		label_1.setBounds(134, 90, 166, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Hai collezionato " + ut.getLivello() + " trofei!");
		label_2.setBounds(134, 120, 166, 14);
		panel_1.add(label_2);
		
		JLabel lblTimeline = new JLabel("Timeline:");
		lblTimeline.setBounds(10, 200, 166, 14);
		panel_1.add(lblTimeline);
		
		TreeMap<Integer,String> timeline = new UtenteController().timeline(ut.getUsername());
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		
		for(Map.Entry entry : timeline.entrySet()){
			jl.add(new JLabel(entry.getValue().toString() + "     -    livello  " + entry.getKey().toString()));
		}
		int i = 0;
		for(JLabel current : jl){
			current.setBounds(134, 160+20*i,160,30);
			panel_1.add(current);
			i++;
		}
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(335, 227, 89, 23);
		panel_1.add(btnHome);
		
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ut.getTipo().equals("moderatore")){
					panel_1.setVisible(false);
					new ModeratoreView(frmPiattaformaGaming, ut);
				}
				else{
					panel_1.setVisible(false);
					new UtenteView(frmPiattaformaGaming, ut);
				}
		}});
	}
}