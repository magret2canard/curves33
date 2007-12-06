package curves;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * 
 * Cette classe créer un JFileChooser qui permet l'écriture d'un fichier.
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public class CurveFichierEcriture extends JFileChooser{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4724794112986662635L;
	PrintWriter printWriter;
	File fichier;
	CurveFrame f;
	JFrame frame;
	
	/**
	 * 
	 * @param f
	 * @param frame est la fenetre dans laquelle on affiche le JFileChooser
	 * @throws IOException
	 */
	public CurveFichierEcriture(CurveFrame f, JFrame frame) throws IOException {
		// TODO Auto-generated constructor stub
		this.f = f;
		this.frame = frame;
	}
	
	public void approveSelection() {
		super.approveSelection();
		try {
			fichier = this.getSelectedFile();
			enregisterFoncVar(f.tracer.fvar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.dispose();
	}

	@Override
	public void cancelSelection() {
		// TODO Auto-generated method stub
		super.cancelSelection();
		frame.dispose();
	}
	
	/**
	 * 
	 * Ecrit dans le fichier la liste fvar.
	 * @param fvars
	 * @throws IOException
	 */
	private void enregisterFoncVar(List<FunctionVariations> fvars) throws IOException{
		if(!fichier.exists())
			fichier.createNewFile();
		
		printWriter = new PrintWriter(fichier);
		printWriter.println(fvars.get(0).getXmin() + " , " + fvars.get(0).getXmax());
		printWriter.println("");
		int length = fvars.size();
		for(int i = 0; i < length; i++)
			printWriter.println(fvars.get(i));
		
		printWriter.flush();
	}
}