package curves;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * 
 * @author casteran
 * 
 */

class CurveTracer extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FunctionVariations[] fvar;

	/**
	 * part of the height of the canvas devoted to the drawing of the curve
	 */

	static double croppingFactor = 0.9;

	CurveTracer(FunctionVariations[] var) {
		this.fvar = var;
		setPreferredSize(new Dimension(400, 300));
		setOpaque(true);
		setBackground(Color.green);
		setForeground(Color.red);
	}

	/**
	 * converts the mouse's y into the corresponding value in the function's
	 * range
	 */

	protected double realX(int mouseX) {
		double xmin = fvar[0].getXmin();
		return xmin + (mouseX * (fvar[0].getXmax() - xmin) / getWidth());
	}

	/**
	 * converts the mouse's y into the corresponding value in the function's
	 * range
	 */

	protected double realY(int mouseY) {
		/*if (fvar.isConstant())
			return fvar.getYmax();*/
		int h = getHeight();
		double ymax = maxY(fvar);
		return ymax + (minY(fvar) - ymax)
				* (mouseY - h * (1 - croppingFactor) * 0.5) / h
				/ croppingFactor;
	}

	/** converts some y in the fun's range into a mouse y coordinate */

	protected int getY(double y) {
		int h = getHeight();
		/*if (fvar.isConstant())
			return (int) h / 2;
		else {*/
			double ymax = maxY(fvar);
			return (int) ((h * (1 - croppingFactor) / 2.0) + h * croppingFactor
					* (y - ymax) / (minY(fvar) - ymax));
		//}
	}
	
	private double minY(FunctionVariations[] fvars) {
		// TODO Auto-generated method stub
		double min = fvars[0].getYmin();
		for(int i=1; i<fvars.length; i++)
			if(fvars[i].getYmin() < min)
				min = fvars[i].getYmin();
		return min;
	}
	
	private double maxY(FunctionVariations[] fvars) {
		// TODO Auto-generated method stub
		double max = fvars[0].getYmax();
		for(int i=1; i<fvars.length; i++)
			if(fvars[i].getYmax() > max)
				max = fvars[i].getYmax();
		return max;
	}

	protected void paintComponent(Graphics g) {
		int width = getWidth();

		// Paint background if we're opaque.
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, width, getHeight());
		}
		
		g.setColor(getForeground());
		for(int i = 0; i < fvar.length; i++)
		{
			double step = ((double) width) / fvar[i].getStepNumber();
			double lastX = 0;
			double newX;
			
			int lastY = getY(fvar[i].getStepValue(0));
			int newy;
			for (int j = 0; j < fvar[i].getStepNumber(); j++) {
				newy = getY(fvar[i].getStepValue(j + 1));
				newX = lastX + step;
				g.drawLine((int) lastX, lastY, (int) newX, newy);
				lastY = newy;
				lastX = newX;
			}
			if (lastX < (double) width) {
				g.drawLine((int) lastX, lastY, width, lastY);
			}
		}
	}
}
