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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.util.*;
import java.sql.*;

public class ModeratoreViewLU{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public ModeratoreViewLU(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	
	public void initialize(){
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(0, 0, 465, 300);
		frmPiattaformaGaming.getContentPane().add(panel_8);
		panel_8.setLayout(null);
		panel_8.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new GridLayout(0,2, 0, 20));
		
		List<Utente> lU = new ModeratoreController().usersList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		for(Utente u: lU){
			if(!(u.getTipo().equals("amministratore") || u.getUsername().equals(ut.getUsername())))
				jl.add(new JLabel(u.getUsername()));
		}
		for(JLabel j: jl){
			ps.add(j);
			JButton selez;
			String type = new GestioneUtenza().findUser(j.getText()).getTipo();
			if(type.equals("moderatore"))
				selez = new JButton("RETROCEDI");
			else
				selez = new JButton("PROMUOVI");
			selez.setSize(30,20);
			ps.add(selez);
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selez.getText().equals("RETROCEDI")){
							new ModeratoreController(new GestioneUtenza().findUser(j.getText())).demote();
							JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Moderatore retrocesso.");
							selez.setText("PROMUOVI");
						}
						else{
							new ModeratoreController(new GestioneUtenza().findUser(j.getText())).promote();
							JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Utente promosso!");
							selez.setText("RETROCEDI");
						}
							
			}});
		}
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(330, 242, 89, 18);
		panel_8.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_8.setVisible(false);
				new ModeratoreView(frmPiattaformaGaming, ut);
		}});
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setSize(460,240);
		panel_8.add(scroll);
	}
}