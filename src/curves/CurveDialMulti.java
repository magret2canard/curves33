package curves;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import function.Fonction;
import function.Function;
import function.Functions;
import function.SyntaxErrorException;

/**
 * 
 * @author Ewans Edouard
 * @author V�drenne Beno�t
 *
 */
public class CurveDialMulti extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7984853357394808311L;
	String fun = null;
	String minimum = null;
	String maximum = null;
	
	public CurveDialMulti(){
		this(null);
	}
	
	public CurveDialMulti(CurveFrame cv) {
		// TODO Auto-generated constructor stub
		super("Nouvelle fonction");
		final CurveFrame curve = cv;
		JPanel pane = new JPanel();
		final JTextField fonction = new JTextField(20);
		final JTextField min = new JTextField(10);
		final JTextField max = new JTextField(10);
		JButton ok = new JButton("OK");
		JButton annuler = new JButton("Annuler");
		
		ok.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fun = fonction.getText();
				maximum = max.getText();
				minimum = min.getText();
				try{
					if(maximum.compareTo("") == 0)
						maximum = String.valueOf(curve.infos.fvar.get(0).getXmax());
					if(minimum.compareTo("") == 0)
						minimum = String.valueOf(curve.infos.fvar.get(0).getXmin());
				
					try { //Nouvelle fenetre
						double tempMin, tempMax;
						Function f = Functions.parse(fun);
						tempMin = Fonction.parseDouble(minimum);
						tempMax = Fonction.parseDouble(maximum);
						double max = Math.max(tempMin, tempMax);
						double min = Math.min(tempMin, tempMax);
						if(curve == null)
						{
							List<FunctionVariations> fvars = new ArrayList<FunctionVariations>();
							fvars.add(new FunVariationNamed(f, min, max, fun));
							CurveFrame cv = new CurveFrame(fvars);
							cv.setBounds(CurveDialMulti.this.getX()+30 % 90, CurveDialMulti.this.getY()+30 % 90, cv.getWidth(), cv.getHeight());
						}
						else //Ajout d'une fonction
						{
							List<FunctionVariations> fvar = curve.infos.fvar;
							FunVariationNamed fv = new FunVariationNamed(f, min, max, fun);
							
							boolean exist = false;
							int length = fvar.size();
							for(int i = 0; i < length; i++)
								if(fvar.get(i).toString().compareTo(fun) == 0)
									exist = true;
							
							if(!exist)
							{
								fvar.add(fv);
								length = fvar.size();
								AbstractFunVariations.fixerX(fvar);
								for(int i = 0; i < length; i++)
									fvar.get(i).tabulate(curve.controls.currentPrecision());
							
								curve.infos.fvar = fvar;
								curve.infos.update();
								curve.tracer.fvar = fvar;
								curve.tracer.repaint();
							}
						}
						CurveDialMulti.this.dispose();
					} catch (SyntaxErrorException e1) {
						// TODO Auto-generated catch block
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					} catch(NumberFormatException e1){
					}
				}catch (NullPointerException e1){
				}
			}
		});
		annuler.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CurveDialMulti.this.dispose();
			}
		});
		
		pane.setLayout(new GridLayout(0,2));
		pane.add(new JLabel("   Fonction prefixe : "));
		pane.add(fonction);
		pane.add(new JLabel("   Minimum de la fonction : "));
		pane.add(min);
		pane.add(new JLabel("   Maximum de la fonction : "));
		pane.add(max);
		pane.add(ok);
		pane.add(annuler);
		add(pane);
		pack();
		setVisible(true);
	}
}

