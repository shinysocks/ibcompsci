import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

	public static void main(String[] args) {
		
		final int WIDTH = 500;
		final int HEIGHT = 300;		
	}
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(new Color(130,255,255));
		this.setFocusable(true);
	}

}
