package curves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
 * @author Védrenne Benoît
 *
 */
public class Curves {
	protected static String minimum;
	protected static String maximum;
	protected static String fun;
	protected static JFrame frame;
	
	public Curves() {
		// TODO Auto-generated constructor stub
	}
	
	public static void start() {
		frame = new JFrame("Tapez votre fonction de manière préfixé.");
		JPanel pane = new JPanel();
		final JTextField fonction = new JTextField(20);
		final JTextField min = new JTextField(10);
		final JTextField max = new JTextField(10);
		JButton ok = new JButton("OK");
		JButton annuler = new JButton("Quitter");
		JButton ouvrir = new JButton("Ouvrir un Fichier");
		
		ok.setMnemonic(KeyEvent.VK_ENTER);
		
		ok.addActionListener(new ActionListener(){
			//@Override
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fun = fonction.getText();
				maximum = max.getText();
				minimum = min.getText();
				if(fun != null && maximum != null && minimum != null)
				{
					try{
						double tempMin, tempMax;
						Function f = Functions.parse(fun);
						tempMin = Fonction.parseDouble(minimum);
						tempMax = Fonction.parseDouble(maximum);
						double max1 = Math.max(tempMin, tempMax);
						double min1 = Math.min(tempMin, tempMax);
						FunVariationNamed fv = new FunVariationNamed(f, min1, max1, fun);
						List<FunctionVariations> lfv = new ArrayList<FunctionVariations>();
						lfv.add(fv);
			
						CurveApplication.start(lfv);
						
						Curves.this.frame.dispose();
					} catch (SyntaxErrorException e1) {
						// TODO Auto-generated catch block
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					} catch(NumberFormatException e1){
					}
				}
			}
		});
		annuler.addActionListener(new ActionListener(){
			//@Override
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Curves.this.frame.dispose();
			}
		});
		ouvrir.addActionListener(new ActionListener(){
			//@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final JFrame frame = new JFrame();
				JPanel pane = new JPanel();
				JFileChooser jfc = new JFileChooser(){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("static-access")
					@Override
					public void approveSelection() {
						// TODO Auto-generated method stub
						super.approveSelection();
						try{
							CurveFichier cf = new CurveFichier(this.getSelectedFile());
							try{
								CurveApplication.start(cf.creerFoncVar());
								frame.dispose();
								Curves.this.frame.dispose();
							}catch (ArrayIndexOutOfBoundsException e){
								
							}
						}catch(IOException e)
						{
						}catch(ClassNotFoundException e){
						}
					}
					
					@Override
					public void cancelSelection() {
						// TODO Auto-generated method stub
						super.cancelSelection();
						frame.dispose();
					}
				};
				
				pane.add(jfc);
				frame.add(pane);
				
				frame.pack();
				frame.setVisible(true);
			}
		});
		
		//pane.setLayout(new GridLayout(0,2));
		pane.add(new JLabel("   Fonction prefixe : "));
		pane.add(fonction);
		pane.add(new JLabel("   Minimum de la fonction : "));
		pane.add(min);
		pane.add(new JLabel("   Maximum de la fonction : "));
		pane.add(max);
		pane.add(ok);
		pane.add(annuler);
		pane.add(ouvrir);
		
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
