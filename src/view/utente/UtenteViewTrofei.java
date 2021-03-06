package src.view.utente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

import src.model.*;

/**
*Class which represents the view of MVC pattern of trophies' list of a user
*/
public class UtenteViewTrofei {
	
	private JFrame frmPiattaformaGaming;
	private Utente utente;
	private int count = 1;
	
	/**
	*Class constructor
	*
	*@param frame initial frame
	*@param utente current user
	*/
	public UtenteViewTrofei(JFrame frame, Utente utente) {
		this.frmPiattaformaGaming = frame;
		this.utente = utente;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 665, 415);
		frmPiattaformaGaming.getContentPane().add(panel_2);
		panel_2.setLayout(new MigLayout());
		panel_2.setVisible(true);
		
		JLabel trof = new JLabel();
		
		ImageIcon iconSX = new ImageIcon(getClass().getResource("../img/FrecciaSX.png"));
		ImageIcon iconDX = new ImageIcon(getClass().getResource("../img/FrecciaDX.png"));
		ImageIcon[] icon = new ImageIcon[utente.getLivello()+1];
		
		JButton arrowSX = new JButton();
		arrowSX.setIcon(iconSX);
		arrowSX.setVisible(false);
		panel_2.add(arrowSX, "wmax 50, hmax 25, pos 10px 200px");
		
		JButton arrowDX = new JButton();
		arrowDX.setIcon(iconDX);
		panel_2.add(arrowDX, "wmax 50, hmax 25, pos 600px 200px");
		
		JButton btnBack = new JButton("Indietro");
		panel_2.add(btnBack, "width 140, height 20, pos 250px 350px");
		
		for( int i=1; i < utente.getLivello()+1; i++ )
			icon[i] = new ImageIcon(getClass().getResource("../img/Trofeo" + i + ".png"));
		
		trof.setIcon(icon[count]);
		
		if( utente.getLivello() == 1 ){
			arrowSX.setVisible(false);
			arrowDX.setVisible(false);
		}
		
		panel_2.add(trof, "pos 263px 0px, width 175, height 400");
		
		arrowSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( count > 1 ){
					trof.setIcon(icon[--count]);
					arrowSX.setVisible(true);
					if( count == 1 )
						arrowSX.setVisible(false);
					if( count == utente.getLivello()-1 )
						arrowDX.setVisible(true);
				}
		}});
		
		arrowDX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( count < utente.getLivello() ){
					trof.setIcon(icon[++count]);
					arrowDX.setVisible(true);
					if( count == utente.getLivello() )
						arrowDX.setVisible(false);
					if( count == 2 )
						arrowSX.setVisible(true);
				}
		}});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				new UtenteViewProfiloGaming(frmPiattaformaGaming, utente);
		}});
		
	}
}