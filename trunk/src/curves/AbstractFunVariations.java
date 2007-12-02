package curves;

import java.util.List;

public abstract class AbstractFunVariations extends Variations {

	@Override
	protected abstract double fun(double x);
	
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
	
	public static double maximumX(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double max = fvars.get(0).getXmax();
		int length = fvars.size();
		for(int i=1; i<length; i++)
			if(fvars.get(i).getXmax() > max)
				max = fvars.get(i).getXmax();
		return max;
	}
	
	public static double minimumX(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double min = fvars.get(0).getXmin();
		int length = fvars.size();
		for(int i=1; i<length; i++)
			if(fvars.get(i).getXmin() < min)
				min = fvars.get(i).getXmin();
		return min;
	}
	
	public static double maximumY(List<FunctionVariations> fvars) {
		// TODO Auto-generated method stub
		double max = fvars.get(0).getYmax();
		int length = fvars.size();
		for(int i=1; i<length; i++)
			if(fvars.get(i).getYmax() > max)
				max = fvars.get(i).getYmax();
		return max;
	}
	
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
