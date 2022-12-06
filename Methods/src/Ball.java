import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Ball
{
	//global variables
	int x;
	int y;
	Color color;
	int dx = 1; //change in x
	int dy = 1;
	final int WIDTH = 50; 
	
	//constructor
	public Ball(int theX, int theY, Color c)
	{
		x = theX;
		y = theY;
		color = c;
	}
	
	//draw
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval((int)x, (int)y, WIDTH, WIDTH);
	}
	
	public void right()
	{
		x += dx;
	}
	
	public void left()
	{
		x -= dx;
	}
	
	public void up()
	{
		y -= dy;
	}
	
	public void down()
	{
		y += dy;
	}
}


