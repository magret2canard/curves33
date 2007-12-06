package curves;

import java.util.List;

/**
 * 
 * Cette classe permet d'utiliser des méthodes statiques propres aux listes de FunctionVariations 
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public abstract class AbstractFunVariations extends Variations {

	@Override
	protected abstract double fun(double x);
	
	/**
	 * 
	 * Fixe le minimum et le maximum de tous les éléments de la liste fvars avec la valeur
	 * du minimum des x et du maximum des x de cette liste.
	 * @param fvars
	 */
	public static void fixerX(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double max = maximumX(fvars);
		double min = minimumX(fvars);
		int length = fvars.size();
		for(int i = 0; i<length; i++)
		{
			fvars.get(i).setXmin(min);
			fvars.get(i).setXmax(max);
		}
	}
	 /**
	  * 
	  * @param fvars
	  * @return maximum des Xmax de fvars
	  */
	public static double maximumX(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double max = fvars.get(0).getXmax();
		int length = fvars.size();
		for(int i=1; i<length; i++)
			if(fvars.get(i).getXmax() > max)
				max = fvars.get(i).getXmax();
		return max;
	}
	 /**
	  * 
	  * @param fvars
	  * @return minimum des Xmin de fvars
	  */
	public static double minimumX(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double min = fvars.get(0).getXmin();
		int length = fvars.size();
		for(int i=1; i<length; i++)
			if(fvars.get(i).getXmin() < min)
				min = fvars.get(i).getXmin();
		return min;
	}
	
	/**
	 * 
	 * @param fvars
	 * @return maximum des Ymax de fvars
	 */
	public static double maximumY(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double max = fvars.get(0).getYmax();
		int length = fvars.size();
		for(int i=1; i<length; i++)
			if(fvars.get(i).getYmax() > max)
				max = fvars.get(i).getYmax();
		return max;
	}
	
	/**
	 * 
	 * @param fvars
	 * @return minimum des Xmin de fvars
	 */
	public static double minimumY(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double min = fvars.get(0).getYmin();
		int length = fvars.size();
		for(int i=1; i<length; i++)
			if(fvars.get(i).getYmin() < min)
				min = fvars.get(i).getYmin();
		return min;
	}
}
