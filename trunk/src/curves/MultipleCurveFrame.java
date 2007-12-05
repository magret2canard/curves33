package curves;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

/**
 * 
 * Cette classe était censée remplacé la classe CurveFrame, malheureusement il exister une erreur dans
 * l'affichage du panneau de control Controller.
 * @author Ewans Edouard
 * @author Védrenne Benoît
 * @version 1
 *
 */
public class MultipleCurveFrame extends CurveFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4406620148874648246L;
	public static final int ZOOM_PLUS = 0;
	public static final int ZOOM_MOINS = 1;
	public static final int DECALAGE_DROIT = 2;
	public static final int DECALAGE_GAUCHE = 3;
	
	/**
	 * 
	 * @param fvar
	 */
	public MultipleCurveFrame(List<FunctionVariations> fvar) {
		super(fvar);
		// TODO Auto-generated constructor stub
		controls = new Controller(fvar, this);
		
		addMouseWheelListener(new MouseWheelListener(){ //Bug marche que dans certaines zones et ne marche pas sous Windows XP
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getWheelRotation() > 0)
					MultipleCurveFrame.this.zoom(ZOOM_MOINS);
				else
					MultipleCurveFrame.this.zoom(ZOOM_PLUS);
			}
		});
		
		tracer.addMouseListener(new MouseListener(){
			CurveFrame cf = MultipleCurveFrame.this;
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
	}
	
	/**
	 * Effectue un zoom.
	 * Voir ZOOM_PLUS, ZOOM_MOINS
	 */
	protected void zoom(int direction){
		double facteur;
		double min, max;
		if((direction == ZOOM_PLUS) || (direction == ZOOM_MOINS))
		{
			int length = tracer.fvar.size();
			for(int i = 0; i < length; i++)
			{
				facteur = ((max = tracer.fvar.get(i).getXmax()) - (min = tracer.fvar.get(i).getXmin())) / 10;
				if(direction == ZOOM_PLUS)
				{
					tracer.fvar.get(i).setXmin(min + facteur);
					tracer.fvar.get(i).setXmax(max - facteur);
				}
				else
				{
					tracer.fvar.get(i).setXmin(min - facteur);
					tracer.fvar.get(i).setXmax(max + facteur);
				}
			}
		}
		else if((direction == DECALAGE_GAUCHE) || direction == DECALAGE_DROIT)
		{
			int length = tracer.fvar.size();
			for(int i = 0; i < length; i++)
			{
				facteur = (tracer.fvar.get(i).getXmax() - tracer.fvar.get(i).getXmin()) / 10;
				tracer.fvar.get(i).setXmin(tracer.fvar.get(i).getXmin() + facteur);
				tracer.fvar.get(i).setXmax(tracer.fvar.get(i).getXmax() + facteur);
			}
		}
		else
		{
			return;
		}
		int length = tracer.fvar.size();
		for(int i=0; i < length; i++)
			tracer.fvar.get(i).tabulate(controls.currentPrecision());
		infos.update();
		tracer.repaint();
	}
	
	/**
	 * Effectue un déplacement
	 * DECALAGE_DROIT , DECALAGE_GAUCHE
	 */
	protected void deplacement(int direction){
		zoom(direction);
	}
}
