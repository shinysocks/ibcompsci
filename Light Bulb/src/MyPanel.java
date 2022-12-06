import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements ActionListener {
	final int WIDTH = 500;
	final int HEIGHT = 400;
	
	JButton b;
	boolean isOn = false;
	int click = 1;
	
	JLabel myLabel;
	String myLight;
	JPanel p;

	public MyPanel()
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.BLACK);
		
		b = new JButton("On/Off");
		b.addActionListener(this);
		this.add(b);
		
		p = new JPanel();
		p.setBackground(Color.YELLOW);
		p.setPreferredSize(new Dimension(WIDTH, HEIGHT/3));
		myLabel = new JLabel(myLight);
		myLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
		
		this.add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b)
		{
			click++;
			if(click%2==0)
			{
				isOn = true;
			}
			else
			{
				isOn = false;
			}
			myLight = lights();
			myLabel.setText(myLight);
		}
	}

	//change to return
	private String lights()
	{
		if(isOn)
		{
			this.setBackground(Color.YELLOW);
			return "on";
		}
		else
		{
			this.setBackground(Color.BLACK);
			return "off";
		}
	}
}