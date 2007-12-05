package curves;

import java.util.ArrayList;
import java.util.List;


public class CurvesFusion{
	private static List<FunctionVariations> multiliste = new ArrayList<FunctionVariations>();
	
	public static void add(FunctionVariations fvar){
		boolean exist = false;
		List<FunctionVariations> l = new ArrayList<FunctionVariations>();
		int length = multiliste.size();
		for(int i = 0; i < length; i++)
			if(equal(fvar, multiliste.get(i)))
			{
				exist = true;
				l.add(multiliste.get(i));
			}
		if(exist)
		{
			l.add(fvar);
			double max = AbstractFunVariations.maximumX(l);
			double min = AbstractFunVariations.minimumX(l);
			fvar.setXmin(min);
			fvar.setXmax(max);
			length = l.size();
			for(int i = 0; i < length; i++)
				multiliste.remove(l.get(i));
		}
		multiliste.add(fvar);
		AbstractFunVariations.fixerX(multiliste);
	}
	
	public static void remove(FunctionVariations fvar){
		multiliste.remove(fvar);		
	}
	
	public static List<FunctionVariations> getMultiliste(){
		return multiliste;
	}
	
	private static boolean equal(FunctionVariations fvar1, FunctionVariations fvar2){
		return fvar1.toString().compareTo(fvar2.toString()) == 0;
	}
}
