package src.view.utente;

import src.controller.*;
import src.model.*;

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
	int count = 1;
	
	public UtenteViewTrofei(JFrame frame, Utente utente) {
		this.frmPiattaformaGaming=frame;
		this.ut=utente;
		initialize();
	}

	public void initialize() {
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 460, 300);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(new MigLayout());
		panel_2.setVisible(true);
		
		JLabel trof = new JLabel();
		
		ImageIcon iconSX = new ImageIcon("../Immagini/Trofei/FrecciaSX.png");
		ImageIcon iconDX = new ImageIcon("../Immagini/Trofei/FrecciaDX.png");
		ImageIcon[] icon = new ImageIcon[ut.getLivello()+1];
		
		JButton arrowSX = new JButton();
		arrowSX.setIcon(iconSX);
		panel_2.add(arrowSX, "wmax 30, hmax 22, pos 10px 139px");
		
		JButton arrowDX = new JButton();
		arrowDX.setIcon(iconDX);
		panel_2.add(arrowDX, "wmax 30, hmax 22, pos 405px 139px");
		
		JButton btnBack = new JButton("Indietro");
		panel_2.add(btnBack, "width 140, height 30, pos 160px 250px");
		
		for(int i=1; i < ut.getLivello()+1; i++)
			icon[i] = new ImageIcon("../Immagini/Trofei/Trofeo"+i+".ico");
		
		if(ut.getLivello() == 1){
			arrowSX.setVisible(false);
			arrowDX.setVisible(false);
		}
		if(ut.getLivello()>0)
			trof.setIcon(icon[count]);
		else{
			arrowSX.setVisible(false);
			arrowDX.setVisible(false);
			trof.setText("Non hai ancora collezionato trofei.");
		}
		
		panel_2.add(trof, "pos 155px 58px");
		
		arrowSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(count>1){
					trof.setIcon(icon[--count]);
					arrowSX.setVisible(true);
					if(count==1)
						arrowSX.setVisible(false);
					if(count==ut.getLivello()-1)
						arrowDX.setVisible(true);
				}
		}});
		
		arrowDX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(count<ut.getLivello()){
					trof.setIcon(icon[++count]);
					arrowDX.setVisible(true);
					if(count==ut.getLivello())
						arrowDX.setVisible(false);
					if(count==2)
						arrowSX.setVisible(true);
				}
		}});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewProfiloGaming(frmPiattaformaGaming, ut);
		}});
		
	}
}