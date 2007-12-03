package curves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Cette classe créer le menu
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public class CurveMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final CurveFrame f;
	
	public CurveMenuBar(final CurveFrame f) {
		// TODO Auto-generated constructor stub
		this.f = f;
		JMenu fichier = new JMenu("Fichier");
		JMenuItem nouveau = new JMenuItem("Nouveau", KeyEvent.VK_N);
		JMenuItem ouvrir = new JMenuItem("Ouvrir", KeyEvent.VK_O);
		JMenuItem enregistrer = new JMenuItem("Enregistrer", KeyEvent.VK_S);
		JMenuItem fermer = new JMenuItem("Fermer", KeyEvent.VK_F);
		
		nouveau.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				creerNouveau();
			}
		});
		ouvrir.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ouvrirFileChooser();
			}
		});
		enregistrer.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				enregistrer();
			}
		});
		fermer.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});
		
		fichier.add(nouveau);
		fichier.add(ouvrir);
		fichier.add(enregistrer);
		fichier.addSeparator();
		fichier.add(fermer);
		
		JMenu aide = new JMenu("Aide");
		JMenuItem aPropos = new JMenuItem("A Propos");
		
		aPropos.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(f, "Ce logiciel est proposé par :\n" +
						"Ewans Edouard et\n" +
						"Védrenne Benoît\n" +
						"\n" +
						"Nous vous remercions de l'utiliser.\n" +
						"\n");
			}
		});
		aide.add(aPropos);
		
		add(fichier);
		add(aide);
	}

	private void ouvrirFileChooser() {
		// TODO Auto-generated method stub
		JFrame fenetre = new JFrame("Fichier");
		fenetre.setBounds(f.getX()+30 % 90, f.getY()+30 % 90, f.getHeight(), f.getWidth());
		CurveFichierOuverture jfc = new CurveFichierOuverture(f, fenetre);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		JPanel panel = new JPanel();
		
		panel.add(jfc);
		fenetre.add(panel);
		
		fenetre.pack();
		fenetre.setVisible(true);
	}	
	
	private void enregistrer(){ //ça ne marche pas
		final JFrame frame = new JFrame("Enregistrer");
		JPanel pane = new JPanel();
		CurveFichierEcriture jfc = null;
		try {
			jfc = new CurveFichierEcriture(f, frame);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pane.add(jfc);
		frame.add(pane);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		frame.setBounds(f.getX()+30 % 90, f.getY()+30 % 90, f.getHeight(), f.getWidth());
		frame.pack();
		frame.setVisible(true);
	}

	private void creerNouveau() {
		// TODO Auto-generated method stub
		CurveDialMulti nouveau = new CurveDialMulti();
		nouveau.setBounds(this.getX() +30 % 90, this.getY()+30 % 90, nouveau.getWidth(), nouveau.getHeight());
	}
}
