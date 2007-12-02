package curves;

import java.util.List;

import javax.swing.JFrame;

public class CurveApplication {

	private CurveApplication() {
	}
	
	public static void start(List<FunctionVariations> fv) {
		final List<FunctionVariations> fvar = fv;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				CurveFrame cv = new CurveFrame(fvar);
				cv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

}
