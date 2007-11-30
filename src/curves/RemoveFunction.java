package curves;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RemoveFunction extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoveFunction(CurveFrame cf) {
		// TODO Auto-generated constructor stub
		super("Fonction à supprimer");
		setLayout(new GridLayout(0,1));
		
		FunctionVariations[] fonctions  = cf.tracer.fvar;
		int length = fonctions.length;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,6));
		for(int i = 0; i < length; i++)
		{
			//JCheckBox cb = new JCheckBox(fonctions[i].toString(), true);
			panel.add(new JCheckBox(fonctions[i].toString(), true));
		}
		add(panel);
		
		JButton fermer = new JButton("OK");
		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//Ajouter les listes
				RemoveFunction.this.dispose();
			}
		});
		add(fermer);
		
		pack();
		setVisible(true);
	}
}
