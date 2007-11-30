package function;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Fonction extends Functions {
	
	public static final Map<String, Operator> operators = new HashMap<String, Operator>();

	private static abstract class Operator {
		final String name;

		final int arity;

		public Operator(String name, int arity) {
			this.name = name;
			this.arity = arity;
		}

		public abstract double eval(double... args);
	}

	private static Operator[] entries = { new Operator("sin", 1) {
		public double eval(double... args) {
			return Math.sin(args[0]);
		}
	}, new Operator("cos", 1) {
		public double eval(double... args) {
			return Math.cos(args[0]);
		}
	}, new Operator("+", 2) {
		public double eval(double... args) {
			return args[0] + args[1];
		}
	}, new Operator("-", 2) {
		public double eval(double... args) {
			return args[0] - args[1];
		}
	}, new Operator("*", 2) {
		public double eval(double... args) {
			return args[0] * args[1];
		}
	}, new Operator("/", 2) {
		public double eval(double... args) {
			return args[0] / args[1];
		}
	}, new Operator("tan", 1) {
		public double eval(double... args){
			return Math.tan(args[0]);
		}
	}, new Operator("e", 1) {
		public double eval(double... args){
			return Math.exp(args[0]);
		}
	}, new Operator("ln", 1) {
		public double eval(double... args){
			return Math.log(args[0]);
		}
	} };

	static {
		for (Operator e : entries) {
			operators.put(e.name, e);
		}
	}
	
	private static double parseDoublePrive(StreamTokenizer st) throws SyntaxErrorException, IOException{
		switch (st.ttype) {
		case StreamTokenizer.TT_NUMBER:
			return st.nval;
		case StreamTokenizer.TT_WORD:
			Operator op = operators.get(st.sval);
			if (op == null) {
				throw new SyntaxErrorException(st, "Unknown operator");
			}
			double[] args = new double[op.arity];
			for (int i = 0; i < op.arity; ++i) {
				st.nextToken();
				args[i] = parseDoublePrive(st);
			}
			return op.eval(args);
		default:
			throw new SyntaxErrorException(st);
		}
	}
	
	public static double parseDouble(String s) throws SyntaxErrorException,
	IOException {
		StreamTokenizer st = new StreamTokenizer(new StringReader(s));
		st.wordChars('!', '~');
		st.eolIsSignificant(false);
		st.nextToken();
		double n = parseDoublePrive(st);
		st.nextToken();
		if (st.ttype != StreamTokenizer.TT_EOF) {
			throw new SyntaxErrorException(st);
		}
		return n;
	}
}
