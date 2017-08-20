package src.view.gioco;

import src.controller.*;
import src.model.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

import java.util.*;
import java.sql.*;

/**
*Class which represents the view of MVC pattern of game reviews' list
*/
public class GiocoRecensioniView {
	
	private JFrame frmPiattaformaGaming;
	private Gioco gioco;
	private Utente utente;
	
	public GiocoRecensioniView(JFrame frame, Utente utente, Gioco gioco){
		this.frmPiattaformaGaming=frame;
		this.gioco=gioco;
		this.utente=utente;
		
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 0, 465, 300);
		frmPiattaformaGaming.getContentPane().add(panel_7);
		panel_7.setLayout(new MigLayout());
		panel_7.setVisible(true);
		
		JTextArea ta = new JTextArea();
		ta.setEditable(false);
		panel_7.add(ta);
		
		ArrayList<Recensione> al = new GiocoController(gioco).allReviews();
		for(Recensione r: al){
			ta.setText(ta.getText() + "  - " +  r.getTesto() + "\n\n");
		}
		
		JButton btnBack = new JButton("Indietro");
		panel_7.add(btnBack, "pos 339px 250px, width 90, height 20");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_7.setVisible(false);
				new GiocoView(frmPiattaformaGaming,utente,gioco);
		}});
		
		JScrollPane scroll = new JScrollPane(ta);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setSize(460,240);
		panel_7.add(scroll, "pos 0px 0px, width 460, height 240");
	}
}