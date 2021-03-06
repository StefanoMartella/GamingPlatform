package src.view.amministratore;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

import src.controller.*;
import src.model.*;

/**
*Class which represents the view of MVC pattern of list of games for admin
*/
public class AmministratoreViewLG{
	private JFrame frmPiattaformaGaming;
	private Utente utente;
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*@param utente current user
	*/
	public AmministratoreViewLG(JFrame frame, Utente utente){
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
		panel_9.setLayout(new MigLayout());
		panel_9.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("", "[]270[]", "[][]"));
		
		JButton btnHome = new JButton("Home");
		panel_9.add(btnHome, "pos 500px 345px, width 110, height 20");
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setSize(460,240);
		panel_9.add(scroll, "pos 0px 0px, width 660, height 340");
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel_9.setVisible(false);
					new AmministratoreView(frmPiattaformaGaming, utente);
		}});
		
		JButton btnAG = new JButton("Inserisci");
		panel_9.add(btnAG, "pos 40px 345px, width 110, height 20");
		
		btnAG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel_9.setVisible(false);
					new AmministratoreViewAG(frmPiattaformaGaming, utente);
		}});
		
		List<Gioco> gl = new GiocoController().listOfGames();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		for( Gioco g : gl ){
				jl.add(new JLabel("" + g.getNome()));
		}
		for( JLabel j : jl ){
			ps.add(j);
			JButton selez = new JButton("Elimina");
			ps.add(selez, "wmin 250,, hmin 30, wrap");
			selez.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(frmPiattaformaGaming, new AmministratoreController(new GiocoController().findGame(j.getText())).deleteGame());
					j.setVisible(false);
					selez.setVisible(false);
					panel_9.setVisible(false);
					new AmministratoreViewLG(frmPiattaformaGaming, utente);
			}});
		}
	}
}