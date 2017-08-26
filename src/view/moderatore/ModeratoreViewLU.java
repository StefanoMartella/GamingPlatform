package src.view.moderatore;

import src.controller.*;
import src.model.*;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

/**
*Class which represents the view of MVC pattern of users' list for moderator
*/
public class ModeratoreViewLU{
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	
	public ModeratoreViewLU(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_8);
		panel_8.setLayout(new MigLayout());
		panel_8.setVisible(true);
		
		JPanel ps = new JPanel();
		ps.setLayout(new MigLayout("", "[]280[]", "[][]"));
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		panel_8.add(scroll, "pos 0px 0px, width 660, height 340");
		
		List<Utente> lU = new ModeratoreController().usersList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		for(Utente u: lU){
			if(!(u.getTipo().equals("amministratore") || u.getUsername().equals(ut.getUsername())))
				jl.add(new JLabel(u.getUsername()));
		}
		for(JLabel j: jl){
			ps.add(j);
			JButton selez;
			String type = new GestioneUtenza().findUser(j.getText()).getTipo();
			if(type.equals("moderatore"))
				selez = new JButton("Retrocedi");
			else
				selez = new JButton("Promuovi");
			ps.add(selez,"wmin 250, hmin 30, wrap");
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selez.getText().equals("Retrocedi")){
							new ModeratoreController(new GestioneUtenza().findUser(j.getText())).demote();
							JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Moderatore retrocesso.");
							selez.setText("Promuovi");
						}
						else{
							new ModeratoreController(new GestioneUtenza().findUser(j.getText())).promote();
							JOptionPane.showMessageDialog(
							frmPiattaformaGaming, "Utente promosso!");
							selez.setText("Retrocedi");
						}
							
			}});
		}
		JButton btnBack = new JButton("Indietro");
		panel_8.add(btnBack, "pos 267px 345px, width 110, height 15");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_8.setVisible(false);
				new ModeratoreView(frmPiattaformaGaming, ut);
		}});
	}
}
