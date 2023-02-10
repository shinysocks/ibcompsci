import javax.swing.JFrame;

public class MainDriver extends JFrame{
	GamePanel gp = new GamePanel();
	
	public MainDriver()
	{
		this.setTitle("Mario");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(gp);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public static void main(String[] args)
	{
		new MainDriver();
	}
}
