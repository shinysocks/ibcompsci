import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements ActionListener{
	final int WIDTH = 200;
	final int HEIGHT = 300;
	
	JButton button;
	int click = 1;
	
	public MyPanel()
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.BLACK);
		
		button = new JButton("TOGGLE");
		button.addActionListener(this);
		this.add(button);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button)
		{
			click++;
			if (click%2 == 0)
			{
				this.setBackground(Color.RED);
			}
			
			if (click%3 == 0)
			{
				this.setBackground(Color.ORANGE);
			}
			
			if (click%4 == 0)
			{
				this.setBackground(Color.YELLOW);
			}
			
			if (click%5 == 0)
			{
				this.setBackground(Color.GREEN);
			}
			
			if (click%6 == 0)
			{
				this.setBackground(Color.BLUE);
			}
			
			if (click%7 == 0)
			{
				this.setBackground(Color.MAGENTA);
			}
		}
		
	}
}
