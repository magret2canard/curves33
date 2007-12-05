package curves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import function.Fonction;
import function.Function;
import function.Functions;
import function.SyntaxErrorException;

/**
 * 
 * Cette classe créer un JFileChooser pour pouvoir ouvrir un fichier
 * Le fichier doit être du type :
 * Un intervalle c'est à dire la valeur minimale, une virgule et la valeur maximale
 * Une ligne vide
 * Une suite de fonction préfixée (pour chaque nouvelle fonction, retourner à la ligne
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public class CurveFichierOuverture extends JFileChooser{
	private static final long serialVersionUID = -1760058913564864866L;
	private CurveFrame f;
	private JFrame frame;
	
	/**
	 * 
	 * @param f
	 * @param frame , la fenetre dans laquel on affiche le JFileChooser
	 */
	public CurveFichierOuverture(CurveFrame f, JFrame frame) {
		// TODO Auto-generated constructor stub
		super();
		this.f = f;
		this.frame = frame;
	}
	
	//@Override
	public void approveSelection() {
		// TODO Auto-generated method stub
		super.approveSelection();
		try {
			File file = this.getSelectedFile();
			if(file.exists())
			{
				f.tracer.fvar = creerFoncVar(file);
				double min = AbstractFunVariations.minimumX(f.tracer.fvar);
				double max = AbstractFunVariations.maximumX(f.tracer.fvar);
				int precision = f.controls.currentPrecision();
				int length = f.tracer.fvar.size();
				for(int i = 0; i < length; i++)
				{
					f.tracer.fvar.get(i).setXmin(min);
					f.tracer.fvar.get(i).setXmax(max);
					f.tracer.fvar.get(i).tabulate(precision);
				}
				f.infos.fvar = f.tracer.fvar;
				f.infos.update();
				f.tracer.repaint();
				
				frame.dispose();
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
		}catch (ClassNotFoundException e){
		}
		catch (NumberFonctionException e){
			affichageErreur();
		}
	}
	
	@Override
	public void cancelSelection() {
		// TODO Auto-generated method stub
		super.cancelSelection();
		frame.dispose();
	}
	
	private List<FunctionVariations> creerFoncVar(File file) throws IOException, ClassNotFoundException, NumberFonctionException {
		// TODO Auto-generated method stub
		//return (List<FunctionVariations>) in.readObject();
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		List<FunctionVariations> liste = new ArrayList<FunctionVariations>();
		boolean fin = false, erreur = false;
		String s;
		Function f = null;
		double min = 0, max = 0;
		
		try {
			while(((s = buffer.readLine()) != null) && (!fin))
				if(s.contains(","))
				{
					StringTokenizer st = new StringTokenizer(s, ",");
					if(st.countTokens() == 2)
						try{
							min = Fonction.parseDouble(st.nextToken().replaceAll("pi", String.valueOf(Math.PI)));
							max = Fonction.parseDouble(st.nextToken().replaceAll("pi", String.valueOf(Math.PI)));
							fin = true;
						} catch (SyntaxErrorException e){
							erreur = true;
						}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		try {
			while((s = buffer.readLine()) != null)
				try{
					f = Functions.parse(s);
					liste.add(new FunVariationNamed(f, min, max, s));
				}catch (SyntaxErrorException e){
					erreur = true;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		if(erreur){
			JOptionPane.showMessageDialog(CurveFichierOuverture.this, "Le fichier a été chargé\n" +
					"malgré les erreurs de celui-ci\n" +
					"\n");
		}
		
		int length = liste.size();
		if(length < 1)
			throw new NumberFonctionException();
		return liste;
	}
	
	private void affichageErreur(){
		JOptionPane.showMessageDialog(CurveFichierOuverture.this, "Ce fichier ne comporte pas de fonction correcte\n" +
				"\n" +
				"Un fichier correct est de la forme\n" +
				"minimum , maximum\n" +
				"Une ligne vide\n" +
				"Suite de fonction préfixé\n" +
				"\n");
	}
}
