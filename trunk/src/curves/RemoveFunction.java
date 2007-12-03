package curves;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RemoveFunction extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoveFunction(final CurveFrame cf) {
		// TODO Auto-generated constructor stub
		super("Fonction à supprimer");
		setLayout(new GridLayout(0,1));
		
		final List<FunctionVariations> fonctions  = cf.tracer.fvar;
		final List<FunctionVariations> l = new ArrayList<FunctionVariations>();
		l.addAll(fonctions);
		int length = fonctions.size();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,6));
		
		for(int i = 0; i < length; i++)
		{
			final JCheckBox cb = new JCheckBox(fonctions.get(i).toString(), true);
			cb.addActionListener(new ActionListener(){
				
				
				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(cb.isSelected())
					{
						int length = fonctions.size();
						for(int i = 0; i < length; i++)
							if(fonctions.get(i).toString().compareTo(cb.getText()) == 0)
								l.add(fonctions.get(i));
					}
					else
					{
						int length = fonctions.size();
						for(int i = 0; i < length; i++)
							if(fonctions.get(i).toString().compareTo(cb.getText()) == 0)
								l.remove(fonctions.get(i));
					}
				}
			});
			
			panel.add(cb);
		}
		add(panel);
		
		JButton fermer = new JButton("OK");
		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int length = l.size();
				for(int i = 0; i < length; i++)
					fonctions.get(i).tabulate(cf.controls.currentPrecision());
				cf.tracer.fvar = l;
				cf.infos.fvar = l;
				try{
					cf.infos.update();
				}catch(IndexOutOfBoundsException e){
				}
				
				
				RemoveFunction.this.dispose();
			}
		});
		add(fermer);
		
		pack();
		setVisible(true);
	}
}
