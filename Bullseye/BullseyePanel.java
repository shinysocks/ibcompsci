import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class BullseyePanel extends JPanel {
	final int NUM_RINGS = 5;

    public BullseyePanel()
    {
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(300, 300));
    }
}
