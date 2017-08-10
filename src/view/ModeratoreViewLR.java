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
		
		List<Recensione> lr = new ModeratoreController().reviewsList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		
		JLabel recen = new JLabel("Recensioni:\n");
		recen.setBounds(15,15,120,20);
		panel_9.add(recen);
		
		for(Recensione r: lr){
				jl.add(new JLabel("" + r.getId()));
		}
		
		Object[] options = {"PUBBLICA", "NEGA"};
		int i=0;
		for(JLabel j: jl){
			j.setBounds(15,30+25*i,120,20);
			panel_9.add(j);
			i++;
			JButton selez = new JButton("LEGGI");
			selez.setBounds(200,j.getY(),100,20);
			panel_9.add(selez);
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
		btnBack.setBounds(330, 227, 89, 23);
		panel_9.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_9.setVisible(false);
				new ModeratoreView(frmPiattaformaGaming, ut);
		}});
	}
}