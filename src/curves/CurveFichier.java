package curves;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import function.Fonction;
import function.Function;
import function.Functions;
import function.SyntaxErrorException;

public class CurveFichier {
	BufferedReader buffer;
	
	public CurveFichier(File file) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		buffer = new BufferedReader(new FileReader(file));
	}
	
	public List<FunctionVariations> creerFoncVar() throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		List<FunctionVariations> liste = new ArrayList<FunctionVariations>();
		boolean fin = false;
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
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		int length = liste.size();
		if(length < 1)
			throw new ArrayIndexOutOfBoundsException();
		return liste;
	}
}
