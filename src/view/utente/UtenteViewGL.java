package src.view.utente;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

import src.controller.*;
import src.model.*;
import src.view.moderatore.*;
import src.view.gioco.*;

/**
*Class which represents the view of MVC pattern of games' list for user
*/
public class UtenteViewGL{
	private JFrame frmPiattaformaGaming;
	private Utente utente;
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*@param utente current user
	*/
	public UtenteViewGL(JFrame frame, Utente utente){
		this.frmPiattaformaGaming = frame;
		this.utente = utente;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_5);
		panel_5.setLayout(new MigLayout());
		panel_5.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("center center, wrap, gapy 5"));
		
		List<Gioco> gl = new GiocoController().listOfGames();
		ArrayList<JButton> jl = new ArrayList<JButton>();
		for( Gioco g: gl ){
				jl.add(new JButton(g.getNome()));
		}
		for( JButton j : jl ){
			ps.add(j, "width 620, height 35");
			j.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.setVisible(false);
				new GiocoView(frmPiattaformaGaming, utente, new GiocoController().findGame(j.getText()));
			}});
		}
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		panel_5.add(scroll, "pos 0px 0px, width 660, height 340");
		
		
		JButton btnHome = new JButton("Home");
		panel_5.add(btnHome, "pos 267px 345px, width 110, height 15");

		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( utente.getTipo().equals("moderatore") ){
					panel_5.setVisible(false);
					new ModeratoreView(frmPiattaformaGaming, utente);
				}
				else{
					panel_5.setVisible(false);
					new UtenteView(frmPiattaformaGaming, utente);
				}
		}});
	}
}

