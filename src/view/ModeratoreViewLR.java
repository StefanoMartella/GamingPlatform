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

import java.util.*;
import java.sql.*;

public class ModeratoreViewLR{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public ModeratoreViewLR(JFrame frame, Utente ut){
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
		
		JPanel ps = new JPanel();
		ps.setLayout(new GridLayout(0,2, 0, 20));
		
		List<Recensione> lr = new ModeratoreController().reviewsList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		
		JLabel recen = new JLabel("Recensioni:\n");
		recen.setBounds(15,15,120,20);
		panel_9.add(recen);
		
		for(Recensione r: lr){
				jl.add(new JLabel("" + r.getId()));
		}
		
		Object[] options = {"PUBBLICA", "NEGA"};
		for(JLabel j: jl){
			ps.add(j);
			JButton selez = new JButton("LEGGI");
			selez.setSize(30,20);
			ps.add(selez);
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							int n = JOptionPane.showOptionDialog(frmPiattaformaGaming, new ModeratoreController().findReview(Integer.parseInt(j.getText())).getTesto(),
							"Recensione", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
							if(n==JOptionPane.YES_OPTION){
								JOptionPane.showMessageDialog(
								frmPiattaformaGaming, new ModeratoreController().approve(new ModeratoreController().findReview(Integer.parseInt(j.getText()))));
								selez.setVisible(false);
								j.setVisible(false);
							}
							if(n==JOptionPane.NO_OPTION){
								JOptionPane.showMessageDialog(
								frmPiattaformaGaming, new ModeratoreController().disapprove(new ModeratoreController().findReview(Integer.parseInt(j.getText()))));
								selez.setVisible(false);
								j.setVisible(false);
							}
			}});
		}
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(330, 242, 89, 18);
		panel_9.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_9.setVisible(false);
				new ModeratoreView(frmPiattaformaGaming, ut);
		}});
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setSize(460,240);
		panel_9.add(scroll);
	}
}