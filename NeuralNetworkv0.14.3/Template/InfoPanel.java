package Template;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 7981729042205533774L;
	private final ArrayList<InfoLabel> labels = new ArrayList<InfoLabel>();

	public InfoPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

		

		for(InfoLabel label : labels) {
			add(label);
		}
	}
	
	private static class InfoLabel extends JLabel {
		private String baseText;
		
		public InfoLabel(String baseText) {
			setText(baseText);
			this.baseText = baseText;
		}
		
		@Override
		public void setText(String text) {
			super.setText(baseText + text);
		}
	}
}
