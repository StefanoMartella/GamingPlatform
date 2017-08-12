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

public class AmministratoreViewAG{
	JFrame frmPiattaformaGaming;
	Utente ut;
	
	public AmministratoreViewAG(JFrame frame, Utente ut){
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
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(15, 130, 99, 14);
		panel_9.add(lblNewLabel);
		
		JLabel lblPE = new JLabel("Punti EXP");
		lblPE.setBounds(170, 130, 99, 14);
		panel_9.add(lblPE);
		
		JTextField textField = new JTextField();
		textField.setBounds(70, 130, 86, 20);
		panel_9.add(textField);
		textField.setColumns(10);
		
		JTextField textField2 = new JTextField();
		textField2.setBounds(230, 130, 86, 20);
		panel_9.add(textField2);
		textField.setColumns(10);
		
		JButton btnA = new JButton("Aggiungi");
		btnA.setBounds(120, 170, 89, 23);
		panel_9.add(btnA);
		
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new AmministratoreController().insertGame(textField.getText(),Integer.parseInt(textField2.getText())));
				textField.setText("");
				textField2.setText("");
		}});
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(330, 227, 89, 23);
		panel_9.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_9.setVisible(false);
				new AmministratoreViewLG(frmPiattaformaGaming, ut);
		}});
	}
}