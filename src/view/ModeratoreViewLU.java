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
import net.miginfocom.swing.MigLayout;


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
		panel_8.setLayout(new MigLayout());
		panel_8.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("", "[]170[]", "[][]"));
		
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
				selez = new JButton("Retrocedi");
			else
				selez = new JButton("Promuovi");
			ps.add(selez,"wmin 140, wrap");
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selez.getText().equals("Retrocedi")){
							new ModeratoreController(new GestioneUtenza().findUser(j.getText())).demote();
							JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Moderatore retrocesso.");
							selez.setText("Promuovi");
						}
						else{
							new ModeratoreController(new GestioneUtenza().findUser(j.getText())).promote();
							JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Utente promosso!");
							selez.setText("Retrocedi");
						}
							
			}});
		}
		JButton btnBack = new JButton("Indietro");
		panel_8.add(btnBack, "pos 330px 242px, width 90, height 25");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_8.setVisible(false);
				new ModeratoreView(frmPiattaformaGaming, ut);
		}});
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		panel_8.add(scroll, "pos 0px 0px, width 460, height 240");
	}
}