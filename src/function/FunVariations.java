package function;

import curves.Variations;

public class FunVariations extends Variations {
	/**
	 * 
	 */
	private static final long serialVersionUID = 814792668273453011L;
	private Function f;

	public FunVariations(Function f, double xmin, double xmax) {
		this.f = f;
		setXmin(xmin);
		setXmax(xmax);
	}

	protected double fun(double x) {
		return f.value(x);
	}
}
