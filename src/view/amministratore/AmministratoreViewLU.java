package src.view.amministratore;

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
*Class which represents the view of MVC pattern of users' list of admin
*/
public class AmministratoreViewLU{
	private JFrame frmPiattaformaGaming;
	private Utente ut;
	
	public AmministratoreViewLU(JFrame frame, Utente ut){
		this.frmPiattaformaGaming=frame;
		this.ut=ut;
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
		ps.setLayout(new MigLayout("", "[]285[]", "[][]"));
		
		JScrollPane scroll = new JScrollPane(ps);
		scroll.setVerticalScrollBarPolicy ( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		scroll.setSize(460,240);
		panel_9.add(scroll, "width 660, height 340, pos 0px 0px");
		
		JButton btnHome = new JButton("Home");
		panel_9.add(btnHome, "pos 267px 345px, width 110, height 20");
		
		List<Utente> lU = new ModeratoreController().usersList();
		ArrayList<JLabel> jl = new ArrayList<JLabel>();
		for(Utente u: lU){
			if(!u.getTipo().equals("amministratore"))
				jl.add(new JLabel("" + u.getUsername()));
		}
		for(JLabel j: jl){
			ps.add(j);
			JButton selez = new JButton("Elimina");
			ps.add(selez, "wmin 250, hmin 30, wrap");
				selez.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(
							frmPiattaformaGaming, new AmministratoreController(new GestioneUtenza().findUser(j.getText())).deleteUser());
							j.setVisible(false);
							selez.setVisible(false);
							panel_9.setVisible(false);
							new AmministratoreViewLU(frmPiattaformaGaming, ut);
			}});
		}
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_9.setVisible(false);
				new AmministratoreView(frmPiattaformaGaming, ut);
		}});
	}
}