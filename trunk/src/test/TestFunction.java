package test;
import java.io.IOException;

import curves.Curves;
import function.SyntaxErrorException;


public class TestFunction {
	public static void main(String[] args) throws SyntaxErrorException, IOException {
		//Function f = times(X, comp(SIN, times(constant(2.), X)));
		/*Function f = Functions.parse("* x sin * 2 x");
		Function f2 = Functions.parse("sin x");
		FunVariations[] fvars = {new FunVariations(f, -2 * Math.PI, 2 * Math.PI),
												new FunVariations(f2, -Math.PI, Math.PI)};
		CurveApplication.start(fvars);*/
		Curves.start();
	}
}
