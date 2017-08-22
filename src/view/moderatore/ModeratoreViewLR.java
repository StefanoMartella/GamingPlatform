package src.view.moderatore;

import src.controller.*;
import src.model.*;
import src.model.dao.concrete.*;

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

/**
*Class which represents the view of MVC pattern of reviews' list for moderator 
*/
public class ModeratoreViewLR{
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	
	public ModeratoreViewLR(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_9);
		panel_9.setLayout(new MigLayout());
		panel_9.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("", "[]285[]", "[][]"));
		
		List<Recensione> lr = new ModeratoreController().reviewsList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		
		for(Recensione r: lr){
				jl.add(new JLabel("Recensione " + r.getId()));
		}
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		panel_9.add(scroll, "pos 0px 0px, width 660, height 340");
		
		Object[] options = {"Pubblica", "Nega"};
		for(JLabel j: jl){
			ps.add(j);
			JButton selez = new JButton("Leggi");
			ps.add(selez, "wmin 255 , hmin 30, wrap");
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							int n = JOptionPane.showOptionDialog(frmPiattaformaGaming, new ModeratoreController().findReview(Integer.parseInt(j.getText().substring(11))).getTesto(),
							"Recensione", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
							if(n==JOptionPane.YES_OPTION){
								JOptionPane.showMessageDialog(
								frmPiattaformaGaming, new ModeratoreController(new ModeratoreController().findReview(Integer.parseInt(j.getText().substring(11)))).approve());
								selez.setVisible(false);
								j.setVisible(false);
								panel_9.setVisible(false);
								new ModeratoreViewLR(frmPiattaformaGaming, ut);
							}
							if(n==JOptionPane.NO_OPTION){
								JOptionPane.showMessageDialog(
								frmPiattaformaGaming, new ModeratoreController(new ModeratoreController().findReview(Integer.parseInt(j.getText().substring(11)))).disapprove());
								selez.setVisible(false);
								j.setVisible(false);
								panel_9.setVisible(false);
								new ModeratoreViewLR(frmPiattaformaGaming, ut);
							}
			}});
		}
		
		JButton btnBack = new JButton("Indietro");
		panel_9.add(btnBack, "pos 267px 345px, width 110, height 15");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_9.setVisible(false);
				new ModeratoreView(frmPiattaformaGaming, ut);
		}});
	}
}