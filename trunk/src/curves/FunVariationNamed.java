package curves;

import function.FunVariations;
import function.Function;

/**
 * 
 * Cette fonction permet de pouvoir récupérer le nom de la fonction variations
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public class FunVariationNamed extends FunVariations {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	/**
	 * 
	 * @param f
	 * @param xmin
	 * @param xmax
	 * @param name nom de la fonction
	 */
	public FunVariationNamed(Function f, double xmin, double xmax, String name) {
		super(f, xmin, xmax);
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
