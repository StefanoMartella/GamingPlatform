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
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

import java.util.*;
import java.sql.*;


public class UtenteViewTrofei {
	
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public UtenteViewTrofei(JFrame frame, Utente utente) {
		this.frmPiattaformaGaming=frame;
		this.ut=utente;
		initialize();
	}

	public void initialize() {
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 465, 300);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(new MigLayout());
		panel_2.setVisible(true);
		
		JLabel trof = new JLabel();
		
		ImageIcon iconSX = new ImageIcon("../Immagini/Trofei/FrecciaSX.png");
		ImageIcon iconDX = new ImageIcon("../Immagini/Trofei/FrecciaDX.png");
		ImageIcon[] icon = new ImageIcon[ut.getLivello()];
		
		for(int i=0; i < ut.getLivello(); i++)
			icon[i] = new ImageIcon("../Immagini/Trofei/Trofeo"+i+".ico");
		
		if(ut.getLivello()>0)
			trof =  new JLabel(new ImageIcon("../Immagini/Trofei/Trofeo.png"));
		
		//trof.setBounds(195,100,100,100);
		panel_2.add(trof, "pos 100px 100px");
		
		JButton arrowSX = new JButton();
		//arrowSX.setBounds(10,139,30,22);
		arrowSX.setIcon(iconSX);
		panel_2.add(arrowSX, "pos 10px 139px");
		
		JButton arrowDX = new JButton();
		//arrowDX.setBounds(420,139,30,22);
		arrowDX.setIcon(iconDX);
		panel_2.add(arrowDX, "pos 420px 139px");
		
		JButton btnBack = new JButton("Indietro");
		//btnBack.setBounds(185, 250, 90, 23);
		panel_2.add(btnBack, "pos 185px 250px");
		
		arrowSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
		}});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewProfiloGaming(frmPiattaformaGaming, ut);
		}});
		
	}
}