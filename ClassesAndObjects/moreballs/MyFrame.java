package moreballs;

import javax.swing.JFrame;

public class MyFrame {

	public static void main(String[] args)
	{
		JFrame f = new JFrame("My Methods Frame");
		
		MyBallPanel mbp = new MyBallPanel();
		f.add(mbp);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
