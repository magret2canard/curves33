package curves;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		int length = fonctions.size();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,6));
		for(int i = 0; i < length; i++)
		{
			final JCheckBox cb = new JCheckBox(fonctions.get(i).toString(), true);
			cb.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(cb.isSelected())
					{
						if(!fonctions.contains(arg0.getSource()))
							fonctions.add((FunctionVariations) arg0.getSource());
					}
					else
					{
						if(fonctions.contains(arg0.getSource()))
							fonctions.remove((FunctionVariations) arg0.getSource());
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
				if(!fonctions.equals(cf.tracer.fvar))
				{
					int length = fonctions.size();
					for(int i = 0; i < length; i++)
						fonctions.get(i).tabulate(cf.controls.currentPrecision());
					cf.tracer.fvar = fonctions;
					cf.infos.fvar = fonctions;
					cf.infos.update();
				}
				
				RemoveFunction.this.dispose();
			}
		});
		add(fermer);
		
		pack();
		setVisible(true);
	}
}
