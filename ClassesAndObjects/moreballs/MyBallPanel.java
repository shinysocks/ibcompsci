import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MyBallPanel extends JPanel implements KeyListener, MouseListener
{
	//global variables
	Ball b1 = new Ball(0,0,Color.CYAN);
	Ball b2 = new Ball(250,250,Color.MAGENTA);
	
	//constructor
	public MyBallPanel()
	{
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(Color.BLACK);
		

		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
	}	
	
	//methods
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		b1.draw(g);
		b2.draw(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			b1.right();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			b1.left();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			b1.up();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			b1.down();
		}
		
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() > b2.x)
		{
			for (int i = b2.x; i <= e.getX(); i += b2.dx)
			{
				b2.x = i;
				repaint();
			}

			if (e.getY() > b2.y)
			{
				for (int i = b2.y; i <= e.getX(); i += b2.dy)
				{
					b2.y = i;
					repaint();
				}
			}
			
			else
			{
				for (int i = b2.y; i >= e.getX(); i -= b2.dy)
				{
					b2.y = i;
					repaint();
				}
			}
		}
		
		else if (e.getX() < b2.x)
		{
			for (int i = b2.x; i >= e.getX(); i -= b2.dx)
			{
				b2.x = i;
				repaint();
			}
			
			if (e.getY() < b2.y)
			{
				for (int i = b2.y; i >= e.getX() ; i -= b2.dy)
				{
					b2.y = i;
					repaint();
				}
			}
			
			else
			{
				for (int i = b2.y; i <= e.getX(); i += b2.dy)
				{
					b2.y = i;
					repaint();
				}
			}
			
			
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}