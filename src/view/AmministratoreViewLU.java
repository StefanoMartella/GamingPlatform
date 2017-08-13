package view;

import controller.*;
import model.*;

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
import java.awt.Dimension;

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
		panel_9.setBounds(0, 0, 465, 300);
		frmPiattaformaGaming.getContentPane().add(panel_9);
		panel_9.setLayout(new MigLayout());
		panel_9.setVisible(true);

		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("", "[]185[]", "[][]"));
		
		int i=0;
		
		List<Utente> lU = new ModeratoreController().usersList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		for(Utente u: lU){
			if(!u.getTipo().equals("amministratore"))
				jl.add(new JLabel("" + u.getUsername()));
		}
		for(JLabel j: jl){
			ps.add(j);
			i++;
			JButton selez = new JButton("Elimina");
			ps.add(selez, "wmin 180, wrap");
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new AmministratoreController(new GestioneUtenza().findUser(j.getText())).deleteUser());
							j.setVisible(false);
							selez.setVisible(false);
			}});
		}
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setSize(460,240);
		panel_9.add(scroll, "width 460, height 240, pos 0px 0px");
		
		JButton btnHome = new JButton("Home");
		panel_9.add(btnHome, "pos 339px 250px, width 90, height 20");
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_9.setVisible(false);
				new AmministratoreView(frmPiattaformaGaming, ut);
		}});
	}
}