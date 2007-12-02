package curves;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CurveFichierEcriture{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter printWriter;
	//ObjectOutputStream out;
	
	public CurveFichierEcriture(File fichier) throws IOException {
		// TODO Auto-generated constructor stub
		if(!fichier.exists()) //Si il existe, il serait bien de demander une confirmation
			fichier.createNewFile();
		printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
		//out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichier)));
	}
	
	public void enregisterFoncVar(List<FunctionVariations> fvars) throws IOException{ //Je n'arrive pas à écrire dans le fichier
		//Il serait bien de récupérer le nom de la fonction
		printWriter.println(fvars.get(0).getXmin() + " , " + fvars.get(0).getXmax());
		printWriter.println("");
		int length = fvars.size();
		for(int i = 0; i < length; i++)
			printWriter.println(fvars.get(i));
		//out.writeObject(fvars);
	}
}