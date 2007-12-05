package curves;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

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

	JLabel xmin;
	JLabel xmax;
	JLabel ymin;
	JLabel ymax;

	JLabel xmouse;
	JLabel ymouse;

	JLabel integral;

	List<FunctionVariations> fvar;

	CurveInfos(List<FunctionVariations> fvar) {
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
		try{
			xmin.setText("xmin = " + fvar.get(0).getXmin());
			xmax.setText("xmax = " + fvar.get(0).getXmax());
			ymin.setText("ymin = " + AbstractFunVariations.minimumX(fvar));
			ymax.setText("ymax = " + AbstractFunVariations.maximumY(fvar));
		}catch(IndexOutOfBoundsException e){
			xmin.setText("xmin = " + "indéfini");
			xmax.setText("xmax = " + "indéfini");
			ymin.setText("ymin = " + "indéfini");
			ymax.setText("ymax = " + "indéfini");
		}
		xmouse.setText("");
		ymouse.setText("");
		try{
			integral.setText("sum = " + fvar.get(0).getIntegral());
		}catch(IndexOutOfBoundsException e){
			integral.setText("sum = " + "indéfini");
		}
	}
}
