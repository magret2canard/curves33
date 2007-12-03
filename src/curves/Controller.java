package curves;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public class Controller extends CurveControls {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param var
	 * @param f
	 */
	public Controller(final List<FunctionVariations> var, final CurveFrame f) {
		// TODO Auto-generated constructor stub
		super(var, f);

		JButton zoomPlus = new JButton("+");
		JButton zoomMoins = new JButton("-");
		zoomPlus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Controller.this.f.zoom(CurveFrame.ZOOM_PLUS);
				}catch(IndexOutOfBoundsException e1){
				}
			}
		});
		zoomMoins.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Controller.this.f.zoom(CurveFrame.ZOOM_MOINS);
				}catch(IndexOutOfBoundsException e1){
				}
			}
		});
		JPanel panel1 = new JPanel(new GridLayout(0,2));
		panel1.add(zoomPlus);
		panel1.add(zoomMoins);
		add(panel1);
		
		JButton aGauche = new JButton("A Gauche");
		aGauche.setToolTipText("d�placement gauche");
		JButton aDroite = new JButton("A Droite");
		aDroite.setToolTipText("d�placement droite");
		aGauche.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Controller.this.f.deplacement(CurveFrame.DECALAGE_GAUCHE);
				}catch(IndexOutOfBoundsException e1){
				}
			}
		});
		aDroite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Controller.this.f.deplacement(CurveFrame.DECALAGE_DROIT);
				}catch(IndexOutOfBoundsException e1){
				}
			}
		});
		JPanel panel2 = new JPanel(new GridLayout(0,2));
		panel2.add(aGauche);
		panel2.add(aDroite);
		add(panel2);
		
		JButton addFunction = new JButton("Ajouter une Fonction");
		JButton removeFunction = new JButton("Retirer une Fonction");
		addFunction.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CurveDialMulti cdm = new CurveDialMulti(Controller.this.f);
				cdm.setBounds(Controller.this.f.getX() +30 % 90, Controller.this.f.getY()+30 % 90, cdm.getWidth(), cdm.getHeight());
			}
		});
		removeFunction.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Controller.this.f.infos.fvar.isEmpty())
					JOptionPane.showMessageDialog(Controller.this.f, "Il n'y a pas de fonction affichée." +
							"\n");
				else
				{
					RemoveFunction rf = new RemoveFunction(Controller.this.f);
					rf.setBounds(Controller.this.f.getX() +30 % 90, Controller.this.f.getY()+30 % 90, rf.getWidth(), rf.getHeight());
				}
				
			}
		});
		JPanel panel3 = new JPanel(new GridLayout(0,2));
		panel3.add(addFunction);
		panel3.add(removeFunction);
		add(panel3);
		
		setLayout(new GridLayout(0,1));
	}
}
