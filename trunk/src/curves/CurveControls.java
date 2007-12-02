package curves;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author casteran
 */

class CurveControls extends JPanel {

	private static final long serialVersionUID = 1L;

	protected final static Integer nStepsChoices[] = { 1, 2, 3, 4, 5, 10, 20,
			40, 80, 160, 320, 640 };

	private JComboBox cb;
	CurveFrame f;

	CurveControls(final List<FunctionVariations> var, final CurveFrame f) {
		super();
		this.f = f;
		JPanel precision = new JPanel();
		JLabel title = new JLabel("Précision");
		cb = new JComboBox(nStepsChoices);

		precision.add(title);
		precision.add(cb);
		add(precision);
		cb.addItemListener((ItemListener) (new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int length = f.infos.fvar.size();
					for(int i = 0; i < length; i++)
						f.infos.fvar.get(i).tabulate(CurveControls.this.currentPrecision());
					try{
						f.infos.update();
					}catch(IndexOutOfBoundsException e1){
					}
					f.infos.setVisible(true);
					f.repaint();
				}
			}

		}));
		cb.setSelectedIndex(nStepsChoices.length / 2);
	}

	int currentPrecision() {
		return nStepsChoices[cb.getSelectedIndex()];
	}
}
