package src.view.gioco;

import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;

import src.controller.*;
import src.model.*;
import src.view.utente.*;

/**
*Class which represents the view of MVC pattern for the game
*/
public class GiocoView{
	private JFrame frmPiattaformaGaming;
	private Utente utente;
	private Gioco gioco;
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*@param utente current user
	*@param gioco current game
	*/
	public GiocoView(JFrame frame, Utente utente, Gioco gioco){
		this.frmPiattaformaGaming = frame;
		this.utente = utente;
		this.gioco = gioco;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_6);
		panel_6.setLayout(null);
		panel_6.setVisible(true);
		
		JLabel lblNewLabel_3 = new JLabel("Nome:");
		lblNewLabel_3.setBounds(55, 20, 114, 14);
		panel_6.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Esperienza fornita:");
		lblNewLabel_4.setBounds(460, 20, 110, 14);
		panel_6.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(gioco.getNome());
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(110, 17, 150, 20);
		panel_6.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("" + gioco.getExp());
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(580, 17, 30, 15);
		panel_6.add(lblNewLabel_6);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(50, 50, 560, 80);
		textPane.setToolTipText("Recensisci " + gioco.getNome());
		panel_6.add(textPane);
		
		JButton btnReview = new JButton("Recensisci");
		btnReview.setBounds(50, 150, 130, 25);
		panel_6.add(btnReview);
		
		JButton btnvReview = new JButton("Vedi recensioni");
		btnvReview.setBounds(480, 150, 130, 25);
		if( new GiocoController(gioco).allReviews().isEmpty() )
			btnvReview.setBackground(Color.LIGHT_GRAY);
		panel_6.add(btnvReview);
		
		JSpinner seleziona_voto = new JSpinner();
		seleziona_voto.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		seleziona_voto.setBounds(255,250,45,25);
		panel_6.add(seleziona_voto);
		
		JButton btnVota = new JButton("Vota");
		btnVota.setBounds(300, 250, 100, 25);
		panel_6.add(btnVota);
		
		JLabel mediaVoti = new JLabel("Media voti:");
		mediaVoti.setBounds(280,195,100,30);
		panel_6.add(mediaVoti);
		
		DecimalFormat df = new DecimalFormat("###.#");
		JLabel average = new JLabel(df.format(new GiocoController(gioco).avgVote()));
		average.setFont(new Font("Tahoma", Font.BOLD, 18));
		average.setBounds(350,195,30,30);
		panel_6.add(average);
		
		JButton btnGioca = new JButton("Gioca");
		btnGioca.setBounds(180, 320, 100, 25);
		panel_6.add(btnGioca);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBounds(360, 320, 100, 25);
		panel_6.add(btnBack);
		
		
		btnGioca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lvl = utente.getLivello();
				new UtenteController(utente, gioco).play();
				JOptionPane.showMessageDialog(frmPiattaformaGaming, "Hai giocato!");
				if( utente.getLivello() > lvl ){
					JPanel opP = new JPanel();
					opP.setLayout(new BorderLayout());
					JLabel text = new JLabel();
					text.setText("<html><body>Complimenti, hai raggiunto il livello " + utente.getLivello() + "<br>Hai ottenuto un trofeo!</body></html>");
					ImageIcon icon = new ImageIcon(getClass().getResource("../img/Trofeo" + utente.getLivello() + ".png"));
					JLabel ico = new JLabel(icon);
					opP.add(ico,BorderLayout.SOUTH);
					opP.add(text,BorderLayout.EAST);
					JOptionPane.showMessageDialog(frmPiattaformaGaming, opP, "Nuovo livello",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(  textPane.getText().trim().isEmpty() ){
					JOptionPane.showMessageDialog(frmPiattaformaGaming, "La recensione non puo' essere vuota!", "Recensione vuota", JOptionPane.ERROR_MESSAGE);
					textPane.setText("");
				}
				else if( textPane.getText().length() > (239 - utente.getUsername().length()) ){
					JOptionPane.showMessageDialog(frmPiattaformaGaming, "La recensione puo' essere di massimo 200 caratteri!", "Recensione troppo lunga", JOptionPane.ERROR_MESSAGE);
					textPane.setText("");
				}
				else{
					JOptionPane.showMessageDialog(frmPiattaformaGaming, new UtenteController(utente, gioco).review("Recensione di " + utente.getUsername() + ":\n" + textPane.getText()));
					if( !new GiocoController(gioco).allReviews().isEmpty() )
						btnvReview.setBackground(null);
					textPane.setText("");
				}
		}});
		
		btnvReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( new GiocoController(gioco).allReviews().isEmpty() )
					JOptionPane.showMessageDialog(frmPiattaformaGaming, "Non ci sono ancora recensioni per questo gioco!", "No recensioni", JOptionPane.ERROR_MESSAGE);
				else{
					panel_6.setVisible(false);
					new GiocoRecensioniView(frmPiattaformaGaming, utente, gioco);
				}
		}});
		
		btnVota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmPiattaformaGaming, new UtenteController(utente, gioco).vote((int) seleziona_voto.getValue()));
				average.setText(df.format(new GiocoController(gioco).avgVote()));
				textPane.setText("");
		}
		});
		
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panel_6.setVisible(false);
				new UtenteViewGL(frmPiattaformaGaming, utente);
		}});
		
	}
}
