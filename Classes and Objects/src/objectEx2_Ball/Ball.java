package objectEx2_Ball;

import java.awt.Color;
import java.awt.Graphics;

public class Ball
{
	int x;
	int y;
	int radius;
	
	public Ball(int theX, int theY, int theR)
	{
		x = theX;
		y = theY;
		radius = theR;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(x, y, radius*2, radius*2);
		
		Graphics2D g2 
	}
}
