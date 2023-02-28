package calculator;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;

public class Calculator extends JPanel {
    // class variables
    final int WIDTH = 300;
    final int HEIGHT = 600;

    // constructor
    public Calculator() {
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
