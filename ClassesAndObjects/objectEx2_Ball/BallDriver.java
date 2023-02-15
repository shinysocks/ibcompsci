package objectEx2_Ball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BallDriver extends JPanel implements ActionListener {
	
	final int WIDTH = 500;
	final int HEIGHT = WIDTH;
	
	int size;
	
	//this is making a button
	JButton button;
	JTextField jtf; //this is a text field
	JLabel label;
	
	Ball[] balls; //data structure called array
	Random rand;
	
	
	public BallDriver()
	{
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		jtf = new JTextField(5);
		button = new JButton("Make Balls");
		button.addActionListener(this); //binds the actionPerformed to the button
		
		// set up JLabel
		label = new JLabel("                ");
		label.setPreferredSize(new Dimension(100, 25));
		label.setFont(new Font("Courier", Font.BOLD, 18));
		label.setOpaque(true);
		label.setBackground(Color.BLUE);
		label.setForeground(Color.YELLOW);

		this.add(jtf);
		this.add(button);
	}
	
	//this has to be included to draw on a jpanel
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(balls != null)
		{
			for(int i = 0; i < balls.length; i++)
			{
				balls[i].draw(g);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Objects Example 2");
		
		BallDriver panel = new BallDriver();
		
		//add the panel to the frame
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		size = Integer.parseInt(jtf.getText());
		
		balls = new Ball[size];
		
		rand = new Random();
		
		for(int i = 0; i < balls.length; i++)
		{
			int x = rand.nextInt(WIDTH)+1; //1 - 500
			int y = rand.nextInt(HEIGHT) + 1; //1 - 500
			int rad = rand.nextInt(80)+1; //1 - 80
			balls[i] = new Ball(x,y, rad, new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
		}

		label.setText("" + Ball.numBallsMade);
		

		repaint();
	}//end methods

}
