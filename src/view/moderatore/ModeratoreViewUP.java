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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

import java.util.*;
import java.sql.*;

public class ModeratoreViewUP{
	JFrame frmPiattaformaGaming;
	Utente mod;
	Utente ut_Scelto;
	
	public ModeratoreViewUP(JFrame frame, Utente ut, Utente scelto){
		this.frmPiattaformaGaming=frame;
		this.mod=ut;
		this.ut_Scelto = scelto;
		
		initialize();
	}
	
	public void initialize(){}
}