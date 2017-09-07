package src.view.amministratore;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import src.controller.*;
import src.model.*;

/**
*Class which represents the view of MVC pattern of game addition by admin
*/
public class AmministratoreViewAG{
	private JFrame frmPiattaformaGaming;
	private Utente utente;
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*@param utente current user
	*/
	public AmministratoreViewAG(JFrame frame, Utente utente){
		this.frmPiattaformaGaming = frame;
		this.utente = utente;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_9);
		panel_9.setLayout(null);
		panel_9.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(110, 100, 99, 14);
		panel_9.add(lblNewLabel);
		
		JLabel lblPE = new JLabel("Punti EXP");
		lblPE.setBounds(110, 160, 99, 14);
		panel_9.add(lblPE);
		
		JTextField textField = new JTextField();
		textField.setBounds(235, 100, 250, 25);
		panel_9.add(textField);
		textField.setColumns(10);
		
		JTextField textField2 = new JTextField();
		textField2.setBounds(235, 160, 250, 25);
		panel_9.add(textField2);
		textField.setColumns(10);
		
		JButton btnA = new JButton("Aggiungi");
		btnA.setBounds(235, 240, 100, 23);
		panel_9.add(btnA);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(380, 240, 100, 23);
		panel_9.add(btnBack);
		
		frmPiattaformaGaming.getRootPane().setDefaultButton(btnA);
		
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmPiattaformaGaming, new AmministratoreController().insertGame(textField.getText(),Integer.parseInt(textField2.getText())));
				textField.setText("");
				textField2.setText("");
		}});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPiattaformaGaming.getRootPane().setDefaultButton(null);
				panel_9.setVisible(false);
				new AmministratoreViewLG(frmPiattaformaGaming, utente);
		}});
	}
}