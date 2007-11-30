package curves;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CurveFichierEcriture {
	PrintWriter printWriter;
	
	public CurveFichierEcriture(File fichier) throws IOException {
		// TODO Auto-generated constructor stub
		if(!fichier.exists()) //Si il existe, il serait bien de demander une confirmation
			fichier.createNewFile();
		printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
	}
	
	public void enregisterFoncVar(FunctionVariations[] fvars) throws IOException{ //Je n'arrive pas à écrire dans le fichier
		//Il serait bien de récupérer le nom de la fonction
		printWriter.println(fvars[0].getXmin() + " , " + fvars[0].getXmax());
		printWriter.println("");
		for(int i = 0; i < fvars.length; i++)
			printWriter.println(fvars[i]);
	}
}