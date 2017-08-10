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

import java.util.*;
import java.sql.*;

public class AmministratoreViewLU{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public AmministratoreViewLU(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	
	public void initialize(){
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(0, 0, 434, 261);
		frmPiattaformaGaming.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		panel_9.setVisible(true);
		
		List<Utente> lU = new ModeratoreController().usersList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		for(Utente u: lU){
			if(!u.getTipo().equals("amministratore"))
				jl.add(new JLabel(u.getUsername()));
		}
		int i=0;
		for(JLabel j: jl){
			j.setBounds(15,15+25*i,120,20);
			panel_9.add(j);
			i++;
			JButton selez = new JButton("ELIMINA");
			selez.setBounds(200,j.getY(),100,20);
			panel_9.add(selez);
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new AmministratoreController(new GestioneUtenza().findUser(j.getText())).deleteUser());
							j.setVisible(false);
							selez.setVisible(false);
			}});
		}
		
		JButton btnBack = new JButton("HOME");
		btnBack.setBounds(330, 227, 89, 23);
		panel_9.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_9.setVisible(false);
				new AmministratoreView(frmPiattaformaGaming, ut);
		}});
	}
}