package curves;

import function.FunVariations;
import function.Function;

public class FunVariationNamed extends FunVariations {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
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
