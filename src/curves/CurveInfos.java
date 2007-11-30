package curves;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author casteran
 * 
 */

class CurveInfos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel xmin, xmax, ymin, ymax;

	JLabel xmouse, ymouse;

	JLabel integral;

	FunctionVariations[] fvar;

	CurveInfos(FunctionVariations[] fvar) {
		this.fvar = fvar;
		setLayout(new GridLayout(0, 2, 10, 10));
		xmin = new JLabel();
		xmax = new JLabel();
		ymin = new JLabel();
		ymax = new JLabel();
		xmouse = new JLabel();
		ymouse = new JLabel();
		integral = new JLabel();

		add(xmin);
		add(xmax);
		add(ymin);
		add(ymax);
		add(integral);
		add(new JLabel());
		add(xmouse);
		add(ymouse);
		setBackground(Color.orange);
	}

	void update() {
		xmin.setText("xmin = " + fvar[0].getXmin());
		xmax.setText("xmax = " + fvar[0].getXmax());
		ymin.setText("ymin = " + fvar[0].getYmin());
		ymax.setText("ymax = " + fvar[0].getYmax());
		xmouse.setText("");
		ymouse.setText("");
		integral.setText("sum = " + fvar[0].getIntegral());
	}
}
