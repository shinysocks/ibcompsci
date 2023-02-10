import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import NeuralNetwork.Generation;

public class Main {
JFrame frame;
	JPanel panel;
	Generation<Agent> gen = new Generation<Agent>(new Agent[100], new Agent(), 20);

	public static void main(String[] args) {
	
		new Main();
	}

	public Main() {
//		for (Agent agent:gen.getNetworks())
//		{
//			System.out.println(agent);
//		}
		frame = new JFrame("Squares");
		panel = new JPanel() {{
				setPreferredSize(new Dimension(100, 100));
			}

			public void paint(Graphics g) {
				for (Agent agent : gen.getNetworks()) {
					agent.draw(g);
				}
			}
		};
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		while (true)
		{
			frame.repaint();
			
			for (Agent agent:gen.getNetworks())
			{
				float[] output = agent.getOutput();
				
				if (output[1] > output[2] && output[1] > .3)
				{
					agent.setDirec(agent.getDirec() + 1);
				} else if (output[2] > .3)
				{
					agent.setDirec(agent.getDirec() - 1);
				}				
				
				if (output[0] > .3)
				{
					agent.move();
				}
				
				if (agent.getX() >= 80 && agent.getY() >= 80)
				{
					System.out.println("win");
				}
			}	
		}
	}

}
