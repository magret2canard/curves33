package curves;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

	CurveControls(final FunctionVariations[] var, final CurveFrame f) {
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
					for(int i = 0; i < var.length; i++)
						f.infos.fvar[i].tabulate(CurveControls.this.currentPrecision());
					f.infos.update();
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
