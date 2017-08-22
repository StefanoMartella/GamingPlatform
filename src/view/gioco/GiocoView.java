package src.view.gioco;

import src.controller.*;
import src.model.*;
import src.view.utente.*;

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
import java.awt.BorderLayout;
import java.awt.Font;

/**
*Class which represents the view of MVC pattern for the game
*/
public class GiocoView{
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	private Gioco gioco;
	
	public GiocoView(JFrame frame, Utente ut, Gioco g){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		this.gioco=g;
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
		panel_6.add(textPane);
		
		JButton btnReview = new JButton("Recensisci");
		btnReview.setBounds(50, 150, 130, 25);
		panel_6.add(btnReview);
		
		JButton btnvReview = new JButton("Vedi recensioni");
		btnvReview.setBounds(480, 150, 130, 25);
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
					int lvl = ut.getLivello();
					new UtenteController(ut,gioco).play();
					JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Hai giocato!");
					if(ut.getLivello()>lvl){
						JPanel opP = new JPanel();
						opP.setLayout(new BorderLayout());
						JLabel text = new JLabel();
						text.setText("<html><body>Complimenti, hai raggiunto il livello " + ut.getLivello() +
							"<br>Hai ottenuto un trofeo!</body></html>");
						ImageIcon icon = new ImageIcon(getClass().getResource("../img/Trofeo"+ ut.getLivello() +".png"));
						JLabel ico = new JLabel(icon);
						opP.add(ico,BorderLayout.SOUTH);
						opP.add(text,BorderLayout.EAST);
						JOptionPane.showMessageDialog(
							frmPiattaformaGaming, opP, "Nuovo livello",
							JOptionPane.PLAIN_MESSAGE);
					}
			}
		});
		
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new UtenteController(ut,gioco).review("Recensione di " + ut.getUsername() + ":\n" + textPane.getText()));
							textPane.setText("");
		}});
		
		btnvReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_6.setVisible(false);
				new GiocoRecensioniView(frmPiattaformaGaming,ut,gioco);
		}});
		
		btnVota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new UtenteController(ut,gioco).vote((int) seleziona_voto.getValue()));
							average.setText(df.format(new GiocoController(gioco).avgVote()));
							textPane.setText("");
		}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_6.setVisible(false);
				new UtenteViewGL(frmPiattaformaGaming, ut);
		}});
		
	}
}
