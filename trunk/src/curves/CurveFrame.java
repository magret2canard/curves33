package curves;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class  to represent the variations of some function  in some interval.
 * Allows some control on the accuracy of this representation
 */

/**
 * @author casteran
 */
public class CurveFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ZOOM_PLUS = 0;
	public static final int ZOOM_MOINS = 1;
	public static final int DECALAGE_DROIT = 2;
	public static final int DECALAGE_GAUCHE = 3;

	/** the graphic part of the display */
	protected CurveTracer tracer;

	/** information bar */
	protected CurveInfos infos;

	/** various commands */
	protected CurveControls controls;
	
	protected JFrame fenetre;

	/**
	 * Builds a top-level window from the varaiations of a function
	 * 
	 * @see FunctionVariations
	 */
	public CurveFrame(FunctionVariations[] fvar) {
		super("Curve");
		tracer = new CurveTracer(fvar);
		infos = new CurveInfos(fvar);
		controls = new Controller(fvar, this);

		JPanel mainPane = new JPanel(new BorderLayout());

		mainPane.add(tracer, BorderLayout.CENTER);
		mainPane.add(infos, BorderLayout.SOUTH);
		mainPane.add(controls, BorderLayout.EAST);
		
		mainPane.add(new CurveMenuBar(CurveFrame.this), BorderLayout.NORTH);

		tracer.addMouseListener(new MouseListener(){
			CurveFrame cf = CurveFrame.this;
			int x;
			
			//@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			//@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				cf.infos.xmouse.setText("x = " + cf.tracer.realX(e.getX()));
				cf.infos.ymouse.setText("y = " + cf.tracer.realY(e.getY()));
				cf.infos.repaint();
			}

			//@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				cf.infos.xmouse.setText("");
				cf.infos.ymouse.setText("");
				cf.infos.repaint();
			}

			//@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				x = e.getX();
			}

			//@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int x2;
				if(x < (x2 = e.getX()))
					for(int i=0; i< (x2 - x) / 10; i++)
						zoom(DECALAGE_GAUCHE);
				else if(x > x2)
					for(int i=0; i< (x - x2) / 10; i++)
						zoom(DECALAGE_DROIT);
			}
			
		});
		tracer.addMouseMotionListener(new MouseMotionAdapter() {
			CurveFrame cf = CurveFrame.this;

			public void mouseMoved(MouseEvent e) {
				cf.infos.xmouse.setText("x = " + cf.tracer.realX(e.getX()));
				cf.infos.ymouse.setText("y = " + cf.tracer.realY(e.getY()));
				cf.infos.repaint();
			}

			public void mouseDragged(MouseEvent e) {
				cf.infos.xmouse.setText("x = " + cf.tracer.realX(e.getX()));
				cf.infos.ymouse.setText("y = " + cf.tracer.realY(e.getY()));
				cf.infos.repaint();
			}
		});
		
		addMouseWheelListener(new MouseWheelListener(){
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getWheelRotation() > 0)
					CurveFrame.this.zoom(ZOOM_MOINS);
				else
					CurveFrame.this.zoom(ZOOM_PLUS);
			}
		});

		for(int i = 0; i < fvar.length; i++)
			fvar[i].tabulate(controls.currentPrecision());
		setContentPane(mainPane);
		pack();
		infos.update();
		setVisible(true);
	}
	
	protected void zoom(int direction){
		double facteur;
		double min, max;
		if((direction == ZOOM_PLUS) || (direction == ZOOM_MOINS))
		{
			for(int i = 0; i < tracer.fvar.length; i++)
			{
				facteur = ((max = tracer.fvar[i].getXmax()) - (min = tracer.fvar[i].getXmin())) / 10;
				if(direction == ZOOM_PLUS)
				{
					tracer.fvar[i].setXmin(min + facteur);
					tracer.fvar[i].setXmax(max - facteur);
				}
				else
				{
					tracer.fvar[i].setXmin(min - facteur);
					tracer.fvar[i].setXmax(max + facteur);
				}
			}
		}
		else if((direction == DECALAGE_GAUCHE) || direction == DECALAGE_DROIT)
		{
			facteur = ((direction == DECALAGE_GAUCHE) ? (facteur = -.20) : (facteur = .20));
			for(int i = 0; i < tracer.fvar.length; i++)
			{
				tracer.fvar[i].setXmin(tracer.fvar[i].getXmin() + facteur);
				tracer.fvar[i].setXmax(tracer.fvar[i].getXmax() + facteur);
			}
		}
		else
		{
			return;
		}
		for(int i=0; i < tracer.fvar.length; i++)
			tracer.fvar[i].tabulate(controls.currentPrecision());
		infos.update();
		tracer.repaint();
	}
	
	protected void deplacement(int direction){
		zoom(direction);
	}
	
	protected void fixerX(FunctionVariations[] fvars) {
		// TODO Auto-generated method stub
		double max = maximum(fvars);
		double min = minimum(fvars);
		for(int i = 0; i<fvars.length; i++)
		{
			fvars[i].setXmin(min);
			fvars[i].setXmax(max);
		}
	}
	
	double maximum(FunctionVariations[] fvars) {
		// TODO Auto-generated method stub
		double max = fvars[0].getXmax();
		for(int i=1; i<fvars.length; i++)
			if(fvars[i].getXmax() > max)
				max = fvars[i].getXmax();
		return max;
	}
	
	double minimum(FunctionVariations[] fvars) {
		// TODO Auto-generated method stub
		double min = fvars[0].getXmin();
		for(int i=1; i<fvars.length; i++)
			if(fvars[i].getXmin() < min)
				min = fvars[i].getXmin();
		return min;
	}
}
