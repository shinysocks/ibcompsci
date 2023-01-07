package objectEx2_Ball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallDriver extends JPanel
{
	Ball b1, b2, b3, b4, b5;
	final int WIDTH = 500;
	final int HEIGHT = WIDTH;
	
	public BallDriver()
	{
		this.setBackground(Color.YELLOW);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		b1 = new Ball(0, 0, 10);
		b2 = new Ball((WIDTH/2)-(100), (HEIGHT/2)-(100), 100);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(b1 != null || b2 != null)
		{
			b1.draw(g);
			b2.draw(g);
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Objects Example 2");
		
		BallDriver panel = new BallDriver();
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
