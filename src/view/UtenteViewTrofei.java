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

import java.util.*;
import java.sql.*;


public class UtenteViewTrofei {
	
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public UtenteViewTrofei(JFrame frame, Utente ut) {
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}

	public void initialize() {
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 460, 300);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(true);
		
		JLabel trof;
		
		ImageIcon iconSX = new ImageIcon("../Immagini/Trofei/FrecciaSX.png");
		ImageIcon iconDX = new ImageIcon("../Immagini/Trofei/FrecciaDX.png");
		ImageIcon[] icon = new ImageIcon[ut.getLivello()];
		
		for(int i=0; i < ut.getLivello(); i++)
			icon[i] = new ImageIcon("../Immagini/Trofei/Trofeo"+i+".png");
		
		if(ut.getLivello()>0)
			trof =  new JLabel(icon[0]);
		
		JButton arrowSX = new JButton();
		arrowSX.setBounds(200,10,60,60);
		arrowSX.setIcon(iconSX);
		panel_2.add(arrowSX);
		
		JButton arrowDX = new JButton();
		arrowDX.setBounds(200,230,60,60);
		arrowDX.setIcon(iconDX);
		panel_2.add(arrowDX);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(250, 227, 89, 23);
		panel_2.add(btnBack);
		
		arrowSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
		}});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewGL(frmPiattaformaGaming, ut);
		}});
		
	}
}