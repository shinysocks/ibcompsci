import javax.swing.JFrame;

public class MyFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		MyPanel mp = new MyPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.add(mp);
	}

}
