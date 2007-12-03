package curves;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class CurveFichierEcriture extends JFileChooser{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4724794112986662635L;
	PrintWriter printWriter;
	//ObjectOutputStream out;
	File fichier;
	CurveFrame f;
	JFrame frame;
	
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
	
	private void enregisterFoncVar(List<FunctionVariations> fvars) throws IOException{ //Je n'arrive pas à  écrire dans le fichier
		if(!fichier.exists()) //Si il existe, il serait bien de demander une confirmation
			fichier.createNewFile();
		
		printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
		printWriter.println(fvars.get(0).getXmin() + " , " + fvars.get(0).getXmax());
		printWriter.println("");
		int length = fvars.size();
		for(int i = 0; i < length; i++)
			printWriter.println(fvars.get(i));
		//out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichier)));
		//t.writeObject(fvars);
	}
}