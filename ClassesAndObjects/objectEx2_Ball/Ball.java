package objectEx2_Ball;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

public class Ball {
	private int x;
	private int y;
	private int radius;
	private Color col;

	static int numBallsMade = 0;
	private int ballNum;
	
	public Ball(int theX, int theY, int theR, Color c)
	{
		x = theX;
		y = theY;
		radius = theR;
		col = c;
		
		numBallsMade++;
		ballNum = numBallsMade;
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(10));
		g2.drawOval(x - radius, y - radius, radius*2, radius*2);//border
		g.setColor(col);
		g.fillOval(x - radius, y - radius, radius*2, radius*2);

		// add writing to ball

		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		g2.drawString("" + ballNum, x - 10, y);
	}
}
