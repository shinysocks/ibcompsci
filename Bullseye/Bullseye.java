import javax.swing.JFrame;

public class Bullseye extends JFrame{
   
    public static void main(String[] args) {
        JFrame frame = new JFrame();
    	frame.setTitle("Bull's Eye");
        BullseyePanel bp = new BullseyePanel();
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.add(bp);
	}
}
