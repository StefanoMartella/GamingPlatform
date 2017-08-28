package src.view.utente;

import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import src.controller.*;
import src.model.*;
import src.view.moderatore.*;

/**
*Class which represents the view of MVC pattern of user's gaming profile
*/
public class UtenteViewProfiloGaming{
	private JFrame frmPiattaformaGaming;
	private Utente utente;
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*@param utente current user
	*/
	public UtenteViewProfiloGaming(JFrame frame, Utente utente){
		this.frmPiattaformaGaming = frame;
		this.utente = utente;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(true);
		
		JLabel gaming = new JLabel("Profilo Gaming");
		gaming.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		gaming.setBounds(250, 10, 200, 25);
		panel_1.add(gaming);
		
		JLabel lblNewLabel_3 = new JLabel("Username:");
		lblNewLabel_3.setBounds(20, 75, 114, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblLivello_1 = new JLabel("Livello:");
		lblLivello_1.setBounds(20, 115, 114, 14);
		panel_1.add(lblLivello_1);
		
		JLabel lblLivello = new JLabel("Punti EXP:");
		lblLivello.setBounds(20, 155, 114, 14);
		panel_1.add(lblLivello);
		
		JLabel lblTrofei = new JLabel("Trofei:");
		lblTrofei.setBounds(20, 200, 114, 14);
		panel_1.add(lblTrofei);
		
		JLabel lblNewLabel_4 = new JLabel(utente.getUsername());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(200, 75, 166, 20);
		panel_1.add(lblNewLabel_4);
		
		JLabel label = new JLabel("" + utente.getLivello());
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(200, 115, 166, 20);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("" + utente.getPuntiExp());
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(200, 155, 166, 20);
		panel_1.add(label_1);
		
		JButton trofei = new JButton("Visualizza trofei");
		if( utente.getLivello() == 0 )
			trofei.setBackground(Color.LIGHT_GRAY);
		trofei.setBounds(200, 192, 124, 25);
		panel_1.add(trofei);
		
		JLabel lblTimeline = new JLabel("Timeline:");
		lblTimeline.setBounds(20, 260, 166, 25);
		panel_1.add(lblTimeline);
		
		TreeMap<Integer,String> timeline = new UtenteController().timeline(utente.getUsername());
		
		String[] column_names = { "Data", "Livello" };
		Object[][] timelineTable = new Object[timeline.size()][2];
		int count = 0;
		for( Map.Entry<Integer,String> entry : timeline.entrySet() ){
			timelineTable[count][0] = entry.getValue();
			timelineTable[count][1] = entry.getKey();
			count++;
		}

		JTable showTimeline = new JTable(timelineTable, column_names);
		JScrollPane scroll = new JScrollPane(showTimeline);
		scroll.setBounds(200, 230, 320, 85);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		panel_1.add(scroll);

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(540, 340, 100, 25);
		panel_1.add(btnHome);
		
		frmPiattaformaGaming.getRootPane().setDefaultButton(trofei);
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( utente.getTipo().equals("moderatore") ){
					frmPiattaformaGaming.getRootPane().setDefaultButton(null);
					panel_1.setVisible(false);
					new ModeratoreView(frmPiattaformaGaming, utente);
				}
				else{
					frmPiattaformaGaming.getRootPane().setDefaultButton(null);
					panel_1.setVisible(false);
					new UtenteView(frmPiattaformaGaming, utente);
				}
		}});
		
		trofei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( utente.getLivello() < 1 )
					JOptionPane.showMessageDialog(frmPiattaformaGaming, "Non hai ancora collezionato trofei!", "No trofei", JOptionPane.ERROR_MESSAGE);
				else{
					frmPiattaformaGaming.getRootPane().setDefaultButton(null);
					panel_1.setVisible(false);
					new UtenteViewTrofei(frmPiattaformaGaming, utente);
				}
		}});
	}
}
