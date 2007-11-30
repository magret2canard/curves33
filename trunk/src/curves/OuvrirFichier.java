package curves;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OuvrirFichier extends JFileChooser {
	private static final long serialVersionUID = -1760058913564864866L;
	private CurveFrame f;
	private JFrame frame;
	
	public OuvrirFichier(CurveFrame f, JFrame frame) {
		// TODO Auto-generated constructor stub
		super();
		this.f = f;
		this.frame = frame;
	}
	
	//@Override
	public void approveSelection() {
		// TODO Auto-generated method stub
		super.approveSelection();
		try {
			File file = this.getSelectedFile();
			if(file.exists())
			{
				CurveFichier fichier = new CurveFichier(file);
				f.tracer.fvar = fichier.creerFoncVar();
				double min = f.minimum(f.tracer.fvar);
				double max = f.maximum(f.tracer.fvar);
				int precision = f.controls.currentPrecision();
				for(int i = 0; i < f.tracer.fvar.length; i++)
				{
					f.tracer.fvar[i].setXmin(min);
					f.tracer.fvar[i].setXmax(max);
					f.tracer.fvar[i].tabulate(precision);
				}
				f.infos.fvar = f.tracer.fvar;
				f.infos.update();
				f.tracer.repaint();
				
				frame.dispose();
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}catch (ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(OuvrirFichier.this, "Ce fichier ne comporte pas de fonction correcte\n" +
					"\n" +
					"Un fichier correct est de la forme\n" +
					"minimum , maximum\n" +
					"Une ligne vide\n" +
					"Suite de fonction préfixé\n" +
					"\n");
		}
	}
	
	@Override
	public void cancelSelection() {
		// TODO Auto-generated method stub
		super.cancelSelection();
		frame.dispose();
	}
}
