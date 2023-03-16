package MethodOverloading;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{

    Rect r1, r2, r3;

    public Panel() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(500, 500));

        r1 = new Rect(120, 160, 50, 40, Color.PINK);
        r2 = new Rect(30, 45, 5, 6, Color.CYAN);
        r3 = new Rect(120, 160, 50, 40, Color.PINK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setPreferredSize(new Dimension(500, 500));

        r1.draw(g);
        r2.draw(g);
        r3.draw(g);
    }
}
