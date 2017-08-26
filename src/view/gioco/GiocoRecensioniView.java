package src.view.gioco;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

import src.controller.*;
import src.model.*;

/**
*Class which represents the view of MVC pattern of game reviews' list
*/
public class GiocoRecensioniView {
	
	private JFrame frmPiattaformaGaming;
	private Gioco gioco;
	private Utente utente;
	
	public GiocoRecensioniView(JFrame frame, Utente utente, Gioco gioco){
		this.frmPiattaformaGaming = frame;
		this.gioco = gioco;
		this.utente = utente;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_7);
		panel_7.setLayout(new MigLayout());
		panel_7.setVisible(true);
		
		JTextArea ta = new JTextArea();
		ta.setEditable(false);
		panel_7.add(ta);
		
		ArrayList<Recensione> al = new GiocoController(gioco).allReviews();
		for( Recensione r : al ){
			ta.setText(ta.getText() + "  - " +  r.getTesto() + "\n\n");
		}
		
		JButton btnBack = new JButton("Indietro");
		panel_7.add(btnBack, "pos 267px 345px, width 110, height 15");
		
		JScrollPane scroll = new JScrollPane(ta);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setSize(460,240);
		panel_7.add(scroll, "pos 0px 0px, width 660, height 340");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_7.setVisible(false);
				new GiocoView(frmPiattaformaGaming,utente,gioco);
		}});
	}
}